package game_src;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * Created by Dinesh on 22/03/2020
 */
public class Particle {

    protected final int id;
    protected static Color col;
    protected final Body body;

    private static final float FRICTION = 0.0f;
    private static final float RESTITUTION = 1.0f;
    private ParticleInfo particleInfo;
    private static int screenRadius;
    private static float rollingFriction;
    private static float mass;
    private boolean applyForceChangeType;
    private boolean rightForce;

    public Particle(float posX, float posY, float velX, float velY, float radius, float mass, float rollingFriction,
                    ParticleInfo particleInfo, boolean applyForceChangeType, boolean rightForce) {
        World world = Box2DPhysicsEngine.world;
        this.particleInfo = particleInfo;
        this.id = particleInfo.getId();
        this.rollingFriction = rollingFriction;
        this.mass = mass;
        this.screenRadius = (int) Math.max(Box2DPhysicsEngine.convertWorldLengthToScreenLength(radius), 1);
        this.applyForceChangeType = applyForceChangeType;
        this.rightForce = rightForce;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = particleInfo.getParticleType() != GameInfo.ParticleType.DYNAMIC
                ? BodyType.STATIC : BodyType.DYNAMIC;
        bodyDef.position.set(posX, posY);
        bodyDef.linearVelocity.set(velX, velY);
        bodyDef.setUserData(particleInfo);
        this.body = world.createBody(bodyDef);
        CircleShape circleShape = new CircleShape();
        circleShape.m_radius = radius;
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.isSensor = true;
        fixtureDef.density = (float) (mass / (Math.PI * radius * radius));
        fixtureDef.friction = FRICTION;
        fixtureDef.restitution = RESTITUTION;
        body.createFixture(fixtureDef);
    }

    public void draw(Graphics2D g) {
        updateBodyType();
        col = getColor();
        int x = Box2DPhysicsEngine.convertWorldXtoScreenX(body.getPosition().x);
        int y = Box2DPhysicsEngine.convertWorldYtoScreenY(body.getPosition().y);
        g.setColor(getColor());
        g.fillOval(x - screenRadius, y - screenRadius, 2 * screenRadius, 2 * screenRadius);
    }

    public void notificationOfNewTimestep() {
        if (rollingFriction > 0) {
            Vec2 rollingFrictionForce = new Vec2(body.getLinearVelocity());
            rollingFrictionForce = rollingFrictionForce.mul(-rollingFriction * mass);
            body.applyForceToCenter(rollingFrictionForce);
        }

        if (applyForceChangeType && particleInfo.getParticleType()
                == GameInfo.ParticleType.DYNAMIC) {
            Vec2 rollingFrictionForce = new Vec2(1.0f, 0);
            float friction = rightForce ? -rollingFriction : rollingFriction;
            rollingFrictionForce = rollingFrictionForce.mul(friction * mass);
            body.applyForceToCenter(rollingFrictionForce);
            if (body.getLinearVelocity().x != 0 && body.getLinearVelocity().y != 0)
                applyForceChangeType = !applyForceChangeType;
        }
    }

    private Color getColor() {
        Color col = null;
        switch (particleInfo.getParticleType()) {
            case DYNAMIC:
                col = GameInfo.MOVE_COLOR ;
                break;
            case FIXED:
                col = GameInfo.FIXED_COLOR;
                break;
            case SEMI_STATIC:
                col = GameInfo.SEMI_STATIC_COLOR;
                break;
        }
        return col;
    }

    private void updateBodyType() {
        if (this.body.getType() == BodyType.DYNAMIC) return;

        switch (particleInfo.getParticleType()) {
            case DYNAMIC:
                this.body.setType(BodyType.DYNAMIC);
                break;
            case FIXED:
            case SEMI_STATIC:
            default:
                this.body.setType(BodyType.STATIC);
                break;
        }
    }

    public ParticleInfo getParticleInfo() {
        return particleInfo;
    }
}
