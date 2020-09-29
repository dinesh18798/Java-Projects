package pbgLecture4lab;

import java.awt.*;

import javax.swing.JComponent;

public class BasicView extends JComponent {
	/* Author: Michael Fairbank
	 * Creation Date: 2016-01-28
	 * Significant changes applied:
	 */
	// background colour
	public static final Color BG_COLOR = Color.BLACK;

	private BasicPhysicsEngine game;

	public BasicView(BasicPhysicsEngine game) {
		this.game = game;
	}
	
	@Override
	public void paintComponent(Graphics g0) {
		BasicPhysicsEngine game;
		synchronized(this) {
			game=this.game;
		}
		Graphics2D g = (Graphics2D) g0;
		// paint the background
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (BasicParticle p : game.particles)
			p.draw(g);
		for (ElasticConnector c : game.connectors)
			c.draw(g);
		for (AnchoredBarrier b : game.barriers)
			b.draw(g);
		UpdateScore(g);
	}

	private void UpdateScore(Graphics2D g) {
		int x1 = BasicPhysicsEngine.convertWorldXtoScreenX(game.WORLD_WIDTH/2);
		int y1 = BasicPhysicsEngine.convertWorldYtoScreenY(0.5);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		if(game.isWin()) {
			g.drawString("You Win!!!", x1, y1);
		}
		else {
			if(game.getLive() > 0)
				g.drawString("Lives: " + game.getLive(), x1, y1);
			else
				g.drawString("Game Over", x1, y1);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return BasicPhysicsEngine.FRAME_SIZE;
	}
	
	public synchronized void updateGame(BasicPhysicsEngine game) {
		this.game=game;
	}
}