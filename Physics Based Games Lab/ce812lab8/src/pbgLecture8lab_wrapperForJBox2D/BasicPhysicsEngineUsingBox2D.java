package pbgLecture8lab_wrapperForJBox2D;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.MouseJoint;


public class BasicPhysicsEngineUsingBox2D {
	/* Author: Michael Fairbank
	 * Creation Date: 2016-02-05 (JBox2d version)
	 * Significant changes applied:
	 */
	
	// frame dimensions
	public static final int SCREEN_HEIGHT = 800;
	public static final int SCREEN_WIDTH = 1200;
	public static final Dimension FRAME_SIZE = new Dimension(
			SCREEN_WIDTH, SCREEN_HEIGHT);
	public static final float WORLD_WIDTH=30;//metres
	public static final float WORLD_HEIGHT=SCREEN_HEIGHT*(WORLD_WIDTH/SCREEN_WIDTH);// meters - keeps world dimensions in same aspect ratio as screen dimensions, so that circles get transformed into circles as opposed to ovals
	public static final float GRAVITY=9.8f;
	public static final boolean ALLOW_MOUSE_POINTER_TO_DRAG_BODIES_ON_SCREEN=false;// There's a load of code in basic mouse listener to process this, if you set it to true

	public static World world; // Box2D container for all bodies and barriers 

	// sleep time between two drawn frames in milliseconds 
	public static final int DELAY = 20;
	public static final int NUM_EULER_UPDATES_PER_SCREEN_REFRESH=10;
	// estimate for time between two frames in seconds 
	public static final float DELTA_T = DELAY / 1000.0f;
	protected static boolean isGameOver = false;
	
	
	public static int convertWorldXtoScreenX(float worldX) {
		return (int) (worldX/WORLD_WIDTH*SCREEN_WIDTH);
	}
	public static int convertWorldYtoScreenY(float worldY) {
		// minus sign in here is because screen coordinates are upside down.
		return (int) (SCREEN_HEIGHT-(worldY/WORLD_HEIGHT*SCREEN_HEIGHT));
	}
	public static float convertWorldLengthToScreenLength(float worldLength) {
		return (worldLength/WORLD_WIDTH*SCREEN_WIDTH);
	}
	public static float convertScreenXtoWorldX(int screenX) {
		return screenX*WORLD_WIDTH/SCREEN_WIDTH;
	}
	public static float convertScreenYtoWorldY(int screenY) {
		return (SCREEN_HEIGHT-screenY)*WORLD_HEIGHT/SCREEN_HEIGHT;
	}
	
	
	
	public List<BasicParticle> particles;
	public List<BasicPolygon> polygons;
	public List<BasicRightAngleTriangle> rightAngleTriangles;
	public List<AnchoredBarrier> barriers;
	public List<ElasticConnector> connectors;
	public static MouseJoint mouseJointDef;

	protected Bike bike = null;
	
	public static enum LayoutMode {CONVEX_ARENA, CONCAVE_ARENA, CONVEX_ARENA_WITH_CURVE, PINBALL_ARENA, RECTANGLE, SNOOKER_TABLE};
	public BasicPhysicsEngineUsingBox2D() {
		world = new World(new Vec2(0, -GRAVITY));// create Box2D container for everything
		world.setContinuousPhysics(true);

		particles = new ArrayList<BasicParticle>();
		polygons = new ArrayList<BasicPolygon>();
		barriers = new ArrayList<AnchoredBarrier>();
		connectors=new ArrayList<ElasticConnector>();
		rightAngleTriangles = new ArrayList<>();
		LayoutMode layout=LayoutMode.RECTANGLE;
		// pinball:
		float rollingFriction=0.1f;
		float r=.5f;
//			rectangles.add(new BasicRectangle(WORLD_WIDTH/2,WORLD_HEIGHT*3/4,  -4,3, r*4, r*8, 0, 5,  false, Color.BLUE, 1,0.5));
//			public BasicRectangle(double sx, double sy, double vx, double vy, double width, double height, double orientation, double angularVeloctiy, boolean improvedEuler, Color col, double mass) {

		float s=1.2f;
		//particles.add(new BasicParticle(WORLD_WIDTH/2-2,WORLD_HEIGHT/2-2.2f,1.5f*s,1.2f*s, r,Color.GREEN, 1, rollingFriction));
		//rightAngleTriangles.add(new BasicRightAngleTriangle(WORLD_WIDTH/3.5f,WORLD_HEIGHT/4f, 0f, 0f, Color.DARK_GRAY, 5f, rollingFriction, 3, 1.5f, true, BasicRightAngleTriangle.Direction.UP));
		//rightAngleTriangles.add(new BasicRightAngleTriangle(WORLD_WIDTH/1.25f,WORLD_HEIGHT/4f, 0f, 0f, Color.DARK_GRAY, 5f, rollingFriction, 3, 1.5f, true, BasicRightAngleTriangle.Direction.DOWN));
		//polygons.add(new BasicPolygon(WORLD_WIDTH/2-2,WORLD_HEIGHT/2+1.4f,-1.5f*s,1.2f*s, r*4,Color.RED, 1, rollingFriction,3));
		//add(new BasicPolygon(WORLD_WIDTH/2-2,WORLD_HEIGHT/2+1.3f,-1.2f*s,1.2f*s, r*2,Color.WHITE, 1, rollingFriction,5));
		bike = new Bike(WORLD_WIDTH/10f,WORLD_HEIGHT/3.75f,1.5f*s,1.2f*s, Color.YELLOW, 1, rollingFriction,0.25f, 2f, r);
		
//		particles.add(new BasicParticle(WORLD_WIDTH/2+2,WORLD_HEIGHT/2+2f,-1.2f*s,-1.4f*s, r,Color.BLUE, 2, 0));
//		particles.add(new BasicParticle(3*r+WORLD_WIDTH/2,WORLD_HEIGHT/2,2,6.7f, r*3,Color.BLUE, 90, 0));
//		particles.add(new BasicParticle(r+WORLD_WIDTH/2,WORLD_HEIGHT/2,3.5f,5.2f, r,Color.RED, 2, 0));
		
//		// Example revolute joint creation:
//		BasicPolygon p1 = polygons.get(0);
//		BasicParticle p2 = particles.get(0);
//		RevoluteJointDef jointDef=new RevoluteJointDef();
//		jointDef.bodyA = p1.body;
//		jointDef.bodyB = p2.body;
//		jointDef.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
//		jointDef.localAnchorA=new Vec2(0.2f,0.2f);
//		jointDef.localAnchorB=new Vec2(-0.2f,-0.2f);
//		world.createJoint(jointDef);
//		

		

		if (false) {
			// spaceship flying under gravity
			particles.add(new ControllableSpaceShip(3*r+WORLD_WIDTH/2+1,WORLD_HEIGHT/2-2,0f,2f, r,true, 2*4));
		} else if (false) {
			// spaceship flying with dangling pendulum
			double springConstant=1000000, springDampingConstant=1000;
			double hookesLawTruncation=0.2;
			particles.add(new ControllableSpaceShip(3*r+WORLD_WIDTH/2+1,WORLD_HEIGHT/2-2,0f,2f, r,true, 2*4));
			particles.add(new BasicParticle(3*r+WORLD_WIDTH/2+1,WORLD_HEIGHT/2-4,-3f,9.7f, r,Color.BLUE, 2*4, rollingFriction));
			connectors.add(new ElasticConnector(particles.get(0), particles.get(1), r*6, springConstant, springDampingConstant, false, Color.WHITE, hookesLawTruncation));
		} else if (false) {
			// Simple pendulum attached under mouse pointer
			rollingFriction=.5f;
			double springConstant=10000, springDampingConstant=10;
			Double hookesLawTruncation=null;
			boolean canGoSlack=false;
			particles.add(new ParticleAttachedToMousePointer(WORLD_WIDTH/2,WORLD_HEIGHT/2,0,0, r, 10000));
			particles.add(new BasicParticle(WORLD_WIDTH/2,WORLD_HEIGHT/2-2,0,0, r,Color.BLUE, 2*4, rollingFriction));
			connectors.add(new ElasticConnector(particles.get(0), particles.get(1), r*10, springConstant,springDampingConstant, canGoSlack, Color.WHITE, hookesLawTruncation));
		} else if (false) {
			// 4 link chain
			rollingFriction=1;
			double springConstant=1000000, springDampingConstant=1000;
			Double hookesLawTruncation=null;//0.2;//null;//0.2;
			particles.add(new ParticleAttachedToMousePointer(WORLD_WIDTH/2,WORLD_HEIGHT/2,0,0, r, 10000));
			particles.add(new BasicParticle(WORLD_WIDTH/2,WORLD_HEIGHT/2-2,0,0, r/2,Color.BLUE, 2*4, rollingFriction));
			connectors.add(new ElasticConnector(particles.get(0), particles.get(1), r*6, springConstant,springDampingConstant, false, Color.WHITE, hookesLawTruncation));
			particles.add(new BasicParticle(WORLD_WIDTH/2,WORLD_HEIGHT/2-4,0,0, r/2,Color.BLUE, 2*4, rollingFriction));
			connectors.add(new ElasticConnector(particles.get(1), particles.get(2), r*6, springConstant,springDampingConstant, false, Color.WHITE, hookesLawTruncation));
			particles.add(new BasicParticle(WORLD_WIDTH/2,WORLD_HEIGHT/2-6,0,0, r/2,Color.BLUE, 2*4, rollingFriction));
			connectors.add(new ElasticConnector(particles.get(2), particles.get(3), r*6, springConstant,springDampingConstant, false, Color.WHITE, hookesLawTruncation));
			particles.add(new BasicParticle(WORLD_WIDTH/2,WORLD_HEIGHT/2-7,0,0, r/2,Color.BLUE, 2*4, rollingFriction));
			connectors.add(new ElasticConnector(particles.get(3), particles.get(4), r*6, springConstant,springDampingConstant, false, Color.WHITE, hookesLawTruncation));
		} else if (false) {
			// rectangle box
			rollingFriction=.1f;
			double springConstant=1000000, springDampingConstant=1000;
			double hookesLawTruncation=0.2;
//				particles.add(new ParticleAttachedToMousePointer(WORLD_WIDTH/2,WORLD_HEIGHT/2,0,0, r/2, true, 10000));				
			particles.add(new BasicParticle(WORLD_WIDTH/2,WORLD_HEIGHT/2-1,24,34, r/2,Color.BLUE, 2*4, rollingFriction));
			particles.add(new BasicParticle(WORLD_WIDTH/2+0.1f,WORLD_HEIGHT/2-2,0f,0f, r/2,Color.BLUE, 2*4, rollingFriction));
			connectors.add(new ElasticConnector(particles.get(0), particles.get(1), r*6, springConstant,springDampingConstant, false, Color.WHITE, hookesLawTruncation));
			particles.add(new BasicParticle(WORLD_WIDTH/2+0.1f,WORLD_HEIGHT/2-4,-14,14, r/2,Color.BLUE, 2*4, rollingFriction));
			connectors.add(new ElasticConnector(particles.get(1), particles.get(2), r*6, springConstant,springDampingConstant, false, Color.WHITE, hookesLawTruncation));
			particles.add(new BasicParticle(WORLD_WIDTH/2,WORLD_HEIGHT/2-6,0,0, r/2,Color.BLUE, 2*4, rollingFriction));
			connectors.add(new ElasticConnector(particles.get(2), particles.get(3), r*6, springConstant,springDampingConstant, false, Color.WHITE, hookesLawTruncation));
			connectors.add(new ElasticConnector(particles.get(3), particles.get(0), r*6, springConstant,springDampingConstant, false, Color.WHITE, hookesLawTruncation));
			connectors.add(new ElasticConnector(particles.get(2), particles.get(0), r*6*Math.sqrt(6), springConstant,springDampingConstant, false, Color.GRAY, hookesLawTruncation));
			connectors.add(new ElasticConnector(particles.get(1), particles.get(3), r*6*Math.sqrt(6), springConstant,springDampingConstant, false, Color.GRAY, hookesLawTruncation));
		}
		
		
		
		if (false) {
			Random x=new Random(3);
			for (int i=0;i<40;i++) {
				particles.add(new BasicParticle((0.5f+0.3f*(x.nextFloat()-.5f))*WORLD_HEIGHT,(0.5f+0.3f*(x.nextFloat()-.5f))*WORLD_WIDTH,0f,0f, r/2,new Color(x.nextFloat(), x.nextFloat(), x.nextFloat()), .2f, rollingFriction));				
			}
		}
		
		//particles.add(new BasicParticle(r,r,5,12, r,false, Color.GRAY, includeInbuiltCollisionDetection));

		
		barriers = new ArrayList<AnchoredBarrier>();
		
		switch (layout) {
			case RECTANGLE: {
				// rectangle walls:
				// anticlockwise listing
				// These would be better created as a JBox2D "chain" type object for efficiency and potentially better collision detection at joints. 
				barriers.add(new AnchoredBarrier_StraightLine(0, 0, WORLD_WIDTH, 0, Color.WHITE));

				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/3.5f,WORLD_HEIGHT/5f, WORLD_WIDTH/2.5f, WORLD_HEIGHT/3.5f, Color.RED));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/2.5f, WORLD_HEIGHT/3.5f, WORLD_WIDTH/2.5f, WORLD_HEIGHT/5, Color.ORANGE));

				barriers.add(new AnchoredBarrier_StraightLine( WORLD_WIDTH/1.5f, WORLD_HEIGHT/3.5f, WORLD_WIDTH/1.25f,WORLD_HEIGHT/5f, Color.RED));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/1.5f, WORLD_HEIGHT/3.5f, WORLD_WIDTH/1.5f, WORLD_HEIGHT/5f, Color.ORANGE));

				barriers.add(new AnchoredBarrier_StraightLine(0,WORLD_HEIGHT/5, WORLD_WIDTH, WORLD_HEIGHT/5, Color.GREEN));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT, 0, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(0, WORLD_HEIGHT, 0, 0, Color.WHITE));
				break;
			}
			case CONVEX_ARENA: {
				// These would be better created as a JBox2D "chain" type object for efficiency and potentially better collision detection at joints. 
				barriers.add(new AnchoredBarrier_StraightLine(0,WORLD_HEIGHT/3, WORLD_WIDTH/2, 0, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/2, 0, WORLD_WIDTH, WORLD_HEIGHT/3, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT/3, WORLD_WIDTH, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT, 0, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(0, WORLD_HEIGHT, 0, WORLD_HEIGHT/3, Color.WHITE));
				break;
			}
			case CONCAVE_ARENA: {
				// These would be better created as a JBox2D "chain" type object for efficiency and potentially better collision detection at joints. 
				barriers.add(new AnchoredBarrier_StraightLine(0f,WORLD_HEIGHT/3, WORLD_WIDTH/2, 0, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/2, 0, WORLD_WIDTH, WORLD_HEIGHT/3, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT/3, WORLD_WIDTH, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT, 0, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(0f, WORLD_HEIGHT, 0, WORLD_HEIGHT/3, Color.WHITE));
				float width=WORLD_HEIGHT/20;
				barriers.add(new AnchoredBarrier_StraightLine(0f, WORLD_HEIGHT*2/3, WORLD_WIDTH/2, WORLD_HEIGHT*1/2, Color.WHITE));
				barriers.add(new AnchoredBarrier_Point(WORLD_WIDTH/2, WORLD_HEIGHT*1/2));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/2, WORLD_HEIGHT*1/2, WORLD_WIDTH/2, WORLD_HEIGHT*1/2-width, Color.WHITE));
				barriers.add(new AnchoredBarrier_Point(WORLD_WIDTH/2, WORLD_HEIGHT*1/2-width));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/2, WORLD_HEIGHT*1/2-width, 0, WORLD_HEIGHT*2/3-width, Color.WHITE));
				break;
			}
			case CONVEX_ARENA_WITH_CURVE: {
				// These would be better created as a JBox2D "chain" type object for efficiency and potentially better collision detection at joints. 
				barriers.add(new AnchoredBarrier_StraightLine(0,WORLD_HEIGHT/3, WORLD_WIDTH/2, 0, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH/2, 0, WORLD_WIDTH, WORLD_HEIGHT/3, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT/3, WORLD_WIDTH, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT, 0, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(0, WORLD_HEIGHT, 0, 0, Color.WHITE));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH/2, WORLD_HEIGHT-WORLD_WIDTH/2, WORLD_WIDTH/2, 0.0f, 180.0f,Color.WHITE));
				break;
			}
			case PINBALL_ARENA: {
				// These would be better created as a JBox2D "chain" type object for efficiency and potentially better collision detection at joints. 
				// simple pinball board
				barriers.add(new AnchoredBarrier_StraightLine(0, 0, WORLD_WIDTH, 0, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(WORLD_WIDTH, WORLD_HEIGHT, 0, WORLD_HEIGHT, Color.WHITE));
				barriers.add(new AnchoredBarrier_StraightLine(0, WORLD_HEIGHT, 0, 0, Color.WHITE));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH/2, WORLD_HEIGHT-WORLD_WIDTH/2, WORLD_WIDTH/2, 0.0f, 200.0f,Color.WHITE));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH/2, WORLD_HEIGHT*3/4, WORLD_WIDTH/15, -0.0f, 360.0f,Color.WHITE));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH*1/3, WORLD_HEIGHT*1/2, WORLD_WIDTH/15, -0.0f, 360.0f,Color.WHITE));
				barriers.add(new AnchoredBarrier_Curve(WORLD_WIDTH*2/3, WORLD_HEIGHT*1/2, WORLD_WIDTH/15, -0.0f, 360.0f,Color.WHITE));
				break;
			}
			case SNOOKER_TABLE: {
				// These would be better created as a JBox2D "chain" type object for efficiency and potentially better collision detection at joints. 
				float snookerTableHeight=WORLD_HEIGHT;
				float pocketSize=0.4f;
				float cushionDepth=0.3f;
				float cushionLength = snookerTableHeight/2-pocketSize-cushionDepth;
				float snookerTableWidth=cushionLength+cushionDepth*2+pocketSize*2;
				
				createCushion(barriers, snookerTableWidth-cushionDepth/2, snookerTableHeight*0.25f,0, cushionLength, cushionDepth); 
				createCushion(barriers, snookerTableWidth-cushionDepth/2, snookerTableHeight*0.75f,0, cushionLength, cushionDepth); 
				createCushion(barriers, snookerTableWidth/2, snookerTableHeight-cushionDepth/2, Math.PI/2, cushionLength, cushionDepth); 
				createCushion(barriers, cushionDepth/2, snookerTableHeight*0.25f,Math.PI, cushionLength, cushionDepth); 
				createCushion(barriers, cushionDepth/2, snookerTableHeight*0.75f,Math.PI, cushionLength, cushionDepth); 
				createCushion(barriers, snookerTableWidth/2, cushionDepth/2, Math.PI*3/2, cushionLength, cushionDepth); 
				
				
				break;
			}
		}
	}
	
	private void createCushion(List<AnchoredBarrier> barriers, float centrex, float centrey, double orientation, float cushionLength, float cushionDepth) {
		// on entry, we require centrex,centrey to be the centre of the rectangle that contains the cushion.
		Color col=Color.WHITE;
		Vec2 p1=new Vec2(cushionDepth/2, -cushionLength/2-cushionDepth/2);
		Vec2 p2=new Vec2(-cushionDepth/2, -cushionLength/2);
		Vec2 p3=new Vec2(-cushionDepth/2, +cushionLength/2);
		Vec2 p4=new Vec2(cushionDepth/2, cushionLength/2+cushionDepth/2);
		p1=rotateVec(p1,orientation);
		p2=rotateVec(p2,orientation);
		p3=rotateVec(p3,orientation);
		p4=rotateVec(p4,orientation);
		// we are being careful here to list edges in an anticlockwise manner, so that normals point inwards!
		barriers.add(new AnchoredBarrier_StraightLine((float)(centrex+p1.x), (float)(centrey+p1.y), (float)(centrex+p2.x), (float)(centrey+p2.y), col));
		barriers.add(new AnchoredBarrier_StraightLine((float)(centrex+p2.x), (float)(centrey+p2.y), (float)(centrex+p3.x), (float)(centrey+p3.y), col));
		barriers.add(new AnchoredBarrier_StraightLine((float)(centrex+p3.x), (float)(centrey+p3.y), (float)(centrex+p4.x), (float)(centrey+p4.y), col));
		// oops this will have concave corners so will need to fix that some time! 
	}
	private static Vec2 rotateVec(Vec2 v, double angle) {
		// I couldn't find a rotate function in Vec2 so had to write own temporary one here, just for the sake of 
		// cushion rotation for snooker table...
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		float nx = v.x * cos - v.y * sin;
		float ny = v.x * sin + v.y * cos;
		return new Vec2(nx,ny);
	}
	public static void main(String[] args) throws Exception {
		final BasicPhysicsEngineUsingBox2D game = new BasicPhysicsEngineUsingBox2D();
		final BasicView view = new BasicView(game);
		JEasyFrame frame = new JEasyFrame(view, "Basic Physics Engine");
		frame.addKeyListener(new BasicKeyListener());
		view.addMouseMotionListener(new BasicMouseListener());
		game.startThread(view);
	}
	private void startThread(final BasicView view) throws InterruptedException {
		final BasicPhysicsEngineUsingBox2D game=this;
		while (true) {
			game.update();
			view.repaint();

			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
			}
		}
	}
	


	public void update() {
		int VELOCITY_ITERATIONS = NUM_EULER_UPDATES_PER_SCREEN_REFRESH;
		int POSITION_ITERATIONS = NUM_EULER_UPDATES_PER_SCREEN_REFRESH;
		for (BasicParticle p : particles) {
			// give the objects an opportunity to add any bespoke forces, e.g. rolling friction
			p.notificationOfNewTimestep();
		}
		for (BasicPolygon p : polygons) {
			// give the objects an opportunity to add any bespoke forces, e.g. rolling friction
			p.notificationOfNewTimestep();
		}

		if (bike != null) {
			bike.notificationOfNewTimestep();

			world.step(DELTA_T, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		}
	}
	

}


