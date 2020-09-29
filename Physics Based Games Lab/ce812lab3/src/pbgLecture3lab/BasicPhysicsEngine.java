package pbgLecture3lab;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;


public class BasicPhysicsEngine {
	/* Author: Michael Fairbank
	 * Creation Date: 2016-01-28
	 * Significant changes applied:
	 */
	
	// frame dimensions
	public static final int SCREEN_HEIGHT = 680;
	public static final int SCREEN_WIDTH = 480;
	public static final Dimension FRAME_SIZE = new Dimension(
			SCREEN_WIDTH, SCREEN_HEIGHT);
	public static final double WORLD_WIDTH=10;//metres
	public static final double WORLD_HEIGHT=SCREEN_HEIGHT*(WORLD_WIDTH/SCREEN_WIDTH);// meters - keeps world dimensions in same aspect ratio as screen dimensions, so that circles get transformed into circles as opposed to ovals
	public static final double GRAVITY=0;

	// sleep time between two drawn frames in milliseconds 
	public static final int DELAY = 20;
	public static final int NUM_EULER_UPDATES_PER_SCREEN_REFRESH=10;
	// estimate for time between two frames in seconds 
	public static final double DELTA_T = DELAY / 1000.0 / NUM_EULER_UPDATES_PER_SCREEN_REFRESH ;

	private static BasicParticle cueBall;
	public static BasicMouseListener basicMouseListener;
	//public MousePointer mousePointer;



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
	public static double convertScreenXtoWorldX(int screenX) {
		// to get this to work you need to program the inverse function to convertWorldXtoScreenX
		// this means rearranging the equation z=(worldX/WORLD_WIDTH*SCREEN_WIDTH) to make worldX the subject, 
		// and then returning worldX
		 return (double) screenX*WORLD_WIDTH/SCREEN_WIDTH;
	}
	public static double convertScreenYtoWorldY(int screenY) {
		// to get this to work you need to program the inverse function to convertWorldYtoScreenY
		// this means rearranging the equation z= (SCREEN_HEIGHT-(worldY/WORLD_HEIGHT*SCREEN_HEIGHT)) to make 
		// worldY the subject, and then returning worldY
		return (double) (SCREEN_HEIGHT-screenY)*WORLD_WIDTH/SCREEN_WIDTH;
	}
	
	
	
	public List<BasicParticle> particles;
	public List<AnchoredBarrier> barriers;

	List<BasicParticle> outBall;
	int score = 0;

	public Vect2D GetMousePosition() {
		return basicMouseListener.getWorldCoordinatesOfMousePointer();
	}

	public BasicParticle GetCueBall() {
		return cueBall;
	}

	public void HitCueBall()
	{
		Vect2D mousePointer = GetMousePosition();
		Vect2D  ball = cueBall.getPos();
		Vect2D diff = Vect2D.minus(ball,mousePointer);
		Vect2D temp = diff.normalise();
		double kFactor = 5;
		double finalCoff = kFactor*diff.mag();
	 	Vect2D vel = temp.mult(finalCoff);
		cueBall.setVel(vel);
	}

	public static enum LayoutMode {CONVEX_ARENA, CONCAVE_ARENA, CONVEX_ARENA_WITH_CURVE, PINBALL_ARENA, RECTANGLE, SNOOKER_TABLE};
	public BasicPhysicsEngine() {
		particles = new ArrayList<BasicParticle>();
		barriers = new ArrayList<AnchoredBarrier>();
		// empty particles array, so that when a new thread starts it clears current particle state:
		particles = new ArrayList<BasicParticle>();
		outBall = new ArrayList<BasicParticle>();
		LayoutMode layout=LayoutMode.SNOOKER_TABLE;
		double r=0.15;

		for(int i=0;i<5;i++) {
			for (int j = 0; j <= i; j++) {
				double distance = 0.4;
				double minus = (i / 2.0f) * distance;
				double x = WORLD_WIDTH/1.75 - 2 - minus + j * distance;
				double y = 10 + i * distance;
				BasicParticle a = new BasicParticle(x, y, 0, 0, r, true, Color.RED, 1);
				particles.add(a);
			}
		}

		cueBall = new BasicParticle(WORLD_WIDTH/1.75-2,WORLD_HEIGHT/5,0,0, r,true, Color.WHITE, 1);
		particles.add(cueBall);
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
				barriers.add(new AnchoredBarrier_StraightLine(0, WORLD_HEIGHT*2/3, WORLD_WIDTH/2, WORLD_HEIGHT*1/2, Color.YELLOW,width/10));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/2, WORLD_HEIGHT*1/2, WORLD_WIDTH/2, WORLD_HEIGHT*1/2-width, Color.WHITE,width/10));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/2, WORLD_HEIGHT*1/2-width, 0, WORLD_HEIGHT*2/3-width, Color.YELLOW,width/10));
				barriers.add(new AnchoredBarrier_Point(WORLD_WIDTH/2, WORLD_HEIGHT*1/2));
				barriers.add(new AnchoredBarrier_Point(WORLD_WIDTH/2, WORLD_HEIGHT*1/2-width));
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
				barriers.add(new AnchoredBarrier_StraightLine(0, 0, WORLD_WIDTH, 0, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT, 0, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(0, WORLD_HEIGHT, 0, 0, Color.WHITE));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH/2, WORLD_HEIGHT-WORLD_WIDTH/2, WORLD_WIDTH/2, 0.0, 200.0,true, Color.WHITE));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH/2, WORLD_HEIGHT*3/4, WORLD_WIDTH/15, -0.0, 360.0,false, Color.WHITE));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH*1/3, WORLD_HEIGHT*1/2, WORLD_WIDTH/15, -0.0, 360.0,false, Color.WHITE));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH*2/3, WORLD_HEIGHT*1/2, WORLD_WIDTH/15, -0.0, 360.0,false, Color.WHITE));
				break;
			}
			case SNOOKER_TABLE: {
				double snookerTableHeight=WORLD_HEIGHT;
				double pocketSize=0.5;
				double cushionDepth=0.3;
				double cushionLength = snookerTableHeight/2-pocketSize-cushionDepth;
				double snookerTableWidth=cushionLength+cushionDepth*2+pocketSize*2;
				
				createCushion(barriers, snookerTableWidth-cushionDepth/2, snookerTableHeight*0.25,0, cushionLength, cushionDepth); 
				createCushion(barriers, snookerTableWidth-cushionDepth/2, snookerTableHeight*0.75,0, cushionLength, cushionDepth); 
				createCushion(barriers, snookerTableWidth/2, snookerTableHeight-cushionDepth/2, Math.PI/2, cushionLength, cushionDepth); 
				createCushion(barriers, cushionDepth/2, snookerTableHeight*0.25,Math.PI, cushionLength, cushionDepth); 
				createCushion(barriers, cushionDepth/2, snookerTableHeight*0.75,Math.PI, cushionLength, cushionDepth); 
				createCushion(barriers, snookerTableWidth/2, cushionDepth/2, Math.PI*3/2, cushionLength, cushionDepth);
				//createCurve();
				break;
			}
		}
			
			
	}

	private void createCushion(List<AnchoredBarrier> barriers, double centrex, double centrey, double orientation, double cushionLength, double cushionDepth) {
		// on entry, we require centrex,centrey to be the centre of the rectangle that contains the cushion.
		Color col=Color.BLACK;
		Vect2D p1=new Vect2D(cushionDepth/2, -cushionLength/2-cushionDepth/2);
		Vect2D p2=new Vect2D(-cushionDepth/2, -cushionLength/2);
		Vect2D p3=new Vect2D(-cushionDepth/2, +cushionLength/2);
		Vect2D p4=new Vect2D(cushionDepth/2, cushionLength/2+cushionDepth/2);
		p1=p1.rotate(orientation);
		p2=p2.rotate(orientation);
		p3=p3.rotate(orientation);
		p4=p4.rotate(orientation);
		// we are being careful here to list edges in an anticlockwise manner, so that normals point inwards!
		AnchoredBarrier_StraightLine red =  new AnchoredBarrier_StraightLine(centrex+p1.x, centrey+p1.y, centrex+p2.x, centrey+p2.y, Color.RED);
		barriers.add(red);
		barriers.add(new AnchoredBarrier_StraightLine(centrex+p2.x, centrey+p2.y, centrex+p3.x, centrey+p3.y, col));
		AnchoredBarrier_StraightLine yellow = new AnchoredBarrier_StraightLine(centrex+p3.x, centrey+p3.y, centrex+p4.x, centrey+p4.y, col.YELLOW);
		barriers.add(yellow);
		//barriers.add(new AnchoredBarrier_StraightLine(centrex+p3.x, centrey+p3.y, centrex+p4.x, centrey+p4.y, col.YELLOW));
		// oops this will have concave corners so will need to fix that some time! 
	}
	
	public static void main(String[] args) throws Exception {
		final BasicPhysicsEngine game = new BasicPhysicsEngine();
		final BasicView view = new BasicView(game);
		basicMouseListener =  new BasicMouseListener(game);
		JEasyFrame frame = new JEasyFrame(view, "Basic Physics Engine");
		view.addMouseMotionListener(basicMouseListener);
		view.addMouseListener(basicMouseListener);
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
			p.update();// tell each particle to move

			if(!p.equals(particles.get(particles.size()-1))) {
				if (p.getPos().x <= 0 || p.getPos().x >= WORLD_WIDTH || p.getPos().y <= 0 || p.getPos().y >= WORLD_HEIGHT) {
					if(!outBall.contains(p)) {
						System.out.println("OUT");
						outBall.add(p);
						score++;
					}
				}
			}
		}

		for (BasicParticle particle : particles) {
			for (AnchoredBarrier b : barriers) {
				if (b.isCircleCollidingBarrier(particle.getPos(), particle.getRadius())) {
					Vect2D bouncedVel=b.calculateVelocityAfterACollision(particle.getPos(), particle.getVel());
					particle.setVel(bouncedVel);
				}
			}
		}
		double e=0.95; // coefficient of restitution for all particle pairs
		for (int n=0;n<particles.size();n++) {
			for (int m=0;m<n;m++) {// avoids double check by requiring m<n
				BasicParticle p1 = particles.get(n);
				BasicParticle p2 = particles.get(m);
				if (p1.collidesWith(p2)) {
					BasicParticle.implementElasticCollision(p1, p2, e);
				}
			}
		}
	}

	public int getScore(){return score;}
	
	
	

}


