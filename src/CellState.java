/*
 * @author: Jennifer Tran and Karyn Vo
 * This enum contains room constants and its associated String value for WumpusMap class.
 */

public enum CellState{

	Wumpus("W"), Pit("P"), Blood("B"), Goop("G"), Slime("S"), Empty(" ");
	
	private String cellState;

	// Constructor
	private CellState(String state) {
		this.cellState = state;
	}

	// Returns the value of the constant.
	public String getValue() {
		return cellState;
	}
}