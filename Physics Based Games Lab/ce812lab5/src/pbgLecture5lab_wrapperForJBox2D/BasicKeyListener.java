package pbgLecture5lab_wrapperForJBox2D;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BasicKeyListener extends KeyAdapter {
	/* Author: Michael Fairbank
	 * Creation Date: 2016-01-28
	 * Significant changes applied:
	 */
	private static boolean rotateRightKeyPressed, rotateLeftKeyPressed, rotateUpKeyPressed, rotateDownKeyPressed;

	public static boolean isRotateRightKeyPressed() {
		return rotateRightKeyPressed;
	}

	public static boolean isRotateLeftKeyPressed() {
		return rotateLeftKeyPressed;
	}

	public static boolean isRotateUpKeyPressed() {
		return rotateUpKeyPressed;
	}

	public static boolean isRotateDownKeyPressed() {
		return rotateDownKeyPressed;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP:
			rotateUpKeyPressed=true;
			break;
		case KeyEvent.VK_LEFT:
			rotateLeftKeyPressed=true;
			break;
		case KeyEvent.VK_RIGHT:
			rotateRightKeyPressed=true;
			break;
			case KeyEvent.VK_DOWN:
				rotateDownKeyPressed=true;
				break;

		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP:
			rotateUpKeyPressed=false;
			break;
		case KeyEvent.VK_LEFT:
			rotateLeftKeyPressed=false;
			break;
		case KeyEvent.VK_RIGHT:
			rotateRightKeyPressed=false;
			break;
			case KeyEvent.VK_DOWN:
				rotateDownKeyPressed=false;
				break;
		}
	}
}
