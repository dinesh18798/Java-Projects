package game_src;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

/**
 * Created by Dinesh on 22/03/2020
 */
public class CustomContactListener implements ContactListener {

    private Box2DPhysicsEngine physicsEngine;

    public CustomContactListener(Box2DPhysicsEngine engine) {
        this.physicsEngine = engine;
    }

    // This function get call when the bodies begin to contact
    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if (bodyA != null && bodyB != null) {
            // Particles are collide if both bodies have particle info user data
            if (bodyA.getUserData() instanceof ParticleInfo
                    && bodyB.getUserData() instanceof ParticleInfo) {
                collideParticles(bodyA, bodyB);
            }
            // if any of one of the body user data is string
            // then it must be the stick fall off the screen
            else if (bodyA.getUserData() instanceof String
                    || bodyB.getUserData() instanceof String) {
                collideBoundaryParticle(bodyA, bodyB);
            }
        }
        return;
    }

    @Override
    public void endContact(Contact contact) {
        return;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        return;
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        return;
    }

    private void collideParticles(Body bodyA, Body bodyB) {
        try {
            ParticleInfo bodyAUserData = (ParticleInfo) bodyA.getUserData();
            ParticleInfo bodyBUserData = (ParticleInfo) bodyB.getUserData();

            if (bodyAUserData != null && bodyBUserData != null) {
                // getting the particle type for both bodies
                GameInfo.ParticleType particleAType = bodyAUserData.getParticleType();
                GameInfo.ParticleType particleBType = bodyBUserData.getParticleType();

                // if any one of the body is dynamic and the other either fixed or semi static type
                // then change the body
                if (particleAType == GameInfo.ParticleType.DYNAMIC && (particleBType == GameInfo.ParticleType.FIXED
                        || particleBType == GameInfo.ParticleType.SEMI_STATIC)) {
                    changeBodyType(bodyBUserData);
                }
                if ((particleAType == GameInfo.ParticleType.FIXED || particleAType == GameInfo.ParticleType.SEMI_STATIC)
                        && particleBType == GameInfo.ParticleType.DYNAMIC) {
                    changeBodyType(bodyAUserData);
                }
            }
        } catch (Exception exp) {
            System.out.println("Unable to get user data " + exp.getMessage());
            exp.printStackTrace();
        }
    }

    private void changeBodyType(ParticleInfo particleInfo) {
        // play shatter effect if body is fixed type
        if (particleInfo.getParticleType() == GameInfo.ParticleType.FIXED) physicsEngine.playShatter();
        physicsEngine.changeBodyType(particleInfo.getId());
    }

    private void collideBoundaryParticle(Body bodyA, Body bodyB) {
        try {
            // getting the boundary type
            String boundaryType = bodyA.getUserData() instanceof String ? bodyA.getUserData().toString() :
                    bodyB.getUserData().toString();
            if (boundaryType == "bottom") physicsEngine.loadNextLevel();
        } catch (Exception exp) {
            System.out.println("Not able to load next level");
            exp.printStackTrace();
        }
    }
}
