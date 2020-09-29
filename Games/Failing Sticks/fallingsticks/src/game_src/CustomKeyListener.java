package game_src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Dinesh on 22/03/2020
 */
public class CustomKeyListener extends KeyAdapter {

    private Box2DPhysicsEngine physicsEngine;

    public CustomKeyListener(Box2DPhysicsEngine engine) {
        this.physicsEngine = engine;
    }

    public void keyPressed(KeyEvent e) {
        return;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_ENTER:
                if (physicsEngine.getGameState() == GameInfo.GameState.MENU)
                    physicsEngine.setGameState(GameInfo.GameState.GAME_PLAY);
                break;
            case KeyEvent.VK_SPACE:
                if (physicsEngine.getGameState() == GameInfo.GameState.GAME_OVER)
                    physicsEngine.setGameState(GameInfo.GameState.MENU);
                if (physicsEngine.getGameState() == GameInfo.GameState.LEVEL_FAILED)
                    physicsEngine.setGameState(GameInfo.GameState.GAME_PLAY);
                break;
        }
    }
}
