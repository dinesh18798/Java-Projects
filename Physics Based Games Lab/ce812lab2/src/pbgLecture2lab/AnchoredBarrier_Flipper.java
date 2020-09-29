package pbgLecture2lab;

import java.awt.*;

public class AnchoredBarrier_Flipper extends AnchoredBarrier {

    private Vect2D startPos,endPos,unitNormal,unitTangent;
    private double[] angleArray = {5, 10, 15, 20, 25, 30};
    private final Color col;
    private final double barrierLength;
    private final Double barrierDepth;
    private double energy=0;
    private boolean isMove = false;
    private boolean isRight = false;
    private double lineDist = 0;
    private double angle;


    public AnchoredBarrier_Flipper(double startx, double starty, double endx, double endy, Color col, double e, boolean isright) {
        this(startx, starty, endx, endy, col, null);
        this.isRight = isright;
        this.energy = e;
    }

    public AnchoredBarrier_Flipper(double startx, double starty, double endx, double endy, Color col, Double barrierWidth) {
        startPos=new Vect2D(startx,starty);
        endPos=new Vect2D(endx,endy );

        Vect2D temp=Vect2D.minus(endPos,startPos);
        this.barrierLength=temp.mag();
        temp=temp.normalise();
        setUnitTangent(temp);
        setUnitNormal(getUnitTangent().rotate90degreesAnticlockwise());
        calculateDist();
        this.col=col;
        this.barrierDepth=barrierWidth;
    }

    public void calculateDist() {
        this.lineDist = Math.sqrt(Math.pow(endPos.x - startPos.x, 2) + Math.pow(endPos.y - startPos.y,2));
    }

    public void moveFlipper() {
        this.isMove = !this.isMove;
    }


    public void rotateFlipper(double angle) {
        double pointX, pointY;
        if(!isRight) {
            pointX = startPos.x + lineDist * Math.cos(Math.toRadians(angle));
            pointY = startPos.y + lineDist * Math.sin(Math.toRadians(angle));
            endPos = new Vect2D(pointX,pointY);
            return;
        }
        else {
            pointX = endPos.x - lineDist * Math.cos(Math.toRadians(angle));
            pointY = endPos.y - lineDist * Math.sin(Math.toRadians(angle));
            startPos = new Vect2D(pointX,pointY);
            return;
        }
    }

    @Override
    public Vect2D calculateVelocityAfterACollision(Vect2D pos, Vect2D vel, double e) {
        if(this.energy != 0) e = energy;
        e = 2.0;
        double vParallel=vel.scalarProduct(getUnitTangent());
        double vNormal=vel.scalarProduct(getUnitNormal());
        if (vNormal<0) // assumes normal points AWAY from wall...
            vNormal=-(e * vNormal);
        Vect2D result=getUnitTangent().mult(vParallel);
        result=result.addScaled(getUnitNormal(), vNormal);
        return result;
    }

    @Override
    public boolean isCircleCollidingBarrier(Vect2D circleCentre, double radius) {
        Vect2D ap=Vect2D.minus(circleCentre, startPos);
        double distOnCorrectSideOfBarrierToCentre=ap.scalarProduct(getUnitNormal());
        double distAlongBarrier=ap.scalarProduct(getUnitTangent());
        // Note barrierDepth is type Double declared in constructor.
        // barrierDepth null indicates infinite barrierDepth
        // barrierLength is ||AB||, declared in constructor.
        return distOnCorrectSideOfBarrierToCentre<=radius && (barrierDepth==null || distOnCorrectSideOfBarrierToCentre>=-(barrierDepth+radius))
                && distAlongBarrier>=0 && distAlongBarrier<=barrierLength;
    }

    @Override
    public void draw(Graphics2D g) {
        if(this.isMove) {

            for (int i = 0; i < angleArray.length; i++) {

                this.energy = 2.0;
                this.angle = angleArray[i];

                this.angle = isRight ? -this.angle : this.angle;
                rotateFlipper(this.angle);

                int x1 = BasicPhysicsEngine.convertWorldXtoScreenX(startPos.x);
                int y1 = BasicPhysicsEngine.convertWorldYtoScreenY(startPos.y);
                int x2 = BasicPhysicsEngine.convertWorldXtoScreenX(endPos.x);
                int y2 = BasicPhysicsEngine.convertWorldYtoScreenY(endPos.y);
                g.setColor(col);
                g.drawLine(x1, y1, x2, y2);
            }
            this.isMove = false;
            this.energy = 0.5;
        }
        else rotateFlipper(0);

        int x1 = BasicPhysicsEngine.convertWorldXtoScreenX(startPos.x);
        int y1 = BasicPhysicsEngine.convertWorldYtoScreenY(startPos.y);
        int x2 = BasicPhysicsEngine.convertWorldXtoScreenX(endPos.x);
        int y2 = BasicPhysicsEngine.convertWorldYtoScreenY(endPos.y);
        g.setColor(col);
        g.drawLine(x1, y1, x2, y2);
    }


    public Vect2D getUnitNormal() {
        return unitNormal;
    }

    public void setUnitNormal(Vect2D unitNormal) {
        this.unitNormal = unitNormal;
    }

    public Vect2D getUnitTangent() {
        return unitTangent;
    }

    public void setUnitTangent(Vect2D unitTangent) {
        this.unitTangent = unitTangent;
    }
}
