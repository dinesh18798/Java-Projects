package pbgLecture5lab_wrapperForJBox2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

public class CannonBallLauncher {

    public final int SCREEN_RADIUS;

    private final float rollingFriction,mass;
    public final Color col;
    protected final Body bodyCirle;
    protected  final Body bodySquare;
    //protected  final  RevoluteJointDef jointDef;
    protected BodyDef bodyDefSquare;
    private final float ratioOfScreenScaleToWorldScale;
    private Path2D.Float polygonPath;
    private int numSides;
    private float radius;

    public CannonBallLauncher(float sx, float sy, float vx, float vy, float radius, Color col, float mass, float rollingFriction) {

        World w=BasicPhysicsEngineUsingBox2D.world; // a Box2D object

        //Circle
        BodyDef bodyDefCircle = new BodyDef();  // a Box2D object
        bodyDefCircle.type = BodyType.STATIC; // this says the physics engine is to move it automatically
        bodyDefCircle.position.set(sx + radius, sy);
        bodyDefCircle.linearVelocity.set(vx, vy);

        this.bodyCirle = w.createBody(bodyDefCircle);
        this.radius = radius;
        this.rollingFriction=rollingFriction;
        this.mass=mass;
        this.SCREEN_RADIUS=(int)Math.max(BasicPhysicsEngineUsingBox2D.convertWorldLengthToScreenLength(radius),1);
        this.col=col;
        this.ratioOfScreenScaleToWorldScale=BasicPhysicsEngineUsingBox2D.convertWorldLengthToScreenLength(1);

        CircleShape circleShape = new CircleShape();// This class is from Box2D
        circleShape.m_radius = radius;
        FixtureDef fixtureCircle = new FixtureDef();// This class is from Box2D
        fixtureCircle.shape = circleShape;
        fixtureCircle.density = (float) (mass/(Math.PI*radius*radius));
        fixtureCircle.friction = 0.0f;// this is surface friction;
        fixtureCircle.restitution = 1.0f;

        //Polygon
        bodyDefSquare= new BodyDef();  // a Box2D object
        bodyDefSquare.type = BodyType.STATIC; // this says the physics engine is to move it automatically
        bodyDefSquare.position.set(sx + radius, sy);
        bodyDefSquare.linearVelocity.set(vx, vy);

        this.bodySquare = w.createBody(bodyDefSquare);
        this.numSides = 3;
        this.polygonPath =  BasicPolygon.mkRegularPolygon(this.numSides, 0.5f);

        PolygonShape shape = new PolygonShape();
        //shape.setAsBox(40.0f, 1.0f);
        Vec2[] vertices = BasicPolygon.verticesOfPath2D(polygonPath, numSides);
        shape.set(vertices, numSides);
        FixtureDef fixtureSquare = new FixtureDef();// This class is from Box2D
        fixtureSquare.shape = shape;
        fixtureSquare.density = (float) (mass/((float) numSides)/2f*(radius*radius)*Math.sin(2*Math.PI/numSides));
        fixtureSquare.friction = 0.1f;// this is surface friction;
        fixtureSquare.restitution = 0.5f;

        bodyCirle.createFixture(fixtureCircle);
        bodySquare.createFixture(fixtureSquare);

        //Joint
        /*jointDef=new RevoluteJointDef();
		jointDef.bodyA = bodyCirle;
		jointDef.bodyB = bodySquare;
		jointDef.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
		jointDef.localAnchorA=new Vec2(0.0f,0.3f);
		jointDef.localAnchorB=new Vec2(0.0f,0.0f);
        jointDef.referenceAngle = (float)Math.toRadians(0);
        w.createJoint(jointDef);*/
    }

    public void draw(Graphics2D g) {

        g.setColor(col);

        //Circle
        int x = BasicPhysicsEngineUsingBox2D.convertWorldXtoScreenX(bodyCirle.getPosition().x);
        int y = BasicPhysicsEngineUsingBox2D.convertWorldYtoScreenY(bodyCirle.getPosition().y);
        g.fillOval(x - SCREEN_RADIUS, y - SCREEN_RADIUS, 2 * SCREEN_RADIUS, 2 * SCREEN_RADIUS);

        //Polygon
        g.setColor(Color.WHITE);
        Vec2 position = bodySquare.getPosition();
        float angle = bodySquare.getAngle();
        AffineTransform af = new AffineTransform();
        af.translate(BasicPhysicsEngineUsingBox2D.convertWorldXtoScreenX(position.x +  radius), BasicPhysicsEngineUsingBox2D.convertWorldYtoScreenY(position.y));
        af.scale(ratioOfScreenScaleToWorldScale, -ratioOfScreenScaleToWorldScale);// there is a minus in here because screenworld is flipped upsidedown compared to physics world
        af.rotate(angle);
        Path2D.Float p = new Path2D.Float (polygonPath,af);
        g.fill(p);
    }

    public void notificationOfNewTimestep() {
        if (rollingFriction>0) {
            Vec2 rollingFrictionForce=new Vec2(bodySquare.getLinearVelocity());
            rollingFrictionForce=rollingFrictionForce.mul(-rollingFriction*mass);
            bodySquare.applyForceToCenter(rollingFrictionForce);
        }
    }

    public float getRotation(){
        //System.out.println(body.getTransform().q.getAngle());
        //System.out.println("Angle: " + body.getTransform().q.getAngle());
        return (bodySquare.getAngle());
    }

    public void setRotation(float angle){
        bodySquare.setTransform(bodySquare.getTransform().p, angle);
    }

    public float getRadius(){
        return this.radius;
    }
}
