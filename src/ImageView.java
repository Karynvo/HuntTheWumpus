/* @author: Karyn Vo and Jennifer Tran
 * This class observes the Observable WumpusMap to create the graphical view of the game.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageView extends JPanel implements Observer {
	private BufferedImage hunterImg;
	private BufferedImage secondLayer;
	private BufferedImage ground;
	private WumpusMap map;
	private boolean gameOver;

	// Constructor
	public ImageView(WumpusMap map) {
		super();
		this.map = map;
		try {
			ground = ImageIO.read(new File("Images/Ground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameOver = false;
	}

	// Updates the image of the map when there's a change.
	public void update(Observable observed, Object unused) {
		map = (WumpusMap) observed;
		gameOver = map.getGameOver();
		repaint();
	}

	// Paints the map of the game.
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// first layer of ground
		if (ground != null) {
			for (int r = 0; r < map.getRows(); r++) {
				for (int c = 0; c < map.getColumns(); c++) {
					g2.drawImage(ground, (c * 50) + 70, r * 50, 50, 50, null);
				}
			}
		}

		// second layer of static states of the cells
		for (int r = 0; r < map.getRows(); r++) {
			for (int c = 0; c < map.getColumns(); c++) {
				String imageStatic = map.getMap()[r][c].getStaticState();
				String imageFile = "";
				if (imageStatic.equals("S") || imageStatic.equals("P")
						|| imageStatic.equals("B") || imageStatic.equals("W")
						|| imageStatic.equals("G")) {
					if (imageStatic.equals("S"))
						imageFile = "Images/Slime.png";
					else if (imageStatic.equals("P"))
						imageFile = "Images/SlimePit.png";
					else if (imageStatic.equals("B"))
						imageFile = "Images/Blood.png";
					else if (imageStatic.equals("G"))
						imageFile = "Images/Goop.png";
					else if (imageStatic.equals("W"))
						imageFile = "Images/Wumpus.png";
					try {
						secondLayer = ImageIO.read(new File(imageFile));
					} catch (IOException e) {
						e.printStackTrace();
					}
					g2.drawImage(secondLayer, (c * 50) + 70, r * 50, 50, 50,
							null);
				}
			}
		}

		// third layer of the current state of the cells
		for (int r = 0; r < map.getRows(); r++) {
			for (int c = 0; c < map.getColumns(); c++) {
				String imageState = map.getState(r, c);
				if (imageState.equals("O") || imageState.equals("X")) {
					// draw the Hunter
					if (imageState.equals("O")) {
						try {
							hunterImg = ImageIO.read(new File(
									"Images/TheHunter.png"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						g2.drawImage(hunterImg, (c * 50) + 70, r * 50, 50, 50,
								null);
					}
					//if gameOver is true, don't show black squares
					else if (!gameOver) {
						g2.setColor(Color.BLACK);
						g2.fillRect((c * 50) + 70, r * 50, 50, 50);
					}
				}
			}
		}
	}
}