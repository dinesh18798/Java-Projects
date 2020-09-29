package game_src;

import java.awt.*;

/**
 * Created by Dinesh on 22/03/2020
 */
public class GameInfo {

    public static int GameLevel = -1;
    public static int MAX_GAME_LEVEL = 30; // Maximum levels in the game

    // Default colours
    public static final Color MOVE_COLOR = new Color(42, 245, 152);
    public static final Color SEMI_STATIC_COLOR = new Color(255, 235, 59);
    public static final Color FIXED_COLOR = new Color(244, 67, 54);
    public static final Color CONNECTOR_COLOR = Color.LIGHT_GRAY;

    public enum GameState {
        MENU,
        GAME_PLAY,
        LEVEL_FAILED,
        GAME_OVER
    }

    public enum ParticleType {
        DYNAMIC,
        SEMI_STATIC,
        FIXED
    }
}
