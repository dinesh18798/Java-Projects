package pbgLecture1lab;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;


public class ThreadedGuiForPhysicsEngine {
	
	public ThreadedGuiForPhysicsEngine() {
	}

	private static JButton jButton_go;
	private static JTextField jTextField_angle, jTextField_speed;
	private static JLabel x_Pos, y_Pos, max_Height, hor_Range;
	private static JLabel exactHeight_Value, exactRange_Value;
	private static JLabel score_Label;
	private static int score =0;
	private static Thread theThread;
	private static double launch_angle, launch_speed;
	private static double height = 0;
	private static double str_Dist = 0, end_Dist = 0;
	static BasicPhysicsEngine game2;
	public static void main(String[] args) throws Exception {
		BasicPhysicsEngine game = new BasicPhysicsEngine ();
		final BasicView view = new BasicView(game);
		JComponent mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(view, BorderLayout.CENTER);
		JPanel sidePanel=new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		jButton_go=new JButton("Go");
		JPanel anglePanel = new JPanel();
		anglePanel.setSize(new Dimension(100,50));
		anglePanel.setLayout(new FlowLayout());
		JLabel angle_Label= new JLabel("Launch Angle");
		jTextField_angle = new JTextField("0", 5);
		anglePanel.add(angle_Label);
		anglePanel.add(jTextField_angle);
		JPanel speedPanel = new JPanel();
		speedPanel.setSize(new Dimension(100,50));
		speedPanel.setLayout(new FlowLayout());
		JLabel speed_Label= new JLabel("Launch Speed");
		jTextField_speed = new JTextField("0", 5);
		speedPanel.add(speed_Label);
		speedPanel.add(jTextField_speed);

		JPanel exactData = new JPanel();
		exactData.setSize(new Dimension(100,200));
		exactData.setLayout(new BoxLayout(exactData, BoxLayout.Y_AXIS));
		JLabel exact_Title = new JLabel("Exact Data");

		JPanel exactHeight_Panel = new JPanel();
		exactHeight_Panel.setLayout(new FlowLayout());
		JLabel exactHeight_Title = new JLabel("Maximum Height");
		exactHeight_Value = new JLabel(String.format("%.2f m", 0.0));
		exactHeight_Panel.add(exactHeight_Title);
		exactHeight_Panel.add(exactHeight_Value);

		JPanel exactRange_Panel = new JPanel();
		exactRange_Panel.setLayout(new FlowLayout());
		JLabel exactRange_Title = new JLabel("Range");
		exactRange_Value = new JLabel(String.format("%.2f m", 0.0));
		exactRange_Panel.add(exactRange_Title);
		exactRange_Panel.add(exactRange_Value);

		exactData.add(exact_Title);
		exactData.add(exactHeight_Panel);
		exactData.add(exactRange_Panel);

		sidePanel.add(jButton_go);
		sidePanel.add(anglePanel);
		sidePanel.add(speedPanel);
		sidePanel.add(exactData);

		mainPanel.add(sidePanel, BorderLayout.WEST);
		// add any new buttons or textfields to side panel here...


		JComponent topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.add(new JLabel("X: "));
		x_Pos = new JLabel("0");
		topPanel.add(x_Pos);
		topPanel.add(new JLabel("Y: "));
		y_Pos  = new JLabel("0");
		topPanel.add(y_Pos);
		topPanel.add(new JLabel("Maximum Height: "));
		max_Height = new JLabel(String.format("%.2f m", 0.0));
		topPanel.add(max_Height);
		topPanel.add(new JLabel("Range: "));
		hor_Range = new JLabel(String.format("%.2f m", 0.0));
		topPanel.add(hor_Range);
		topPanel.add(new JLabel("Score: "));
		score_Label = new JLabel(String.format("%d", score));
		score_Label.setOpaque(true);
		score_Label.setBackground(Color.CYAN);
		topPanel.add(score_Label);
		mainPanel.add(topPanel, BorderLayout.NORTH);
		
		new JEasyFrame(mainPanel, "Basic Physics Engine");
		
		ActionListener listener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==jButton_go) {
					try {

						try {
							launch_angle = Double.parseDouble(jTextField_angle.getText());
						}catch (Exception a){
						}
						try {
							launch_speed = Double.parseDouble(jTextField_speed.getText());
						}catch (Exception s){
						}

						if(launch_angle >= 0 && launch_speed > 0) {
							double vx = launch_speed *Math.cos(launch_angle/180.0 * Math.PI);
							double vy = launch_speed *Math.sin(launch_angle/180.0 * Math.PI);
							game2 = new BasicPhysicsEngine(vx,vy);
						}
						else {
							// recreate all particles in their original positions:
							game2 = new BasicPhysicsEngine();
						}
						height =0; str_Dist =0;end_Dist=0;
						CalculateExactData();
						// Tell the view object to start displaying this new Physics engine instead:
						view.updateGame(game2);
						// start a new thread for the new game object:
						startThread(game2, view);

					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		};
		jButton_go.addActionListener(listener);
	}
	private static void startThread(final BasicPhysicsEngine game, final BasicView view) throws InterruptedException {
	    Runnable r = new Runnable() {
	         public void run() {
	        	// this while loop will exit any time this method is called for a second time, because 
	    		while (theThread==Thread.currentThread()) {
					for (int i = 0; i < BasicPhysicsEngine.NUM_EULER_UPDATES_PER_SCREEN_REFRESH; i++) {
					 if(!game.collide) game.update();
					}
					if(game.collide) {
						score_Label.setText(String.format("%d", ++score));
						break;
					}
					view.repaint();
					x_Pos.setText(String.format("%.2f", game2.ballPosition.x));
					y_Pos.setText(String.format("%.2f", game2.ballPosition.y));

					if (height < game2.ballPosition.y) {
						height = game2.ballPosition.y;
						max_Height.setText(String.format("%.2f m", height));
					}

					if (end_Dist < game2.ballPosition.x) {
						if(str_Dist == 0) str_Dist = game2.ballPosition.x;
						end_Dist = game2.ballPosition.x;
						double dist = end_Dist -str_Dist;
						hor_Range.setText(String.format("%.2f m", dist));
					}

	    			try {
						Thread.sleep(BasicPhysicsEngine.DELAY);
					} catch (InterruptedException e) {
					}
	    		}
	         }
	     };

	     theThread=new Thread(r);// this will cause any old threads running to self-terminate
	     theThread.start();
	}

	private static void CalculateExactData()
	{
		double vf = 0.0;
		double vi_Up = launch_speed * Math.sin(launch_angle/180.0 * Math.PI);
		double vi_Hor = launch_speed * Math.cos(launch_angle/180.0 * Math.PI);

		double taken_Time = (vf - vi_Up) / (-BasicPhysicsEngine.GRAVITY);
		double average_vel = 0.5 * vi_Up;

		double height = (average_vel * taken_Time) + 0.2;
		double distance = (2 * taken_Time) * vi_Hor;

		exactHeight_Value.setText(String.format("%.2f m",height));
		exactRange_Value.setText(String.format("%.2f m", distance));
	}
}


