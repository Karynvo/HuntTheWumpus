/* @author: Karyn Vo and Jennifer Tran
 * This class creates a button panel for the WumpusPlayer GUI.
 */

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

public class ButtonPanel extends JPanel {
	private WumpusMap map; // the panel to control

	private BasicArrowButton up, down, left, right; // arrow buttons

	// Class for the move button listeners
	private class ButtonMoveListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (map.getGameOver())
				disableButtons();
			else if (arg0.getSource() == up)
				map.move("up");
			else if (arg0.getSource() == down)
				map.move("down");
			else if (arg0.getSource() == left)
				map.move("left");
			else if (arg0.getSource() == right)
				map.move("right");
		}
	}
	
	// Class for the shoot button listeners
	private class ButtonShootListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (map.getGameOver())
				disableButtons();
			else if (arg0.getSource() == up)
				map.shoot("up");
			else if (arg0.getSource() == down)
				map.shoot("down");
			else if (arg0.getSource() == left)
				map.shoot("left");
			else if (arg0.getSource() == right)
				map.shoot("right");
		}
	}
	
	// Constructor to layout the buttons
	public ButtonPanel(WumpusMap map, String buttonType) {
		super();
		this.map = map;

		this.setLayout(new GridLayout(3, 3)); // grid layout 
		this.setPreferredSize(new Dimension(150, 100)); // set size

		// Create the arrow buttons
		up = new BasicArrowButton(SwingConstants.NORTH);
		down = new BasicArrowButton(SwingConstants.SOUTH);
		left = new BasicArrowButton(SwingConstants.WEST);
		right = new BasicArrowButton(SwingConstants.EAST);
		
		up.setFocusable(false);
		down.setFocusable(false);
		left.setFocusable(false);
		right.setFocusable(false);

		// Add each button and label to this panel (and empty panels)
		JLabel moveLabel = new JLabel(" Move");
		JLabel shootLabel = new JLabel("Shoot");

		moveLabel.setFont(new Font("Roboto", Font.BOLD, 16));
		shootLabel.setFont(new Font("Roboto", Font.BOLD, 16));
		
		add(new JPanel());
		add(up);
		add(new JPanel());
		add(left);
		if (buttonType.equals("move"))
			add(moveLabel);
		else
			add(shootLabel);
		add(right);
		add(new JPanel());
		add(down);

		if (buttonType.equals("move"))
			setButtonMove();
		else
			setButtonShoot();
	}

	// Add action listeners to move in the game.
	public void setButtonMove() {
		ActionListener buttonMoveListener = new ButtonMoveListener();
		up.addActionListener(buttonMoveListener);
		down.addActionListener(buttonMoveListener);
		left.addActionListener(buttonMoveListener);
		right.addActionListener(buttonMoveListener);
	}

	// Add action listeners to shoot in the game.
	public void setButtonShoot() {
		ActionListener buttonShootListener = new ButtonShootListener();
		up.addActionListener(buttonShootListener);
		down.addActionListener(buttonShootListener);
		left.addActionListener(buttonShootListener);
		right.addActionListener(buttonShootListener);
	}

	// Disable buttons when the game is over.
	public void disableButtons() {
		up.setEnabled(false);
		down.setEnabled(false);
		left.setEnabled(false);
		right.setEnabled(false);
	}
}
