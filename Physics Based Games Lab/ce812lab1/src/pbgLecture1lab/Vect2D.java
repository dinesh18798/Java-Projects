package pbgLecture1lab;


public final class Vect2D {
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

	public String toString() {
		return "(" + String.format("%.01f", x) + "," + String.format("%.01f", y)
				+ ")";
	}

	public Vect2D add(Vect2D v) {
		// TODO insert code here to return a new vector that is the sum of this vector plus vector v
		return new Vect2D(this.x+v.x, this.y+v.y);
	}

	// scaled addition - surprisingly useful
	// note: vector subtraction can be expressed as scaled addition with factor
	// (-1)
	public Vect2D addScaled(Vect2D v, double fac) {
		// TODO insert code here to return a new vector equal to "this + v * fac"
		return new Vect2D(this.x+v.x * fac, this.y+v.y * fac);
}

	public Vect2D mult(double fac) {
		// TODO insert code here to return a new vector equal to "this * fac"
		return new Vect2D(this.x  * fac, this.y * fac);
	}

}
