package game_src;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;

/**
 * Created by Dinesh on 22/03/2020
 */
public class GameFrame extends JFrame {

	public Component comp;

	public GameFrame(Component comp, String title) {
		super(title);
		this.comp = comp;
		getContentPane().add(BorderLayout.CENTER, comp);
		pack();
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		repaint();
	}
}
