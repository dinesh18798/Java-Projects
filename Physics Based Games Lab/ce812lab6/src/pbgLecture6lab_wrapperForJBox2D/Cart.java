package pbgLecture6lab_wrapperForJBox2D;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.contacts.ContactVelocityConstraint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;

import static pbgLecture6lab_wrapperForJBox2D.BasicPhysicsEngineUsingBox2D.DELTA_T;

public class Cart {

    public final float ratioOfScreenScaleToWorldScale;

    private final float rollingFriction, mass;
    public final Color col;
    protected final BasicRectangle cartBody;
    protected final BasicWheel wheel1;
    protected final BasicWheel wheel2;
    protected final BasicRectangle pole;
    private float height;
    private float width;
    private final int SCREEN_RADIUS;
    private float poleWidth = 0.25f;
    private float poleHeight = 3.0f;

    public Cart(float sx, float sy, float vx, float vy, Color col, float mass, float rollingFriction, float height, float width, float radius) {
        this.rollingFriction = rollingFriction;
        this.mass = mass;
        this.ratioOfScreenScaleToWorldScale = BasicPhysicsEngineUsingBox2D.convertWorldLengthToScreenLength(1);
        this.col = col;
        this.height = height;
        this.width = width;
        this.SCREEN_RADIUS = (int) Math.max(BasicPhysicsEngineUsingBox2D.convertWorldLengthToScreenLength(radius), 1);

        World w = BasicPhysicsEngineUsingBox2D.world; // a Box2D object

        // Cart
        cartBody = new BasicRectangle(sx,sy, 0f, 0f, Color.DARK_GRAY, 5f, rollingFriction, width, height);

        // Wheel1
        wheel1 = new BasicWheel(sx + width * 0.25f, sy - (height / 2f), radius, rollingFriction, 2f, Color.BLUE);

        // Wheel2
        wheel2 = new BasicWheel(sx + width * 0.75f, sy - (height / 2f), radius, rollingFriction, 2f, Color.ORANGE);

        // Pole
        pole = new BasicRectangle(sx, (sy + height / 2f) + poleHeight / 2f, 0f, 0f, Color.RED, 0.1f, rollingFriction, poleWidth, poleHeight);
        pole.body.m_linearDamping = 12;

        // Wheel1 Joint
        RevoluteJointDef jointDef1 = new RevoluteJointDef();
        jointDef1.bodyA = cartBody.body;
        jointDef1.bodyB = wheel1.body;
        jointDef1.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
        jointDef1.localAnchorA = new Vec2(-(width * 0.25f), -(height / 2f));
        jointDef1.localAnchorB = new Vec2(0f, 0f);
        jointDef1.enableMotor = true;
        jointDef1.motorSpeed = MathUtils.PI / 2;
        w.createJoint(jointDef1);

        // Wheel2 Joint
        RevoluteJointDef jointDef2 = new RevoluteJointDef();
        jointDef2.bodyA = cartBody.body;
        jointDef2.bodyB = wheel2.body;
        jointDef2.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
        jointDef2.localAnchorA = new Vec2(width * 0.25f, -(height / 2f));
        jointDef2.localAnchorB = new Vec2(0f, 0f);
        jointDef2.enableMotor = true;
        jointDef2.motorSpeed = MathUtils.PI / 2;
        w.createJoint(jointDef2);

        // Pole Joint
        RevoluteJointDef jointDef3 = new RevoluteJointDef();
        jointDef3.bodyA = cartBody.body;
        jointDef3.bodyB = pole.body;
        jointDef3.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
        jointDef3.localAnchorA = new Vec2(0, height / 2f);
        jointDef3.localAnchorB = new Vec2(0, -(poleHeight / 2f));
        w.createJoint(jointDef3);
    }

    public void draw(Graphics2D g) {
       cartBody.draw(g);
        wheel1.draw(g);
        wheel2.draw(g);
        pole.draw(g);
    }

    public void notificationOfNewTimestep() {
        if (BasicKeyListener.isRotateLeftKeyPressed()) {
            cartBody.body.applyForce(new Vec2(-20, 0), new Vec2(0, 0));
            System.out.println("Left");
        } else if (BasicKeyListener.isRotateRightKeyPressed()) {
            cartBody.body.applyForce(new Vec2(20, 0), new Vec2(0, 0));
            System.out.println("Right");
        }
    }
}
