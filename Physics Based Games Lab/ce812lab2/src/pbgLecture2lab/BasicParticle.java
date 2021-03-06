package pbgLecture2lab;

import static pbgLecture2lab.BasicPhysicsEngine.DELTA_T;
import static pbgLecture2lab.BasicPhysicsEngine.GRAVITY;

import java.awt.Color;
import java.awt.Graphics2D;

public class BasicParticle {
	private final int SCREEN_RADIUS;

	private Vect2D pos;
	private Vect2D vel;
	private final double radius;
	private final double mass;
	private final Color col;

	private final boolean improvedEuler;

	

	public BasicParticle(double sx, double sy, double vx, double vy, double radius, boolean improvedEuler, Color col, double mass) {
		setPos(new Vect2D(sx,sy));
		setVel(new Vect2D(vx,vy));
		this.radius=radius;
		this.mass=mass;
		this.improvedEuler=improvedEuler;
		this.SCREEN_RADIUS=Math.max(BasicPhysicsEngine.convertWorldLengthToScreenLength(radius),1);
		this.col=col;
	}

	public void update() {
		Vect2D acc=new Vect2D(0,-GRAVITY);
		if (improvedEuler) {
			Vect2D pos2=getPos().addScaled(getVel(), DELTA_T);// in theory this could be used,e.g. if acc2 depends on pos - but in this constant gravity field it will not be relevant
			Vect2D vel2=getVel().addScaled(acc, DELTA_T);
			Vect2D velAv=vel2.add(getVel()).mult(0.5);
			Vect2D acc2=new Vect2D(0,-GRAVITY);//same as acc in this simple example of constant acceleration, but that won't generally be true
			Vect2D accAv=acc2.add(acc).mult(0.5);
			setPos(getPos().addScaled(velAv, DELTA_T));
			setVel(getVel().addScaled(accAv, DELTA_T));
		} else {
			// basic Euler
			setPos(getPos().addScaled(getVel(), DELTA_T));
			setVel(getVel().addScaled(acc, DELTA_T));
		}
	}

	public void draw(Graphics2D g) {
		int x = BasicPhysicsEngine.convertWorldXtoScreenX(getPos().x);
		int y = BasicPhysicsEngine.convertWorldYtoScreenY(getPos().y);
		g.setColor(col);
		g.fillOval(x - SCREEN_RADIUS, y - SCREEN_RADIUS, 2 * SCREEN_RADIUS, 2 * SCREEN_RADIUS);
	}

	public double getRadius() {
		return radius;
	}

	public Vect2D getPos() {
		return pos;
	}

	public void setPos(Vect2D pos) {
		this.pos = pos;
	}

	public Vect2D getVel() {
		return vel;
	}

	public void setVel(Vect2D vel) {
		this.vel = vel;
	}

}
