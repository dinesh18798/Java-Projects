package pbgLecture1lab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JComponent;

public class BasicView extends JComponent {
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
		drawTarget(g);
	}

	public void drawTarget(Graphics2D g) {
		int x = BasicPhysicsEngine.convertWorldXtoScreenX(game.target.x);
		int y = BasicPhysicsEngine.convertWorldYtoScreenY(game.target.y);
		int width = BasicPhysicsEngine.convertWorldLengthToScreenLength(game.target.width);
		int height = BasicPhysicsEngine.convertWorldLengthToScreenLength(game.target.height);
		g.setColor(Color.RED);
		g.fillRect(x,y,width,height);
	}

	@Override
	public Dimension getPreferredSize() {
		return BasicPhysicsEngine.FRAME_SIZE;
	}
	
	public synchronized void updateGame(BasicPhysicsEngine game) {
		this.game=game;
	}
}