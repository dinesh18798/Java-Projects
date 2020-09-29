package pbgLecture3lab;

import java.awt.*;

import javax.swing.JComponent;

public class BasicView extends JComponent {
	/* Author: Michael Fairbank
	 * Creation Date: 2016-01-28
	 * Significant changes applied:
	 */
	// background colour
	public static final Color BG_COLOR = new Color(10, 108, 3);

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
		for (AnchoredBarrier b : game.barriers)
			b.draw(g);
		if(game.basicMouseListener.isMouseButtonDragged())
			DrawMouseLine(g);
		UpdateScore(g);
	}

	private void UpdateScore(Graphics2D g) {
		int x1 = BasicPhysicsEngine.convertWorldXtoScreenX(8.5);
		int y1 = BasicPhysicsEngine.convertWorldYtoScreenY(13);
		g.drawString("Score: " + game.getScore(), x1,y1);
	}

	public void DrawMouseLine(Graphics2D g){
		Vect2D start=game.GetMousePosition();
		Vect2D end = game.GetCueBall().getPos();
		g.setColor(new Color(101, 67, 33));
		int x1 = BasicPhysicsEngine.convertWorldXtoScreenX(start.x);
		int y1 = BasicPhysicsEngine.convertWorldYtoScreenY(start.y);
		int x2 = BasicPhysicsEngine.convertWorldXtoScreenX(end.x);
		int y2 = BasicPhysicsEngine.convertWorldYtoScreenY(end.y);
		g.drawLine(x1,y1,x2,y2);

		Vect2D newMidpoint = end.mult(2);
		Vect2D endPoint = Vect2D.minus(newMidpoint,start);
		int x3 = BasicPhysicsEngine.convertWorldXtoScreenX(endPoint.x);
		int y3 = BasicPhysicsEngine.convertWorldYtoScreenY(endPoint.y);
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		g.setStroke(dashed);
		g.setColor(Color.YELLOW);
		g.drawLine(x2,y2,x3,y3);
	}

	@Override
	public Dimension getPreferredSize() {
		return BasicPhysicsEngine.FRAME_SIZE;
	}
	
	public synchronized void updateGame(BasicPhysicsEngine game) {
		this.game=game;
	}
}