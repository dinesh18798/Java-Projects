package pbgLecture1lab;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BasicPhysicsEngine {
	// frame dimensions
	public static final int SCREEN_HEIGHT = 680;
	public static final int SCREEN_WIDTH = 640;
	public static final Dimension FRAME_SIZE = new Dimension(
			SCREEN_WIDTH, SCREEN_HEIGHT);
	public static final double WORLD_WIDTH=10;//metres
	public static final double WORLD_HEIGHT=SCREEN_HEIGHT*(WORLD_WIDTH/SCREEN_WIDTH);// meters - keeps world dimensions in same aspect ratio as screen dimensions, so that circles get transformed into circles as opposed to ovals
	public static double GRAVITY=9.8;

	// sleep time between two drawn frames in milliseconds
	public static final int DELAY = 20;
	public static final int NUM_EULER_UPDATES_PER_SCREEN_REFRESH=10;
	// estimate for time between two frames in seconds 
	public static final double DELTA_T = DELAY / 1000.0 / NUM_EULER_UPDATES_PER_SCREEN_REFRESH ;

	//Target
	public Rectangle target;
	int leftLimit = 0;
	private static int rightLimit = 27;
	private static int rand = new Random().nextInt(rightLimit);
	private int targetPosX = 0;
	private int targetPosY = 1;
	private int targetHeight = 1;
	private int targetWidth = 1;

	public boolean collide = false;

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
	public Vect2D ballPosition;
	public static final boolean includeInbuiltCollisionDetection=false;
	public BasicPhysicsEngine() {
		particles = new ArrayList<BasicParticle>();
		// empty particles array, so that when a new thread starts it clears current particle state:
		particles = new ArrayList<BasicParticle>();
		double r=.2;
		boolean improvedEuler = false;
		particles.add(new BasicParticle(r,r,1.5,3, r,improvedEuler, Color.GREEN));
		CreateTarget();
	}
	public BasicPhysicsEngine(double vx, double vy) {
		particles = new ArrayList<BasicParticle>();
		// empty particles array, so that when a new thread starts it clears current particle state:
		particles = new ArrayList<BasicParticle>();
		double r=.2;
		boolean improvedEuler = false;
		particles.add(new BasicParticle(r,r,vx,vy, r,improvedEuler, Color.GREEN));
		CreateTarget();
	}
	public static void main(String[] args) throws Exception {
		final BasicPhysicsEngine game = new BasicPhysicsEngine();
		final BasicView view = new BasicView(game);
		new JEasyFrame(view, "Basic Physics Engine");
		while (true) {
			for (int i=0;i<BasicPhysicsEngine.NUM_EULER_UPDATES_PER_SCREEN_REFRESH;i++) {
				game.update();
			}
			view.repaint();
		
			try {
				Thread.sleep(BasicPhysicsEngine.DELAY);
			} catch (InterruptedException e) {
			}
		}
	}
	public void update() {
		for (BasicParticle p : particles) {
			ballPosition = p.pos;
			if(ballPosition.y < 0.0) break;
			if (CheckCollision(p))
			{
				System.out.println("Collide");
				target = null;
				rand = new Random().nextInt(rightLimit);
				CreateTarget();
				collide = true;
				break;
			}
			p.update(); // tell each particle to move
		}
	}

	public void CreateTarget() {
		if(targetPosX <= 0)
			targetPosX = leftLimit + rand;
		target = new Rectangle(targetPosX,targetPosY,targetWidth,targetHeight);
	}

	private boolean CheckCollision(BasicParticle particle) {

		double closestX = Clamp(particle.getX(), target.x, target.x + target.width);
		double closestY = Clamp(particle.getY(), target.y - target.height, target.y);

		double distanceX = particle.getX() - closestX;
		double distanceY = particle.getY() - closestY;

		return Math.pow(distanceX, 2) + Math.pow(distanceY, 2) < Math.pow(particle.getRadius(), 2);
	}

	private static double Clamp(double value, double min, double max) {
		double x = value;
		if (x < min) {
			x = min;
		} else if (x > max) {
			x = max;
		}
		return x;
	}
}


