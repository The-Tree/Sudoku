import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kenny Daily
 *
 */
public class Frame extends JFrame {
	private Dimension panelSize;
	public Dimension buttonSize;
	private JPanel panel;
	private Puzzle puzz;
	private JButton puzzButton;
	private JLabel viewing;
	boolean drawOrig;
	
	public Frame() {
		super("Sudoku");
		
		drawOrig = true;
		
		panelSize = new Dimension(610, 500);
		panel = new JPanel();
		panel.setPreferredSize(panelSize);
		panel.setLayout(null);
		
		viewing = new JLabel("Viewing Original Solution");
		viewing.setSize(150,30);
		viewing.setLocation(450,10);
		
		buttonSize = new Dimension(150, 30);
		puzzButton = new JButton("View Solved Puzzle");
		puzzButton.setSize(buttonSize);
		puzzButton.setLocation(450,50);
		puzzButton.addActionListener(new ButtonListener());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(panelSize);
		setResizable(false);
		
		panel.add(viewing);
		panel.add(puzzButton);
		
		getContentPane().add(panel);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		//filling board with color and a grid
		g.setColor(Color.WHITE);
		g.fillRect(20, 50, 410, 410);
		//latitude
		g.setColor(Color.BLACK);
		g.fillRect(20, 50, 410, 9);
		g.fillRect(20, 95, 410, 5);
		g.fillRect(20, 140, 410, 5);
		g.fillRect(20, 185, 410, 9);
		g.fillRect(20, 230, 410, 5);
		g.fillRect(20, 275, 410, 5);
		g.fillRect(20, 320, 410, 9);
		g.fillRect(20, 365, 410, 5);
		g.fillRect(20, 410, 410, 5);
		g.fillRect(20, 455, 414, 9);
		//longitude
		g.fillRect(20, 50, 9, 410);
		g.fillRect(65, 50, 5, 410);
		g.fillRect(110, 50, 5, 410);
		g.fillRect(155, 50, 9, 410);
		g.fillRect(200, 50, 5, 410);
		g.fillRect(245, 50, 5, 410);
		g.fillRect(290, 50, 9, 410);
		g.fillRect(335, 50, 5, 410);
		g.fillRect(380, 50, 5, 410);
		g.fillRect(425, 50, 9, 410);
		
		//fill in numbers
	}
	
	/**
	 * 
	 */
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			drawOrig = !drawOrig;
			
			if(drawOrig) {
				puzzButton.setText("View Solved Puzzle");
				viewing.setText("Viewing Original Puzzle");
				repaint();
			}
			else {
				puzzButton.setText("View Original Puzzle");
				viewing.setText("Viewing Solved Puzzle");
				repaint();
			}
		}
	}
	
	/**
	 * Starts showing the GUI
	 */
	public void display() {
		pack();
		setVisible(true);
	}
}