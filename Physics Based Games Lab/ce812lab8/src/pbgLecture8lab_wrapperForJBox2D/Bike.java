package pbgLecture8lab_wrapperForJBox2D;

import java.awt.*;

import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.RevoluteJointDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.MathUtils;
import org.jbox2d.dynamics.joints.WheelJointDef;

public class Bike {

    protected final BasicRectangle bikeBody;
    protected final BasicWheel wheel1;
    protected final BasicWheel wheel2;
    //private final BasicRectangle bikeHandle;

    public Bike (float sx, float sy, float vx, float vy, Color col, float mass, float rollingFriction, float height, float width, float radius) {
        World w = BasicPhysicsEngineUsingBox2D.world; // a Box2D object

        // Bike
        bikeBody = new BasicRectangle(sx, sy, 0f, 0f, Color.GREEN, 2f, rollingFriction, width, height, false);

        // Bike
        //bikeHandle = new BasicRectangle(sx + 0.5f, sy + (height / 2f), 0f, 0f, Color.RED, 1f, rollingFriction, 0.25f, 2f, true);

        // Wheel1
        wheel1 = new BasicWheel(sx - width/2, sy - (height / 2f), radius, rollingFriction, 5f, Color.BLUE);

        // Wheel2
        wheel2 = new BasicWheel(sx + width/2, sy - (height / 2f), radius, rollingFriction, 5f, Color.ORANGE);

        // Body Joint
       /* RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.bodyA = bikeBody.body;
        jointDef.bodyB = bikeHandle.body;
        jointDef.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
        jointDef.localAnchorA = new Vec2( 0.5f , 0f);
        jointDef.localAnchorB = new Vec2(0f, -2f/2);
        jointDef.enableMotor = false;
        jointDef.motorSpeed = MathUtils.PI / 2;
        w.createJoint(jointDef);*/

        // Wheel1 Joint
        RevoluteJointDef jointDef1 = new RevoluteJointDef();
        jointDef1.bodyA = bikeBody.body;
        jointDef1.bodyB = wheel1.body;
        jointDef1.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
        jointDef1.localAnchorA = new Vec2( -width /2f, 0);
        jointDef1.localAnchorB = new Vec2(0f, 0f);
        jointDef1.enableMotor = true;
        jointDef1.motorSpeed = MathUtils.PI / 2;
        w.createJoint(jointDef1);

        // Wheel2 Joint
        RevoluteJointDef jointDef2 = new RevoluteJointDef();
        jointDef2.bodyA = bikeBody.body;
        jointDef2.bodyB = wheel2.body;
        jointDef2.collideConnected = false;  // this means we don't want these two connected bodies to have collision detection.
        jointDef2.localAnchorA = new Vec2(width/2f,0);
        jointDef2.localAnchorB = new Vec2(0f, 0f);
        jointDef2.enableMotor = true;
        jointDef2.motorSpeed = MathUtils.PI / 2;
        w.createJoint(jointDef2);
    }
    public void draw(Graphics2D g) {
        bikeBody.draw(g);
        wheel1.draw(g);
        wheel2.draw(g);
    }

    public void notificationOfNewTimestep() {
        if (BasicKeyListener.isDecelerateKeyPressed()) {
            //bikeBody.body.applyForce(new Vec2(-20, 0), new Vec2(0, 0));
            wheel1.body.applyTorque(250f);
            bikeBody.body.applyTorque(-200f);
            bikeBody.body.applyForceToCenter(new Vec2(100f, 0));
            //wheel2.body.applyTorque(500f);
        } else if (BasicKeyListener.isAccelerateKeyPressed()) {
            //bikeBody.body.applyForce(new Vec2(20, 0), new Vec2(0, 0));
            wheel1.body.applyTorque(-250f);
            bikeBody.body.applyTorque(200f);
            bikeBody.body.applyForceToCenter(new Vec2(100f, 0));
            //wheel2.body.applyTorque(-250f);
        }
    }
}
