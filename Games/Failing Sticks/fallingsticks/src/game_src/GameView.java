package game_src;

import java.awt.*;

import javax.swing.JComponent;

/**
 * Created by Dinesh on 22/03/2020
 */
public class GameView extends JComponent {

    private Box2DPhysicsEngine physicsEngine;
    private Font currentFont;

    public GameView(Box2DPhysicsEngine engine) {
        this.physicsEngine = engine;
    }

    @Override
    public void paintComponent(Graphics g0) {
        Box2DPhysicsEngine physicsEngine;
        synchronized (this) {
            physicsEngine = this.physicsEngine;
        }
        Graphics2D g = (Graphics2D) g0;
        currentFont = g.getFont();

        // Update the view based on the game state
        switch (physicsEngine.getGameState()) {
            case MENU:
                DrawMenu(g);
                break;
            case GAME_PLAY:
                DrawGameLevel(g);
                break;
            case LEVEL_FAILED:
                DrawLevelFailed(g);
                break;
            case GAME_OVER:
                DrawGameOver(g);
                break;
        }
    }

    private void DrawMenu(Graphics2D g) {
        Color primaryColor = new Color(48, 207, 208);
        Color secondaryColor = new Color(51, 8, 103);
        GradientPaint gp = new GradientPaint(0, 0, primaryColor, 0, getHeight(), secondaryColor);
        g.setPaint(gp);
        g.fillRect(0, 0, getWidth(), getHeight());

        String text = "CE812 - ASSIGNMENT";
        g.setColor(new Color(255, 75, 31));

        Font newFont = currentFont.deriveFont(currentFont.getSize() * 4.0f);
        FontMetrics metrics = g.getFontMetrics(newFont);
        int x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 150);

        text = "FAILING STICKS";
        newFont = currentFont.deriveFont(currentFont.getSize() * 6.0f);
        metrics = g.getFontMetrics(newFont);
        x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 250);

        g.setColor(Color.ORANGE);
        text = "NAME: DINESH MUNIANDY";
        newFont = currentFont.deriveFont(currentFont.getSize() * 2.5f);
        metrics = g.getFontMetrics(newFont);
        x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 400);

        text = "REGISTRATION NO: 1901098";
        newFont = currentFont.deriveFont(currentFont.getSize() * 2.5f);
        metrics = g.getFontMetrics(newFont);
        x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 450);

        text = "EMAIL: dm19363@essex.ac.uk";
        newFont = currentFont.deriveFont(currentFont.getSize() * 2.5f);
        metrics = g.getFontMetrics(newFont);
        x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 500);

        g.setColor(new Color(255, 88, 88));
        text = "Press ENTER to start the game!";
        newFont = currentFont.deriveFont(currentFont.getSize() * 2.5f);
        metrics = g.getFontMetrics(newFont);
        x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 600);

        Color instructionColor = new Color(214, 164, 164);
        g.setColor(instructionColor);
        text = "GAME INSTRUCTION";
        newFont = currentFont.deriveFont(currentFont.getSize() * 1.5f);
        metrics = g.getFontMetrics(newFont);
        x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 800);

        text = "Each level presents you with interconnected sticks.";
        newFont = currentFont.deriveFont(currentFont.getSize() * 1.5f);
        metrics = g.getFontMetrics(newFont);
        x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 825);

        text = "Click on certain joints (yellow circles) so that they swing into non-clickable joints (red circles),";
        newFont = currentFont.deriveFont(currentFont.getSize() * 1.5f);
        metrics = g.getFontMetrics(newFont);
        x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 850);

        g.setColor(GameInfo.SEMI_STATIC_COLOR);
        text = "Click on certain joints (";
        newFont = currentFont.deriveFont(currentFont.getSize() * 1.5f);
        metrics = g.getFontMetrics(newFont);
        x = metrics.stringWidth(text) + x;
        g.setFont(newFont);
        g.drawString("yellow circles", x, 850);

        g.setColor(GameInfo.FIXED_COLOR);
        text = "yellow circles) so that they swing into non-clickable joints (";
        newFont = currentFont.deriveFont(currentFont.getSize() * 1.5f);
        metrics = g.getFontMetrics(newFont);
        x = metrics.stringWidth(text) + x;
        g.setFont(newFont);
        g.drawString("red circles", x, 850);

        g.setColor(instructionColor);
        text = "thus allowing the sticks to fall off of the stage (all circles become green)";
        newFont = currentFont.deriveFont(currentFont.getSize() * 1.5f);
        metrics = g.getFontMetrics(newFont);
        x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 875);

        g.setColor(GameInfo.MOVE_COLOR);
        text = "thus allowing the sticks to fall off of the stage (all circles become ";
        newFont = currentFont.deriveFont(currentFont.getSize() * 1.5f);
        metrics = g.getFontMetrics(newFont);
        x = metrics.stringWidth(text) + x;
        g.setFont(newFont);
        g.drawString("green", x, 875);
    }

    private void DrawGameIntroduction(Graphics2D g) {
        String text = "These types of circles are clickable !!!";
        g.setColor(new Color(255, 251, 213));
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.5f);
        FontMetrics metrics = g.getFontMetrics(newFont);
        int x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 500);
    }

    private void DrawGameLevel(Graphics2D g) {
        Color primaryColor = new Color(83, 120, 149);
        Color secondaryColor = new Color(9, 32, 63);
        GradientPaint gp = new GradientPaint(0, 0, primaryColor, 0, getHeight(), secondaryColor);
        g.setPaint(gp);
        g.fillRect(0, 0, getWidth(), getHeight());

        String text = "";
        if (GameInfo.GameLevel > -1) {
            g.setColor(new Color(238, 194, 118));
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.0f);
            FontMetrics metrics = g.getFontMetrics(currentFont);
            g.setFont(newFont);

            text = String.format("Score: %d", physicsEngine.getScore());
            g.drawString(text, 5, 25);

            text = String.format("Level: %d", GameInfo.GameLevel);
            int x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
            g.drawString(text, x, 25);

            text = String.format("Time Left %s", physicsEngine.getLeftTime());
            x = physicsEngine.SCREEN_WIDTH - (2 * metrics.stringWidth(text));
            g.drawString(text, x, 25);
        } else
            DrawGameIntroduction(g);

        for (StraightLineBoundaries boundary : physicsEngine.boundaries)
            boundary.draw(g);
        // Draw the particles and connectors
        for (Particle particle : physicsEngine.gameLevels.particles)
            particle.draw(g);
        for (ParticleConnector connector : physicsEngine.gameLevels.connectors)
            connector.draw(g);
    }

    private void DrawLevelFailed(Graphics2D g) {
        Color primaryColor = Color.WHITE;
        Color secondaryColor = new Color(239, 59, 54);
        GradientPaint gp = new GradientPaint(0, 0, primaryColor, 0, getHeight(), secondaryColor);
        g.setPaint(gp);
        g.fillRect(0, 0, getWidth(), getHeight());

        String text = "Level Failed!!!";
        g.setColor(new Color(50, 59, 67));

        Font newFont = currentFont.deriveFont(currentFont.getSize() * 5.0f);
        FontMetrics metrics = g.getFontMetrics(newFont);
        int x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 500);

        g.setColor(new Color(0, 77, 115));
        text = "Press SPACE to retry";
        newFont = currentFont.deriveFont(currentFont.getSize() * 4.0f);
        metrics = g.getFontMetrics(newFont);
        x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 600);
    }

    private void DrawGameOver(Graphics2D g) {
        Color primaryColor = new Color(17, 153, 142);
        Color secondaryColor = new Color(56, 239, 125);
        GradientPaint gp = new GradientPaint(0, 0, primaryColor, 0, getHeight(), secondaryColor);
        g.setPaint(gp);
        g.fillRect(0, 0, getWidth(), getHeight());

        String text = "Congratulations, you have completed all levels!";
        g.setColor(new Color(255, 32, 82));

        Font newFont = currentFont.deriveFont(currentFont.getSize() * 3.5f);
        FontMetrics metrics = g.getFontMetrics(newFont);
        int x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 500);

        g.setColor(new Color(86, 84, 164));
        text = "Press SPACE to restart the game";
        newFont = currentFont.deriveFont(currentFont.getSize() * 2.0f);
        metrics = g.getFontMetrics(newFont);
        x = (physicsEngine.SCREEN_WIDTH - metrics.stringWidth(text)) / 2;
        g.setFont(newFont);
        g.drawString(text, x, 600);
    }

    @Override
    public Dimension getPreferredSize() {
        return Box2DPhysicsEngine.FRAME_SIZE;
    }
}