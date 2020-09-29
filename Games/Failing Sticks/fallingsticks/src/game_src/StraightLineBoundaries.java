package game_src;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * Created by Dinesh on 22/03/2020
 */
public class StraightLineBoundaries {

    private Vec2 startPos, endPos;
    private final Color col;
    public final Body body;

    public StraightLineBoundaries(float startX, float startY, float endX, float endY, Color col, String boundaryType) {

        startPos = new Vec2(startX, startY);
        endPos = new Vec2(endX, endY);
        World world = Box2DPhysicsEngine.world;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.STATIC;
        bodyDef.setUserData(boundaryType);
        bodyDef.position = new Vec2(startX, startY);
        Body body = world.createBody(bodyDef);
        this.body = body;
        Vec2[] vertices = new Vec2[]
                {new Vec2(), new Vec2(endX - startX, endY - startY)};
        ChainShape chainShape = new ChainShape();
        chainShape.createChain(vertices, vertices.length);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = chainShape;
        body.createFixture(fixtureDef);
        this.col = col;
    }

    public void draw(Graphics2D g) {
        int startX = Box2DPhysicsEngine.convertWorldXtoScreenX(startPos.x);
        int startY = Box2DPhysicsEngine.convertWorldYtoScreenY(startPos.y);
        int endX = Box2DPhysicsEngine.convertWorldXtoScreenX(endPos.x);
        int endY = Box2DPhysicsEngine.convertWorldYtoScreenY(endPos.y);
        g.setColor(col);
        g.drawLine(startX, startY, endX, endY);
    }
}
