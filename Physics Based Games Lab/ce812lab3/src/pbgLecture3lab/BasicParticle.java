package pbgLecture3lab;

import static pbgLecture3lab.BasicPhysicsEngine.DELTA_T;
import static pbgLecture3lab.BasicPhysicsEngine.GRAVITY;

import java.awt.*;

public class BasicParticle {
	/* Author: Michael Fairbank
	 * Creation Date: 2016-01-28
	 * Significant changes applied:
	 */
	public final int SCREEN_RADIUS;

	private Vect2D pos;
	private Vect2D vel;
	private final double radius;
	private final double mass;
	public final Color col;
	private boolean cueBall = false;
	private double friction= 0.999;

	private final boolean improvedEuler;

	

	public BasicParticle(double sx, double sy, double vx, double vy, double radius, boolean improvedEuler, Color col, double mass) {
		setPos(new Vect2D(sx,sy));
		setVel(new Vect2D(vx,vy));
		this.radius=radius;
		this.mass=mass;
		this.improvedEuler=improvedEuler;
		this.SCREEN_RADIUS=Math.max(BasicPhysicsEngine.convertWorldLengthToScreenLength(radius),1);
		this.col=col;
		if(this.col == Color.WHITE) cueBall=true;
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
			setVel(getVel().addScaled(accAv, DELTA_T).mult(friction));
		} else {
			// basic Euler
			setPos(getPos().addScaled(getVel(), DELTA_T));
			setVel(getVel().addScaled(acc, DELTA_T));
			double mag=vel.mag();
		}
		if(getVel().mag()<0.1)
			setVel(new Vect2D(0,0));
	}

	public void draw(Graphics2D g) {
		int x = BasicPhysicsEngine.convertWorldXtoScreenX(getPos().x);
		int y = BasicPhysicsEngine.convertWorldYtoScreenY(getPos().y);
		g.setColor(col);
		g.fillOval(x - SCREEN_RADIUS, y - SCREEN_RADIUS, 2 * SCREEN_RADIUS, 2 * SCREEN_RADIUS);
		//g.setStroke(new BasicStroke(0, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
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

	public boolean collidesWith(BasicParticle p2) {
		Vect2D vecFrom1to2 = Vect2D.minus(p2.getPos(), getPos());
		boolean movingTowardsEachOther = Vect2D.minus(p2.getVel(), getVel()).scalarProduct(vecFrom1to2)<0;
		return vecFrom1to2.mag()<getRadius()+p2.getRadius() && movingTowardsEachOther;
	}

	public static void implementElasticCollision(BasicParticle p1, BasicParticle p2, double e) {
		if (!p1.collidesWith(p2)) throw new IllegalArgumentException();

		Vect2D n = Vect2D.minus(p2.pos,p1.pos).normalise();
		double temp = (e+1)*(p1.getVel().scalarProduct(n)-p2.getVel().scalarProduct(n));
		double temp2 = (1/p1.mass) + (1/p2.mass);
		double JB = temp/temp2;

		if(JB<0){
			n=n.mult(-1);
			JB= (e+1)*(n.scalarProduct(p1.vel)-n.scalarProduct(p2.vel))/temp2;
		}
		Vect2D p2FinalSpeed = p2.getVel().addScaled(n,JB/p2.mass);
		Vect2D p1FinalSpeed = Vect2D.minus(p1.getVel(), n.mult(JB/p1.mass));

		p1.setVel(p1FinalSpeed);
		p2.setVel(p2FinalSpeed);
	}
	

}
