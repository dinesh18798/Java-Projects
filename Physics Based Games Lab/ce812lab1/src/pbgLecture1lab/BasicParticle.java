package pbgLecture1lab;

import static pbgLecture1lab.BasicPhysicsEngine.DELTA_T;


import java.awt.Color;
import java.awt.Graphics2D;

public class BasicParticle {
	private final int RADIUS_OF_PARTICLE_IN_PIXELS;

	public Vect2D pos, vel;
	
	private final double radius;
	private final Color col;
	private final boolean improvedEuler;
	

	public BasicParticle(double sx, double sy, double vx, double vy, double radius, boolean improvedEuler, Color col) {

		this.pos = new Vect2D(sx,sy);
		this.vel = new Vect2D(vx,vy);
		this.radius=radius;
		this.improvedEuler=improvedEuler;
		this.RADIUS_OF_PARTICLE_IN_PIXELS=Math.max(BasicPhysicsEngine.convertWorldLengthToScreenLength(radius),1);
		this.col=col;
	}

	public void update() {
		if (improvedEuler) {
			// improved Euler
			//TODO
			Vect2D acc=new Vect2D(0,-BasicPhysicsEngine.GRAVITY);
			Vect2D trial_vel = this.vel.addScaled(acc, DELTA_T);
			Vect2D trial_acc = new Vect2D(0,-BasicPhysicsEngine.GRAVITY);

			this.pos =this.pos.addScaled(this.vel.add(trial_vel),0.5 * DELTA_T);
			this.vel =this.vel.addScaled(acc.add(trial_acc),0.5 * DELTA_T);
		} else {
			// basic Euler: TODO extend this to include BasicPhysicsEngine.GRAVITY
			Vect2D acc=new Vect2D(0,-BasicPhysicsEngine.GRAVITY);
			this.pos=this.pos.addScaled(this.vel, DELTA_T);
			this.vel=this.vel.addScaled(acc,DELTA_T);
		}
	}

	public void draw(Graphics2D g) {
		int x = BasicPhysicsEngine.convertWorldXtoScreenX(this.pos.x);
		int y = BasicPhysicsEngine.convertWorldYtoScreenY(this.pos.y);
		g.setColor(col);
		g.fillOval(x - RADIUS_OF_PARTICLE_IN_PIXELS, y - RADIUS_OF_PARTICLE_IN_PIXELS, 2 * RADIUS_OF_PARTICLE_IN_PIXELS, 2 * RADIUS_OF_PARTICLE_IN_PIXELS);
	}

	public double getRadius() {
		return radius;
	}

	public double getX() {
		return this.pos.x;
	}
	public double getY() {
		return this.pos.y;
	}
}
