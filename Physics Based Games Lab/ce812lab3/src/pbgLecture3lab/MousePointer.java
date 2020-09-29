package pbgLecture3lab;

import java.awt.*;

public class MousePointer {

    private Vect2D startPos,endPos,unitNormal,unitTangent;
    private final Color col;

    public MousePointer(double startx, double starty, double endx, double endy, Color col) {
        startPos=new Vect2D(startx,starty);
        endPos=new Vect2D(endx,endy);
        this.col=col;
    }

    public void draw(Graphics2D g) {
        int x1 = BasicPhysicsEngine.convertWorldXtoScreenX(startPos.x);
        int y1 = BasicPhysicsEngine.convertWorldYtoScreenY(startPos.y);
        int x2 = BasicPhysicsEngine.convertWorldXtoScreenX(endPos.x);
        int y2 = BasicPhysicsEngine.convertWorldYtoScreenY(endPos.y);
        g.setColor(col);
        g.drawLine(x1, y1, x2, y2);
    }

    public void setStartPos(Vect2D vect2D){ this.startPos = vect2D;}
    public void setStartPos(double x, double y){ this.startPos = new Vect2D(x,y);}
    public void setEndPos(double x, double y){ this.endPos = new Vect2D(x,y);}
    public Vect2D getStartPos(){ return this.startPos;}
    public Vect2D getEndPos(){ return this.endPos;}

}
