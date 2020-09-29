package pbgLecture2lab;

import java.io.Serializable;

public final class Vect2D implements Serializable {
	// Based on the Vector2D class, but modified for PBG course to be IMMUTABLE

	public final double x, y;

	// create a null vector
	public Vect2D() {
		this(0, 0);
	}

	// create vector with given coordinates
	public Vect2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// create new vector that is a copy of the argument
	public Vect2D(Vect2D v) {
		this(v.x, v.y);
	}

	public boolean equals(Object o) {
		if (o instanceof Vect2D) {
			Vect2D v = (Vect2D) o;
			return x == v.x && y == v.y;
		} else
			return false;
	}

	public double mag() {
		return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
	}

	public double angle() {
		return Math.atan2(y, x);
	}


	public String toString() {
		return "(" + String.format("%.01f", x) + "," + String.format("%.01f", y)
				+ ")";
	}

	public Vect2D add(Vect2D v) {
		return new Vect2D(this.x+v.x, this.y+v.y);
	}

	// scaled addition - surprisingly useful
	// note: vector subtraction can be expressed as scaled addition with factor
	// (-1)
	public Vect2D addScaled(Vect2D v, double fac) {
		return new Vect2D(this.x + v.x * fac, this.y + v.y * fac);
	}

	public Vect2D mult(double fac) {
		return new Vect2D(this.x * fac,this.y * fac);
	}

	public double scalarProduct(Vect2D v) {
		return ((x * v.x) + (y * v.y));
	}

	public Vect2D normalise() {
		// TODO: return a normalised version of this vector (but don't modify this vector)
		double mag = mag();
		return new Vect2D(x /mag, y/mag);
	}

	public static Vect2D minus(Vect2D v1, Vect2D v2) {
		// returns v1-v2
		return v1.addScaled(v2, -1);
	}

	public Vect2D rotate90degreesAnticlockwise() {
		// Note: this is meant to create a new vector and not modify the current vector
		return new Vect2D(-y,x);
	}

	public Vect2D rotate90degreesClockwise() {
		// Note: this is meant to create a new vector and not modify the current vector
		return new Vect2D(y,-x);
	}
}
