package game_src;

import java.awt.*;

import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.DistanceJointDef;

/**
 * Created by Dinesh on 22/03/2020
 */
public class ParticleConnector {

    private static final float STROKE_WIDTH = 3f;
    private final Particle particle1;
    private final Particle particle2;

    public ParticleConnector(Particle p1, Particle p2) {
        World world = Box2DPhysicsEngine.world;
        this.particle1 = p1;
        this.particle2 = p2;

        DistanceJointDef distanceJoint = new DistanceJointDef();
        distanceJoint.initialize(particle1.body, particle2.body,
                particle1.body.getPosition(), particle2.body.getPosition());
        world.createJoint(distanceJoint);
    }

    public void draw(Graphics2D g) {
        int x1 = Box2DPhysicsEngine.convertWorldXtoScreenX(particle1.body.getPosition().x);
        int y1 = Box2DPhysicsEngine.convertWorldYtoScreenY(particle1.body.getPosition().y);
        int x2 = Box2DPhysicsEngine.convertWorldXtoScreenX(particle2.body.getPosition().x);
        int y2 = Box2DPhysicsEngine.convertWorldYtoScreenY(particle2.body.getPosition().y);
        g.setColor(GameInfo.CONNECTOR_COLOR);
        g.setStroke(new BasicStroke(STROKE_WIDTH));
        g.drawLine(x1, y1, x2, y2);
    }
}
