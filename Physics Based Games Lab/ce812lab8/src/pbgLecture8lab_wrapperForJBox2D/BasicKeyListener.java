package pbgLecture8lab_wrapperForJBox2D;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BasicKeyListener extends KeyAdapter {
	/* Author: Michael Fairbank
	 * Creation Date: 2016-01-28
	 * Significant changes applied:
	 */
	private static boolean rotateRightKeyPressed =false;
	private static boolean rotateLeftKeyPressed = false;
	private static boolean accelerateKeyPressed;
	private static boolean decelerateKeyPressed;

	public static boolean isRotateRightKeyPressed() {
		return rotateRightKeyPressed;
	}

	public static boolean isRotateLeftKeyPressed() {
		return rotateLeftKeyPressed;
	}

	public static boolean isAccelerateKeyPressed() {
		return accelerateKeyPressed;
	}

	public static boolean isDecelerateKeyPressed() {
		return decelerateKeyPressed;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP:
			accelerateKeyPressed=true;
			break;
			case KeyEvent.VK_DOWN:
				decelerateKeyPressed=true;
				break;
		case KeyEvent.VK_LEFT:
			rotateLeftKeyPressed=true;
			break;
		case KeyEvent.VK_RIGHT:
			rotateRightKeyPressed=true;
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP:
			accelerateKeyPressed=false;
			break;
			case KeyEvent.VK_DOWN:
				decelerateKeyPressed=false;
				break;
		case KeyEvent.VK_LEFT:
			rotateLeftKeyPressed=false;
			break;
		case KeyEvent.VK_RIGHT:
			rotateRightKeyPressed=false;
			break;
		}
	}
}
