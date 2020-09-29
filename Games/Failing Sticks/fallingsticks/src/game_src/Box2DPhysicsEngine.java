package game_src;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

import audio.*;
import settings.SettingsFile;

/**
 * Created by Dinesh on 22/03/2020
 */
public class Box2DPhysicsEngine {

    // frame dimensions
    public static final int SCREEN_HEIGHT = 1080;
    public static final int SCREEN_WIDTH = 900;
    public static final Dimension FRAME_SIZE = new Dimension(
            SCREEN_WIDTH, SCREEN_HEIGHT);
    public static final float WORLD_WIDTH = 10;//metres
    // meters - keeps world dimensions in same aspect ratio as screen dimensions, so that circles get transformed into circles as opposed to ovals
    public static final float WORLD_HEIGHT = SCREEN_HEIGHT * (WORLD_WIDTH / SCREEN_WIDTH);
    public static final float GRAVITY = 9.8f;
    public static final boolean ALLOW_MOUSE_POINTER_TO_CLICK_BODIES_ON_SCREEN = true; // flag to click the bodies

    // sleep time between two frames in milliseconds
    public static final long DELAY = 20;

    // time step rate at 60Hz
    public static final float TIME_STEP = 1.0f / 60.0f;
    public static final int VELOCITY_ITERATIONS = 10;
    public static final int POSITION_ITERATIONS = 10;

    public static World world;  // Box2D container for all bodies and barriers

    protected List<StraightLineBoundaries> boundaries;
    protected GameLevels gameLevels; // Contain the particle pattern for each level

    private static SettingsFile settingsFile; // Read and update the game settings ie game level
    private static CustomMouseListener customMouseListener;
    private static CustomKeyListener customKeyListener;
    private CustomContactListener customContactListener; // The contact listener which attached to the world
    private GameInfo.GameState gameState = GameInfo.GameState.MENU;

    private int fixedCount = 0; // Count of static particles
    private int semiStaticCount = 0; //Count of semi-static particles
    private static int dynamicCount = 0; //Count of dynamic(moving) particles
    private static int localLevel;
    private static int score = 0;
    private static boolean allParticleDynamic = false; // flag to check whether all particles are moving

    // Background music and game sound effects
    private static MusicPlayer musicPlayer;
    private static SoundEffects soundEffects;

    private CountDownTimer countDownTimer;

    public Box2DPhysicsEngine() {
        localLevel = GameInfo.GameLevel; // Initially the game level set to -1
        loadWorldAndLevel(); // Setting up the world and load the current level
    }

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        final Box2DPhysicsEngine engine = new Box2DPhysicsEngine();
        final GameView view = new GameView(engine);
        GameFrame gameFrame = new GameFrame(view, "Falling Sticks Game");
        customMouseListener = new CustomMouseListener(engine);
        customKeyListener = new CustomKeyListener(engine);
        gameFrame.addKeyListener(customKeyListener);
        view.addMouseMotionListener(customMouseListener);
        view.addMouseListener(customMouseListener);
        settingsFile = new SettingsFile();
        int[] tempValues = new int[2];
        settingsFile.readGameSettings(tempValues);
        localLevel = tempValues[0];
        score = tempValues[1];
        GameInfo.GameLevel = localLevel;
        musicPlayer = new MusicPlayer();
        soundEffects = new SoundEffects();
        pool.execute(musicPlayer);
        engine.startThread(view);
    }

    public void update() {
        semiStaticCount = 0;
        fixedCount = 0;
        dynamicCount = 0;
        allParticleDynamic = false;

        for (Particle p : gameLevels.particles) {
            p.notificationOfNewTimestep();

            // increase the count of each particle type for validation
            switch (p.getParticleInfo().getParticleType()) {
                case SEMI_STATIC:
                    semiStaticCount += 1;
                    break;
                case FIXED:
                    fixedCount += 1;
                    break;
                case DYNAMIC:
                    dynamicCount += 1;
                    break;
                default:
                    break;
            }
        }

        // when game state is GAME_PLAY
        if (getGameState() == GameInfo.GameState.GAME_PLAY) {
            // if all particles are dynamic then flag set to true
            if (dynamicCount == gameLevels.particles.size()) allParticleDynamic = true;

            if (localLevel > -1 && countDownTimer != null) {
                // if clickable particle count is zero or timer stop then current level failed
                if ((fixedCount > 0 && semiStaticCount == 0) || !countDownTimer.isTimerRunning()) {
                    setGameState(GameInfo.GameState.LEVEL_FAILED);
                }
            }
        }

        world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    public void loadNextLevel() {
        // if flag is true then go to the next level
        if (allParticleDynamic) {
            if (countDownTimer != null) {
                countDownTimer.stopTimer();
                updateScore();
            }
            if (localLevel == GameInfo.GameLevel) {
                GameInfo.GameLevel++; // increase the game level by 1
                localLevel = GameInfo.GameLevel;
                // update the current game level and score in settings file
                settingsFile.updateGameSettings(localLevel, score);
            }
            // if the level reached maximum limit then the game over
            // else load the next level
            if (GameInfo.GameLevel > GameInfo.MAX_GAME_LEVEL)
                setGameState(GameInfo.GameState.GAME_OVER);
            else
                loadWorldAndLevel();
        }
    }

    public String getLeftTime() {
        return countDownTimer.getLeftTimeInString(); // return the time left in string format
    }

    public GameInfo.GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameInfo.GameState gameState) {
        this.gameState = gameState;
        checkGameState();
    }

    public int getScore() {
        return score;
    }

    public static int convertWorldXtoScreenX(float worldX) {
        return (int) (worldX / WORLD_WIDTH * SCREEN_WIDTH);
    }

    public static int convertWorldYtoScreenY(float worldY) {
        // minus sign in here is because screen coordinates are upside down.
        return (int) (SCREEN_HEIGHT - (worldY / WORLD_HEIGHT * SCREEN_HEIGHT));
    }

    public static float convertWorldLengthToScreenLength(float worldLength) {
        return (worldLength / WORLD_WIDTH * SCREEN_WIDTH);
    }

    public static float convertScreenXtoWorldX(int screenX) {
        return screenX * WORLD_WIDTH / SCREEN_WIDTH;
    }

    public static float convertScreenYtoWorldY(int screenY) {
        return (SCREEN_HEIGHT - screenY) * WORLD_HEIGHT / SCREEN_HEIGHT;
    }

    protected void playClick() {
        soundEffects.playClickEffect();
    }

    protected void playShatter() {
        soundEffects.playShatterEffect();
    }

    // change the particle body type dynamically
    protected void changeBodyType(int id) {
        try {
            for (Particle p : gameLevels.particles) {
                // check whether the body id is equal
                if (p.id == id) {
                    // check whether the body type is static
                    if (p.body.getType() == BodyType.STATIC) {
                        ParticleInfo particleInfo = p.getParticleInfo();
                        // updating the particle info and set it to the body user data
                        particleInfo.setParticleType(GameInfo.ParticleType.DYNAMIC);
                        p.body.setUserData(particleInfo);
                    }
                }
            }
        } catch (Exception exp) {
            System.out.println("Unable to change the body type");
            exp.printStackTrace();
        }
    }

    // create world and game level
    private void loadWorldAndLevel() {
        if (customContactListener != null) customContactListener = null;
        if (gameLevels != null) gameLevels = null;
        if (world != null) world = null;
        if (boundaries != null) boundaries = null;

        customContactListener = new CustomContactListener(this);
        world = new World(new Vec2(0, -GRAVITY));
        world.setContinuousPhysics(true);
        world.setContactListener(customContactListener); // assigning contact listener to the world

        gameLevels = new GameLevels(this);
        boundaries = new ArrayList<>();

        updateLevelBoundary();
        updateLevelStage();
    }

    private void updateLevelStage() {
        if (gameState == GameInfo.GameState.GAME_PLAY) {
            // getting the puzzle pattern for the particular level
            gameLevels.getGameLevel(localLevel);

            if (localLevel > -1) {
                if (countDownTimer != null) {
                    countDownTimer = null;
                }
                // recreate countdown timer for each level
                countDownTimer = new CountDownTimer();
            }
        }
    }

    private void updateLevelBoundary() {
        boundaries.clear();
        // add the boundaries
        switch (gameState) {
            case GAME_PLAY: {
                boundaries.add(new StraightLineBoundaries(0, -WORLD_HEIGHT, WORLD_WIDTH, -WORLD_HEIGHT, Color.WHITE, "bottom"));
                boundaries.add(new StraightLineBoundaries(WORLD_WIDTH, -WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT, Color.GREEN, "right"));
                boundaries.add(new StraightLineBoundaries(WORLD_WIDTH, WORLD_HEIGHT, 0, WORLD_HEIGHT, Color.ORANGE, "top"));
                boundaries.add(new StraightLineBoundaries(0, WORLD_HEIGHT, 0, -WORLD_HEIGHT, Color.RED, "left"));
                break;
            }
        }
    }

    // update the score based on the time taken
    private void updateScore() {
        int time = countDownTimer.getCountdownTimeInSeconds();
        if (isBetween(time, 41, 60))
            score += 100;
        if (isBetween(time, 21, 40))
            score += 50;
        if (isBetween(time, 1, 20))
            score += 25;
    }

    private static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    private void startThread(final GameView view) throws InterruptedException {
        final Box2DPhysicsEngine engine = this;
        while (true) {
            engine.update();
            view.repaint();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException exp) {
                exp.printStackTrace();
            }
        }
    }

    private void checkGameState() {
        if (gameState == GameInfo.GameState.MENU)
            GameInfo.GameLevel = 0;
        loadWorldAndLevel();
    }
}