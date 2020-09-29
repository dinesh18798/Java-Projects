package pbgLecture5lab_wrapperForJBox2D;

import java.awt.*;

import javax.swing.JComponent;

public class BasicView extends JComponent {
	/* Author: Michael Fairbank
	 * Creation Date: 2016-01-28
	 * Significant changes applied:
	 */
	// background colour
	public static final Color BG_COLOR = Color.BLACK;

	private BasicPhysicsEngineUsingBox2D game;

	public BasicView(BasicPhysicsEngineUsingBox2D game) {
		this.game = game;
	}
	
	@Override
	public void paintComponent(Graphics g0) {
		BasicPhysicsEngineUsingBox2D game;
		synchronized(this) {
			game=this.game;
		}
		Graphics2D g = (Graphics2D) g0;
		// paint the background
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for (BasicParticle p : game.particles)
			p.draw(g);
		for (BasicPolygon p : game.polygons)
			p.draw(g);		
		for (ElasticConnector c : game.connectors)
			c.draw(g);
		for (AnchoredBarrier b : game.barriers)
			b.draw(g);
		game.flyingFloor.draw(g);
		game.flyingFloorOpposite.draw(g);
		game.launcher.draw(g);
		UpdateShoot(g);
	}


	private void UpdateShoot(Graphics2D g) {
		int x1 = BasicPhysicsEngineUsingBox2D.convertWorldXtoScreenX(game.WORLD_WIDTH/2);
		int y1 = BasicPhysicsEngineUsingBox2D.convertWorldYtoScreenY(game.WORLD_HEIGHT/1.25f);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		if(game.isWin) {
			g.drawString("You Win!!!", x1, y1);
		}
		else {
			g.drawString("No.of Shoot: " + game.noOfShoot, x1, y1);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return BasicPhysicsEngineUsingBox2D.FRAME_SIZE;
	}
	
	public synchronized void updateGame(BasicPhysicsEngineUsingBox2D game) {
		this.game=game;
	}
}