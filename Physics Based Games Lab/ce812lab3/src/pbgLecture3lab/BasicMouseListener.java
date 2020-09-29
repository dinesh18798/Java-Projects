package pbgLecture3lab;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.MouseInputAdapter;

public class BasicMouseListener extends MouseInputAdapter implements MouseListener {
	/* Author: Michael Fairbank
	 * Creation Date: 2016-01-28
	 * Significant changes applied:
	 */
	private static int mouseX, mouseY;
	private static boolean mouseButtonDraged;
	private static boolean mouseButtonPressed;
	private static boolean mouseButtonReleased;
	private BasicPhysicsEngine game;

	public BasicMouseListener(BasicPhysicsEngine game)
	{
		this.game = game;
	}

	public void mouseMoved(MouseEvent e) {
       mouseX=e.getX();
       mouseY=e.getY();

		if(mouseButtonReleased){
			game.HitCueBall();
		}

		mouseButtonDraged=false;
		mouseButtonReleased = false;

		//System.out.println("Move event: "+mouseX+","+mouseY);
    }
	public void mouseDragged(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		mouseButtonDraged=true;
		mouseButtonReleased = true;
		//System.out.println("Drag event: "+mouseX+","+mouseY);
	}

	public void mouseReleased(MouseEvent e) {
		if(mouseButtonReleased) {
			game.HitCueBall();
			mouseButtonDraged = false;
			mouseButtonReleased = false;
		}
//       System.out.println("Drag event: "+mouseX+","+mouseY);
	}

	public void mouseClicked(MouseEvent e) {
		mouseButtonDraged = true;
		mouseButtonReleased=true;
		//System.out.println("Clicked");
//      System.out.println("Drag event: "+mouseX+","+mouseY);
	}

	public boolean isMouseButtonDragged() { return mouseButtonDraged; }
	public static Vect2D getWorldCoordinatesOfMousePointer() {
		return new Vect2D(BasicPhysicsEngine.convertScreenXtoWorldX(mouseX), BasicPhysicsEngine.convertScreenYtoWorldY(mouseY));
	}

}
