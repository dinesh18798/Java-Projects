package pbgLecture6lab_wrapperForJBox2D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import org.jbox2d.collision.shapes.CircleShape;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class BasicWheel {

    public final float ratioOfScreenScaleToWorldScale;

    private final float rollingFriction,mass;
    public final Color col;
    protected final org.jbox2d.dynamics.Body body;
    CircleShape wheelShape;
    Vec2 tangleVec;

    public BasicWheel(float sx, float sy,float radius,float rollingFriction,float mass, Color col)
    {
        this.col=col;
        World w=BasicPhysicsEngineUsingBox2D.world;// a Box2D object
        BodyDef bodyDef = new BodyDef();  // a Box2D object
        bodyDef.type = BodyType.DYNAMIC; // this says the physics engine is to move it automatically
        bodyDef.position.set(sx, sy);
        bodyDef.linearVelocity.set(0, 0);
        bodyDef.angularDamping = 0.1f;
        this.body = w.createBody(bodyDef);

        // wheel
        wheelShape= new CircleShape();
        wheelShape.m_radius = radius;
        wheelShape.m_p.set(0,0);

        tangleVec=new Vec2(radius,0);

        FixtureDef fixtureDefWheel = new FixtureDef();// This class is from Box2D
        fixtureDefWheel.shape = wheelShape;
        fixtureDefWheel.density =(float) (mass/((float) radius*radius*Math.PI));
        fixtureDefWheel.friction = 0.2f;// this is surface friction;
        body.createFixture(fixtureDefWheel);

        this.rollingFriction=rollingFriction;
        this.mass=mass;
        this.ratioOfScreenScaleToWorldScale=BasicPhysicsEngineUsingBox2D.convertWorldLengthToScreenLength(1);


    }
    public Vec2 GetRotateVector(float angle){
        float x=tangleVec.x;
        float y=tangleVec.y;

        float newX=(float)(Math.cos(angle)*x-Math.sin(angle)*y);
        float newY=(float)(Math.sin(angle)*x+Math.cos(angle)*y);
        return new Vec2(newX,newY);
    }

    public void draw(Graphics2D g) {
        g.setColor(col);
        Vec2 position = body.getPosition();
        float angle = body.getAngle();

        AffineTransform af = new AffineTransform();
        af.translate(BasicPhysicsEngineUsingBox2D.convertWorldXtoScreenX(position.x), BasicPhysicsEngineUsingBox2D.convertWorldYtoScreenY(position.y));
        af.scale(ratioOfScreenScaleToWorldScale, -ratioOfScreenScaleToWorldScale);// there is a minus in here because screenworld is flipped upsidedown compared to physics world
        af.rotate(angle);

        int x1 = BasicPhysicsEngineUsingBox2D.convertWorldXtoScreenX(position.x+wheelShape.m_p.x);
        int y1 = BasicPhysicsEngineUsingBox2D.convertWorldYtoScreenY(position.y+wheelShape.m_p.y);
        int radiusInScreenCoordinates=(int)BasicPhysicsEngineUsingBox2D.convertWorldLengthToScreenLength(wheelShape.m_radius);
        g.setColor(col);
        // g.drawArc arguments give dimensions of a rectangle (x,y,width,height) that contains the full ellipse that contains the arc
        g.drawArc(x1-radiusInScreenCoordinates, y1-radiusInScreenCoordinates,
                radiusInScreenCoordinates*2, radiusInScreenCoordinates*2, (int) 0, (int) 360);

        Vec2 point=GetRotateVector(angle);
        int x2 = BasicPhysicsEngineUsingBox2D.convertWorldXtoScreenX(point.x+position.x);
        int y2 = BasicPhysicsEngineUsingBox2D.convertWorldYtoScreenY(point.y+position.y);
        g.setColor(Color.white);
        g.drawLine(x1,y1,x2,y2);

    }

    public void notificationOfNewTimestep() {
        if (rollingFriction>0) {
            Vec2 rollingFrictionForce=new Vec2(body.getLinearVelocity());
            rollingFrictionForce=rollingFrictionForce.mul(-rollingFriction*mass);
            body.applyForceToCenter(rollingFrictionForce);
        }
    }

}
