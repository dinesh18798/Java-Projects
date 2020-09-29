package pbgLecture6lab_wrapperForJBox2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class BasicRectangle {

    private final float rollingFriction,mass;
    public final Color col;
    protected final org.jbox2d.dynamics.Body body;
    private final Path2D.Float polygonPath;
    public final float ratioOfScreenScaleToWorldScale;

    public BasicRectangle(float sx, float sy, float vx, float vy, Color col, float mass, float rollingFriction, float width, float height) {
        org.jbox2d.dynamics.World w=BasicPhysicsEngineUsingBox2D.world; // a Box2D object
        org.jbox2d.dynamics.BodyDef bodyDef = new org.jbox2d.dynamics.BodyDef();  // a Box2D object
        bodyDef.type = org.jbox2d.dynamics.BodyType.DYNAMIC; // this says the physics engine is to move it automatically
        bodyDef.position.set(sx, sy);
        bodyDef.angularDamping = 0.1f;
        bodyDef.linearVelocity.set(vx, vy);
        this.body = w.createBody(bodyDef);
        org.jbox2d.collision.shapes.PolygonShape shape = new org.jbox2d.collision.shapes.PolygonShape();
        org.jbox2d.common.Vec2[] vertices = verticesOfPath2D(width, height);
        shape.set(vertices, vertices.length);
        org.jbox2d.dynamics.FixtureDef fixtureDef = new org.jbox2d.dynamics.FixtureDef();// This class is from Box2D
        fixtureDef.shape = shape;
        fixtureDef.density = (float) (mass/((float) width * height));
        fixtureDef.friction = 0.1f;// this is surface friction;
        fixtureDef.restitution = 0.2f;
        body.createFixture(fixtureDef);

        this.rollingFriction=rollingFriction;
        this.mass=mass;
        this.ratioOfScreenScaleToWorldScale=BasicPhysicsEngineUsingBox2D.convertWorldLengthToScreenLength(1);
        System.out.println("Screenradius="+ratioOfScreenScaleToWorldScale);
        this.col=col;
        this.polygonPath=polygonPath(vertices);
    }

    public void draw(Graphics2D g) {
        g.setColor(col);
        org.jbox2d.common.Vec2 position = body.getPosition();
        float angle = body.getAngle();
        AffineTransform af = new AffineTransform();
        af.translate(BasicPhysicsEngineUsingBox2D.convertWorldXtoScreenX(position.x), BasicPhysicsEngineUsingBox2D.convertWorldYtoScreenY(position.y));
        af.scale(ratioOfScreenScaleToWorldScale, -ratioOfScreenScaleToWorldScale);// there is a minus in here because screenworld is flipped upsidedown compared to physics world
        af.rotate(angle);
        System.out.println("Angle: " + angle);
        Path2D.Float p = new Path2D.Float (polygonPath,af);
        g.fill(p);
    }

    public void notificationOfNewTimestep() {
        if (rollingFriction>0) {
            org.jbox2d.common.Vec2 rollingFrictionForce=new org.jbox2d.common.Vec2(body.getLinearVelocity());
            rollingFrictionForce=rollingFrictionForce.mul(-rollingFriction*mass);
            body.applyForceToCenter(rollingFrictionForce);
        }
    }

    // Vec2 vertices of Path2D
    public static org.jbox2d.common.Vec2[] verticesOfPath2D(float width, float height) {
        org.jbox2d.common.Vec2[] vertices = new org.jbox2d.common.Vec2[4];
        vertices[0] = new org.jbox2d.common.Vec2(-width /2, -height/2);
        vertices[1] = new org.jbox2d.common.Vec2(width /2, -height/2);
        vertices[2] = new org.jbox2d.common.Vec2(width /2, height/2);
        vertices[3] = new org.jbox2d.common.Vec2(-width /2, height/2);
        return vertices;
    }

    public static Path2D.Float polygonPath(org.jbox2d.common.Vec2[] vec) {
        Path2D.Float p = new Path2D.Float();
        p.moveTo(vec[0].x, vec[0].y);
        for (int i = 0; i < vec.length; i++) {
            p.lineTo(vec[i].x, vec[i].y);
        }
        p.closePath();
        return p;
    }
}
