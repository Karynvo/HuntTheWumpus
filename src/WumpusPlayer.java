/* @author: Karyn Vo and Jennifer Tran
 * This class is the GUI for the Observable WumpusMap.
 * The player can play as the Hunter to hunt the Wumpus.
 * The layout of the GUI is made and the observers are added.
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class WumpusPlayer extends JFrame {
	private JFrame frame;
	private JPanel leftPanel;
	private ButtonPanel shootButtons;
	private ButtonPanel moveButtons;
	private JTabbedPane tabbed;
	private ImageView viewImage;
	private TextView viewText;
	private WumpusMap map;

	public WumpusPlayer() {
		// Composed method
		layoutGUI();
		addObservers();
	}

	// The layout of the graphical components
	private void layoutGUI() {
		
		// create a map
		map = new WumpusMap(10, 10);
		map.setMap();

		// main frame
		frame = new JFrame("Hunt the Wumpus!");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // shuts off the thread when the frame closes
		frame.setLayout(new BorderLayout()); // set layout

		// left side for buttons
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		frame.add(leftPanel, BorderLayout.WEST);

		shootButtons = new ButtonPanel(map, "shoot");
		moveButtons = new ButtonPanel(map, "move");

		moveButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
		shootButtons.setAlignmentX(Component.CENTER_ALIGNMENT);

		leftPanel.add(new JPanel());
		leftPanel.add(moveButtons);
		leftPanel.add(new JPanel());
		leftPanel.add(shootButtons);
		leftPanel.add(new JPanel());

		// center for JTabbedPane of graphical and image views
		tabbed = new JTabbedPane();
		frame.add(tabbed, BorderLayout.CENTER);
		viewImage = new ImageView(map);
		viewText = new TextView(map);
		tabbed.add(viewImage, "Graphical View");
		tabbed.add(viewText, "Text View");
		
		frame.setVisible(true);
	}

	// add observers to Observable map
	private void addObservers() {
		map.addObserver((Observer) viewImage);
		map.addObserver((Observer) viewText);

	}

	public static void main(String[] args) {
		new WumpusPlayer();
	}
}
