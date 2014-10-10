/* @author: Karyn Vo and Jennifer Tran
 * This class observes the Observable WumpusMap to create the text view of the game.
*/

import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextView extends JPanel implements Observer {
	private WumpusMap map;
	private String returnString;
	private JTextArea returnText;

	// Constructor
	public TextView(WumpusMap map) {
		returnString = map.toString();
		returnText = new JTextArea(returnString);
		returnText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 21));
		returnText.setLocation(70, 0);
		add(returnText);
	}

	@Override
	public void update(Observable observed, Object unused) {//not sure about this method
		map = (WumpusMap) observed;
		returnString = map.toString();
		returnText.setText(returnString);
	}

}
