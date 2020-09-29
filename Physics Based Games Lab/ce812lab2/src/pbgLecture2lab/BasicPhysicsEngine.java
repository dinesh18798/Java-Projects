package pbgLecture2lab;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class BasicPhysicsEngine {

	public static JEasyFrame frame;
	// frame dimensions
	public static final int SCREEN_HEIGHT = 680;
	public static final int SCREEN_WIDTH = 640;
	public static final Dimension FRAME_SIZE = new Dimension(
			800, 1000);
	public static final double WORLD_WIDTH=10;//metres
	public static final double WORLD_HEIGHT=SCREEN_HEIGHT*(WORLD_WIDTH/SCREEN_WIDTH);// meters - keeps world dimensions in same aspect ratio as screen dimensions, so that circles get transformed into circles as opposed to ovals
	public static final double GRAVITY=9.8;

	// sleep time between two drawn frames in milliseconds
	public static final int DELAY = 20;
	public static final int NUM_EULER_UPDATES_PER_SCREEN_REFRESH=10;
	// estimate for time between two frames in seconds 
	public static final double DELTA_T = DELAY / 1000.0 / NUM_EULER_UPDATES_PER_SCREEN_REFRESH ;

	public static AnchoredBarrier_Flipper rightFlipper;
	public static AnchoredBarrier_Flipper leftFlipper;
	
	public static int convertWorldXtoScreenX(double worldX) {
		return (int) (worldX/WORLD_WIDTH*SCREEN_WIDTH);
	}
	public static int convertWorldYtoScreenY(double worldY) {
		// minus sign in here is because screen coordinates are upside down.
		return (int) (SCREEN_HEIGHT-(worldY/WORLD_HEIGHT*SCREEN_HEIGHT));
	}
	public static int convertWorldLengthToScreenLength(double worldLength) {
		return (int) (worldLength/WORLD_WIDTH*SCREEN_WIDTH);
	}
	
	
	
	public List<BasicParticle> particles;
	public List<AnchoredBarrier> barriers;
	public List<AnchoredBarrier_Flipper> flippers;
	
	public static enum LayoutMode {CONVEX_ARENA, CONCAVE_ARENA, CONVEX_ARENA_WITH_CURVE, PINBALL_ARENA, RECTANGLE, PINBALL_BOARD};
	public BasicPhysicsEngine() {
		particles = new ArrayList<BasicParticle>();
		barriers = new ArrayList<AnchoredBarrier>();
		flippers = new ArrayList<AnchoredBarrier_Flipper>();
		// empty particles array, so that when a new thread starts it clears current particle state:
		particles = new ArrayList<BasicParticle>();
		
		LayoutMode layout=LayoutMode.PINBALL_BOARD;
		if (layout==LayoutMode.PINBALL_ARENA || layout==LayoutMode.PINBALL_BOARD) {
			double pinballradius=0.2;
			particles.add(new BasicParticle(WORLD_WIDTH-pinballradius*1.01,pinballradius,0,14, pinballradius,true, Color.RED, 2));
		} else {
			double r=.2;
			particles.add(new BasicParticle(3*r+WORLD_WIDTH/2+1,WORLD_HEIGHT/2-2,-3*2,9.7*2, 0.4,true, Color.BLUE, 2*4));
			
		}

		
		barriers = new ArrayList<AnchoredBarrier>();
		
		switch (layout) {
			case RECTANGLE: {
				// rectangle walls:
				// anticlockwise listing
				barriers.add(new AnchoredBarrier_StraightLine(0, 0, WORLD_WIDTH, 0, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT, 0, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(0, WORLD_HEIGHT, 0, 0, Color.WHITE));
				break;
			}
			case CONVEX_ARENA: {
				barriers.add(new AnchoredBarrier_StraightLine(0,WORLD_HEIGHT/3, WORLD_WIDTH/2, 0, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/2, 0, WORLD_WIDTH, WORLD_HEIGHT/3, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT/3, WORLD_WIDTH, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT, 0, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(0, WORLD_HEIGHT, 0, WORLD_HEIGHT/3, Color.WHITE));
				break;
			}
			case CONCAVE_ARENA: {
				barriers.add(new AnchoredBarrier_StraightLine(0,WORLD_HEIGHT/3, WORLD_WIDTH/2, 0, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/2, 0, WORLD_WIDTH, WORLD_HEIGHT/3, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT/3, WORLD_WIDTH, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT, 0, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(0, WORLD_HEIGHT, 0, WORLD_HEIGHT/3, Color.WHITE));
				double width=WORLD_HEIGHT/20;
				barriers.add(new AnchoredBarrier_StraightLine(0, WORLD_HEIGHT*2/3, WORLD_WIDTH/2, WORLD_HEIGHT*1/2, Color.WHITE,width/10));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/2, WORLD_HEIGHT*1/2, WORLD_WIDTH/2, WORLD_HEIGHT*1/2-width, Color.WHITE,width/10));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/2, WORLD_HEIGHT*1/2-width, 0, WORLD_HEIGHT*2/3-width, Color.WHITE,width/10));
				break;
			}
			case CONVEX_ARENA_WITH_CURVE: {
				barriers.add(new AnchoredBarrier_StraightLine(0,WORLD_HEIGHT/3, WORLD_WIDTH/2, 0, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/2, 0, WORLD_WIDTH, WORLD_HEIGHT/3, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT/3, WORLD_WIDTH, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT, 0, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(0, WORLD_HEIGHT, 0, 0, Color.WHITE));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH/2, WORLD_HEIGHT-WORLD_WIDTH/2, WORLD_WIDTH/2, 0.0, 180.0,true, Color.WHITE));
				break;
			}
			case PINBALL_ARENA: {
				// simple pinball board
				barriers.add(new AnchoredBarrier_StraightLine(0, 0, WORLD_WIDTH, 0, Color.WHITE, 1.0));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT, Color.WHITE, 0.75));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT, 0, WORLD_HEIGHT, Color.WHITE, 0.5));
				barriers.add(new AnchoredBarrier_StraightLine(0, WORLD_HEIGHT, 0, 0, Color.WHITE, 0.75));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH/2, WORLD_HEIGHT-WORLD_WIDTH/2, WORLD_WIDTH/2, 0.0, 200.0,true, Color.WHITE, 0.5));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH/2, WORLD_HEIGHT*3/4, WORLD_WIDTH/15, -0.0, 360.0,false, Color.WHITE, 1.0));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH*1/3, WORLD_HEIGHT*1/2, WORLD_WIDTH/15, -0.0, 360.0,false, Color.WHITE, 1.0));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH*2/3, WORLD_HEIGHT*1/2, WORLD_WIDTH/15, -0.0, 360.0,false, Color.WHITE, 1.0));
				break;
			}

			case PINBALL_BOARD: {
				barriers.add(new AnchoredBarrier_StraightLine(0, 0, WORLD_WIDTH/3.0, -2.5, Color.GREEN, 0.75));
				leftFlipper = new AnchoredBarrier_Flipper(WORLD_WIDTH/3.0, -2.5, WORLD_WIDTH/2.25, -2.5, Color.RED, 0.5, false);
                barriers.add(leftFlipper);
				//barriers.add(rightFlipper);
				rightFlipper = new AnchoredBarrier_Flipper(WORLD_WIDTH/1.8, -2.5, WORLD_WIDTH/1.5, -2.5, Color.CYAN, 0.5, true);
				//barriers.add(leftFlipper);
                barriers.add(rightFlipper);
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/1.5, -2.5, WORLD_WIDTH, 0, Color.GREEN, 0.75));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT/1.5, Color.ORANGE, 0.75));
				barriers.add(new AnchoredBarrier_StraightLine(0, WORLD_HEIGHT/1.5, 0, 0, Color.MAGENTA, 0.75));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/1.44, WORLD_HEIGHT/1.045, WORLD_WIDTH/3.24, WORLD_HEIGHT/1.045, Color.WHITE, 0.75));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH/1.44, WORLD_HEIGHT/1.5, WORLD_WIDTH/3.25, 0, 90.0,true, Color.BLUE, 0.5));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH/3.24, WORLD_HEIGHT/1.5, WORLD_WIDTH/3.25, 90, 90.0,true, Color.PINK, 0.5));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH/2, WORLD_HEIGHT*3/4, WORLD_WIDTH/10, -0.0, 360.0,false, Color.YELLOW, 1.0));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH*1/3, WORLD_HEIGHT*1/2, WORLD_WIDTH/15, -0.0, 360.0,false, Color.CYAN, 1.0));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH*2/3, WORLD_HEIGHT*1/2, WORLD_WIDTH/15, -0.0, 360.0,false, Color.WHITE, 1.0));
               // barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/3, WORLD_HEIGHT/3.5, WORLD_WIDTH/1.5, WORLD_HEIGHT/3.5, Color.GREEN, 0.75));
                //barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/3, WORLD_HEIGHT/3.5, WORLD_WIDTH/3, WORLD_HEIGHT/6, Color.BLUE, 0.75));
				break;
			}
		}
			
			
	}
	public static void main(String[] args) throws Exception {
		final BasicPhysicsEngine game = new BasicPhysicsEngine();
		final BasicView view = new BasicView(game);

		frame = new JEasyFrame(view, "Basic Physics Engine");
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_A:
					    leftFlipper.moveFlipper();
						break;
					case KeyEvent.VK_RIGHT:
					case KeyEvent.VK_D:
						rightFlipper.moveFlipper();
						break;
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		game.startThread(view);
	}
	private void startThread(final BasicView view) throws InterruptedException {
		final BasicPhysicsEngine game=this;
		while (true) {
			for (int i=0;i<NUM_EULER_UPDATES_PER_SCREEN_REFRESH;i++) {
				game.update();
			}
			view.repaint();

			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
			}
		}
	}
	


	public void update() {
		for (BasicParticle p : particles) {
			p.update(); // tell each particle to move
		}

		for (BasicParticle particle : particles) {
			for (AnchoredBarrier b : barriers) {
				if (b.isCircleCollidingBarrier(particle.getPos(), particle.getRadius())) {
					Vect2D bouncedVel=b.calculateVelocityAfterACollision(particle.getPos(), particle.getVel(), 1.0);
					particle.setVel(bouncedVel);
				}
			}
		}
	}
	
	
	

}


