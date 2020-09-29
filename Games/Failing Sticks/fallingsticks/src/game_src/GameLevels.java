package game_src;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinesh on 27/03/2020
 */
public class GameLevels {

    private static final float WORLD_HEIGHT = Box2DPhysicsEngine.WORLD_HEIGHT;
    private static final float WORLD_WIDTH = Box2DPhysicsEngine.WORLD_WIDTH;
    private static final float MASS = 10f;
    private static final float RADIUS = 0.15f;
    private static final float ROLLING_FRICTION = 0.25f;
    protected List<Particle> particles;
    protected List<ParticleConnector> connectors;
    private Box2DPhysicsEngine physicsEngine;
    private int count = 1;

    public GameLevels(Box2DPhysicsEngine engine) {
        this.physicsEngine = engine;
        particles = new ArrayList<>();
        connectors = new ArrayList<>();
    }

    // Get the puzzle pattern based on the level
    public void getGameLevel(int gameLevel) {
        particles.clear();
        connectors.clear();

        count = 1;
        // The particles and connectors will add to the list on switch the current level
        switch (GameInfo.GameLevel) {
            case -1: {
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                break;
            }
            case 0: {
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.4f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                break;
            }
            case 1: {
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(2)));
                break;
            }
            case 2: {
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                break;
            }
            case 3: {
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                break;
            }
            case 4: {
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                break;
            }
            case 5: {
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                break;
            }
            case 6: {
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                break;
            }
            case 7: {
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(8)));
                break;
            }
            case 8: {
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(0)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(9)));
                break;
            }
            case 9: {
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(8)));
                break;
            }
            case 10: {
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(0)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(13)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(14)));
                connectors.add(new ParticleConnector(particles.get(14), particles.get(15)));
                connectors.add(new ParticleConnector(particles.get(15), particles.get(13)));
                break;
            }
            case 11: {
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));

                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));

                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(13)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(14)));

                connectors.add(new ParticleConnector(particles.get(15), particles.get(16)));
                connectors.add(new ParticleConnector(particles.get(16), particles.get(17)));
                connectors.add(new ParticleConnector(particles.get(17), particles.get(18)));
                connectors.add(new ParticleConnector(particles.get(18), particles.get(19)));
                connectors.add(new ParticleConnector(particles.get(19), particles.get(20)));
                break;
            }
            case 12: {
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));

                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));


                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));

                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));

                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(0)));

                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(3)));

                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(6)));

                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(9)));

                connectors.add(new ParticleConnector(particles.get(12), particles.get(13)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(14)));
                connectors.add(new ParticleConnector(particles.get(14), particles.get(12)));

                connectors.add(new ParticleConnector(particles.get(15), particles.get(16)));
                connectors.add(new ParticleConnector(particles.get(16), particles.get(17)));
                connectors.add(new ParticleConnector(particles.get(17), particles.get(15)));
                break;
            }
            case 13: {
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                break;
            }
            case 14: {
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), true, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(0)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(1)));
                break;
            }
            case 15: {
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.55f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(13)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(14)));
                break;
            }
            case 16: {
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), true, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(13)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(14)));
                connectors.add(new ParticleConnector(particles.get(14), particles.get(12)));
                break;
            }
            case 17: {
                particles.add(new Particle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 0.875f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 0.875f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 0.875f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 - 2.625f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 0.875f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 2.625f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                break;
            }
            case 18: {
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(13)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(14)));
                break;
            }
            case 19: {
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(0)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(5)));
                break;
            }
            case 20: {
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(13)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(14)));
                connectors.add(new ParticleConnector(particles.get(14), particles.get(15)));
                connectors.add(new ParticleConnector(particles.get(15), particles.get(16)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(16)));
                break;
            }
            case 21: {
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(0)));
                break;
            }
            case 22: {
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(0)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(9)));
                break;
            }
            case 23: {
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(13)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(6)));
                break;
            }
            case 24: {
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(0)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(13)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(14)));
                connectors.add(new ParticleConnector(particles.get(14), particles.get(15)));
                connectors.add(new ParticleConnector(particles.get(15), particles.get(16)));
                connectors.add(new ParticleConnector(particles.get(16), particles.get(17)));
                connectors.add(new ParticleConnector(particles.get(16), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(17), particles.get(1)));
                break;
            }
            case 25: {
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(13)));
                connectors.add(new ParticleConnector(particles.get(14), particles.get(15)));
                connectors.add(new ParticleConnector(particles.get(15), particles.get(16)));
                connectors.add(new ParticleConnector(particles.get(15), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(16), particles.get(17)));
                connectors.add(new ParticleConnector(particles.get(16), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(17), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(17), particles.get(7)));
                break;
            }
            case 26: {
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 5f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(13)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(14)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(14), particles.get(15)));
                connectors.add(new ParticleConnector(particles.get(15), particles.get(16)));
                connectors.add(new ParticleConnector(particles.get(16), particles.get(17)));
                connectors.add(new ParticleConnector(particles.get(16), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(17), particles.get(0)));
                ;
                break;
            }
            case 27: {
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(12)));
                break;
            }
            case 28: {
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(0), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(4)));

                connectors.add(new ParticleConnector(particles.get(11), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(13)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(14)));
                connectors.add(new ParticleConnector(particles.get(14), particles.get(15)));
                connectors.add(new ParticleConnector(particles.get(15), particles.get(16)));
                connectors.add(new ParticleConnector(particles.get(16), particles.get(17)));
                connectors.add(new ParticleConnector(particles.get(17), particles.get(18)));
                connectors.add(new ParticleConnector(particles.get(18), particles.get(19)));
                connectors.add(new ParticleConnector(particles.get(19), particles.get(20)));
                connectors.add(new ParticleConnector(particles.get(20), particles.get(21)));
                connectors.add(new ParticleConnector(particles.get(21), particles.get(15)));
                break;
            }
            case 29: {
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(0)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(13)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(14)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(14), particles.get(15)));
                connectors.add(new ParticleConnector(particles.get(15), particles.get(2)));
                break;
            }
            case 30: {
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 + 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 + 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 + 3.5f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, true));
                particles.add(new Particle(WORLD_WIDTH / 2 + 1.75f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 4f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 3.5f, WORLD_HEIGHT / 2 - 3f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.FIXED), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2 - 1.75f, WORLD_HEIGHT / 2 - 2f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.DYNAMIC), false, false));
                particles.add(new Particle(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2 - 1f, 0, 0, RADIUS, MASS, ROLLING_FRICTION, new ParticleInfo(++count, GameInfo.ParticleType.SEMI_STATIC), true, false));

                connectors.add(new ParticleConnector(particles.get(0), particles.get(1)));
                connectors.add(new ParticleConnector(particles.get(1), particles.get(2)));
                connectors.add(new ParticleConnector(particles.get(2), particles.get(3)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(0)));
                connectors.add(new ParticleConnector(particles.get(3), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(4), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(6)));
                connectors.add(new ParticleConnector(particles.get(6), particles.get(7)));
                connectors.add(new ParticleConnector(particles.get(7), particles.get(5)));
                connectors.add(new ParticleConnector(particles.get(5), particles.get(8)));
                connectors.add(new ParticleConnector(particles.get(8), particles.get(9)));
                connectors.add(new ParticleConnector(particles.get(9), particles.get(10)));
                connectors.add(new ParticleConnector(particles.get(10), particles.get(11)));
                connectors.add(new ParticleConnector(particles.get(11), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(13)));
                connectors.add(new ParticleConnector(particles.get(12), particles.get(16)));
                connectors.add(new ParticleConnector(particles.get(13), particles.get(14)));
                connectors.add(new ParticleConnector(particles.get(14), particles.get(15)));
                connectors.add(new ParticleConnector(particles.get(15), particles.get(12)));
                connectors.add(new ParticleConnector(particles.get(16), particles.get(4)));
                connectors.add(new ParticleConnector(particles.get(16), particles.get(8)));
                break;
            }
            default:
                break;
        }
    }
}
