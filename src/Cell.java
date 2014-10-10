/*
 * @author: Jennifer Tran and Karyn Vo
 * This class represents a cell in the WumpusMap class.
 * Each cell has a CellState but initialized as not visited.
 */

public class Cell{
		
		private String staticState;// enum String value("S", "B", "G")
		private int rCell;// store row coordinate
		private int cCell;// store column coordinate
		private boolean visited;
		
		// Constructor
		public Cell(CellState cellState){
			setState(cellState);
			visited = false;
		}
		
		// Sets the static state (not revealed until cell becomes visited)
		public void setState(CellState cellState){
			staticState = cellState.getValue();
		}
		
		// Sets the row coordinate of the Cell
		public void setRCell(int rCell){
			this.rCell = rCell;
		}
		
		// Sets if the Cell is visited
		public void setVisited(boolean x){
			visited = x;
		}
		
		// Returns if the Cell is visited
		public boolean getVisited(){
			return visited;
		}
		
		// Sets the column coordinate of the Cell
		public void setCCell(int cCell){
			this.cCell = cCell;
		}
		
		// Returns the String representation of the static state (enum constant value)
		public String getStaticState(){
			return staticState;
		}
	}
