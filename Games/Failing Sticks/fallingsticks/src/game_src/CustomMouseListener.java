package game_src;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.MouseInputAdapter;

import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;

/**
 * Created by Dinesh on 22/03/2020
 */
public class CustomMouseListener extends MouseInputAdapter implements MouseListener {

    private static int mouseX, mouseY;
    private Box2DPhysicsEngine physicsEngine;

    public CustomMouseListener(Box2DPhysicsEngine engine) {
        this.physicsEngine = engine;
    }

    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        Vec2 worldCoordinatesOfMousePointer = getWorldCoordinatesOfMousePointer();
        if (Box2DPhysicsEngine.ALLOW_MOUSE_POINTER_TO_CLICK_BODIES_ON_SCREEN) {
            Body bodyUnderMousePointer = findBodyAtWorldCoords(worldCoordinatesOfMousePointer);
            if (bodyUnderMousePointer != null) {
                ParticleInfo particleInfo = (ParticleInfo) bodyUnderMousePointer.getUserData();
                // the click is only applicable for semi static particle type and change the body to dynamic
                if (particleInfo.getParticleType() == GameInfo.ParticleType.SEMI_STATIC) {
                    physicsEngine.playClick();
                    physicsEngine.changeBodyType(particleInfo.getId());
                }
            }
        }
    }

    public static Vec2 getWorldCoordinatesOfMousePointer() {
        return new Vec2(Box2DPhysicsEngine.convertScreenXtoWorldX(mouseX), Box2DPhysicsEngine.convertScreenYtoWorldY(mouseY));
    }

    private static final AABB queryAABB = new AABB();  // This is an axis aligned bounding box (AABB)
    private static final TestQueryCallback callback = new TestQueryCallback();

    public static Body findBodyAtWorldCoords(Vec2 worldCoords) {
        // Set up a tiny axis aligned bounding box around the tiny area of screen around the mouse pointer:
        queryAABB.lowerBound.set(worldCoords.x - .001f, worldCoords.y - .001f);
        queryAABB.upperBound.set(worldCoords.x + .001f, worldCoords.y + .001f);
        callback.point.set(worldCoords);
        callback.fixture = null;
        // Now ask the world object which bodies are positioned at the point of the screen we are interested in:
        Box2DPhysicsEngine.world.queryAABB(callback, queryAABB);
        if (callback.fixture != null) {
            Body body = callback.fixture.getBody();
            return body;
        } else
            return null;
    }

    private static class TestQueryCallback implements QueryCallback {
        // This is a callback class we need to use when we are querying which objects lie
        // under the point of the screen that we are interested in?
        public final Vec2 point;
        public Fixture fixture;

        public TestQueryCallback() {
            point = new Vec2();
            fixture = null;
        }

        @Override
        public boolean reportFixture(Fixture argFixture) {
            Body body = argFixture.getBody();
            // return fixture of the static body
            if (body.getType() == BodyType.STATIC) {
                boolean inside = argFixture.testPoint(point);
                if (inside) {
                    fixture = argFixture;
                    return false;
                }
            }
            return true;
        }
    }
}
