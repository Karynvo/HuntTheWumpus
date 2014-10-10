/*
 * @author: Jennifer Tran and Karyn Vo
 * This class creates a map of the Hunt the Wumpus game, randomized and/or non-randomized.
 * This class is the model for the WumpusPlayer GUI.
 * The user can move or shoot in the game.
 * The goal is to kill the Wumpus.
 */

import java.util.Observable;
import java.util.Random;

public class WumpusMap extends Observable{
	private Cell[][] map;
	private int nRows;
	private int nColumns;
	private int rHunter;
	private int cHunter;
	private int rWumpus;
	private int cWumpus;
	private String returnText;
	private boolean gameOver;

	// Random constructor
	public WumpusMap(int rows, int columns) {
		nColumns = columns;
		nRows = rows;
		map = new Cell[nRows][nColumns];
		this.setChanged();
		notifyObservers();
		returnText = "\nWelcome to Hunt the Wumpus!\nYou are in an empty room.\n";
		gameOver = false;
	}

	// Non-random constructor
	public WumpusMap(CellState[][] cells) {
		map = new Cell[cells.length][cells[0].length];
		for (int r = 0; r < cells.length; r++) {
			for (int c = 0; c < cells[0].length; c++) {
				map[r][c] = new Cell(cells[r][c]);
			}
		}
		nColumns = cells[0].length;
		nRows = cells.length;
		returnText = "\nWelcome to Hunt the Wumpus!\nYou are in an empty room.\n";
		gameOver = false;
	}
	
	// Returns copy of random map
	public Cell[][] getMap(){
		Cell[][] copyMap = new Cell[nRows][nColumns];
		for(int r = 0; r < nRows; r++){
			for(int c = 0; c < nColumns; c++){
				copyMap[r][c] = map[r][c];
			}
		}
		return copyMap;
	}
	
	// Returns true if the game is over.
	public boolean getGameOver(){
		return gameOver;
	}
	
	// Returns the number of rows in the map.
	public int getRows() {
		return nRows;
	}
	
	// Returns the number of columns in the map.
	public int getColumns() {
		return nColumns;
	}

	// Returns the current row location of the Hunter.
	public int getRHunter() {
		return rHunter;
	}
	
	// Sets the current row location of the Hunter.
	public void setRHunter(int row) {
		rHunter = row;
	}
	
	// Returns the current column location of the Hunter.
	public int getCHunter() {
		return cHunter;
	}

	// Sets the current column location of the Hunter.
	public void setCHunter(int column) {
		cHunter = column;
	}

	// Sets the row location of the Wumpus.
	public void setRWumpus(int row) {
		rWumpus = row;
	}
	
	// Sets the column location of the Wumpus.
	public void setCWumpus(int column) {
		cWumpus = column;
	}

	/* setMap() and randomize()
	 * 1. set all cells to Empty enum value, have all cells store it's location using
	 * 		setRCell and setCCell
	 * 2. randomize wumpus, set empty field to W
	 * 3. randomize number of pits, randomize locations of pits, set empty fields to P
	 * 4. find location of slime, set empty fields to S
	 * 5. find location of blood, check to see if slime already in location of blood and change to goop
	 * 6. randomize Hunter, store location using private instances variables
	 */

	public void setMap() {
		//1. set all cells to Empty, have cells store its location
		for (int r = 0; r < nRows; r++) {
			for (int c = 0; c < nColumns; c++) {
				map[r][c] = new Cell(CellState.Empty);
				map[r][c].setRCell(r);
				map[r][c].setCCell(c);
			}
		}
		randomize();
		this.setChanged();
	    notifyObservers();
	}

	private void randomize() {
		Random rand = new Random();
		int randomRow = rand.nextInt(nRows);
		int randomColumn = rand.nextInt(nColumns);

		/*
		 * 2. Wumpus
		 * Have wumpus store its coordinates
		 */
		map[randomRow][randomColumn].setState(CellState.Wumpus);
		rWumpus = randomRow;
		cWumpus = randomColumn;

		//3. randomize number of pits between 3-5
		int randomNumberOfPits = rand.nextInt(3) + 3;
		int numOfSetPits = 0;
		while (numOfSetPits != randomNumberOfPits) {
			int randomRowForPit = rand.nextInt(nRows);
			int randomColumnForPit = rand.nextInt(nColumns);

			if (map[randomRowForPit][randomColumnForPit].getStaticState()
					.equals(" ")) {//check to make sure chosen cell coordinates are empty
				map[randomRowForPit][randomColumnForPit]
						.setState(CellState.Pit);//Set Pit
				numOfSetPits++;//if it is, set the random cell to Pit and increment numOfSetPits

				//4. Set Slime
				for (int i = -1; i < 2; i += 2) {
					if (map[wraparoundRow(randomRowForPit + i)][randomColumnForPit]
							.getStaticState().equals(" "))//if empty, set slime
						map[wraparoundRow(randomRowForPit + i)][randomColumnForPit]
								.setState(CellState.Slime);
					if (map[randomRowForPit][wraparoundColumn(randomColumnForPit
							+ i)].getStaticState().equals(" "))//if empty, set slime
						map[randomRowForPit][wraparoundColumn(randomColumnForPit
								+ i)].setState(CellState.Slime);
				}
			}
			//else find diff. coordinates
		}

		//5. Set Blood/Goop
		for (int i = -2; i < 3; i++) {
			if ((map[wraparoundRow(randomRow + i)][randomColumn]
					.getStaticState().equals(" "))
					|| (map[wraparoundRow(randomRow + i)][randomColumn]
							.getStaticState().equals("S"))) {
				if (map[wraparoundRow(randomRow + i)][randomColumn]
						.getStaticState().equals(" "))//if empty set blood
					map[wraparoundRow(randomRow + i)][randomColumn]
							.setState(CellState.Blood);
				else
					//if slime, set goop
					map[wraparoundRow(randomRow + i)][randomColumn]
							.setState(CellState.Goop);
			}
			if (map[randomRow][wraparoundColumn(randomColumn + i)]
					.getStaticState().equals(" ")
					|| map[randomRow][wraparoundColumn(randomColumn + i)]
							.getStaticState().equals("S")) {
				if (map[randomRow][wraparoundColumn(randomColumn + i)]
						.getStaticState().equals(" "))
					map[randomRow][wraparoundColumn(randomColumn + i)]
							.setState(CellState.Blood);
				else
					map[randomRow][wraparoundColumn(randomColumn + i)]
							.setState(CellState.Goop);
			}
			//if neither slime nor empty, must be pit or wumpus. so do not set anything
		}

		//BLOOD CORNERS
		for (int i = -1; i < 2; i += 2) {
			for (int j = -1; j < 2; j += 2) {
				if ((map[wraparoundRow(randomRow + i)][wraparoundColumn(randomColumn
						+ j)].getStaticState().equals(" "))
						|| (map[wraparoundRow(randomRow + i)][wraparoundColumn(randomColumn
								+ j)].getStaticState().equals("S"))) {
					if (map[wraparoundRow(randomRow + i)][wraparoundColumn(randomColumn
							+ j)].getStaticState().equals(" "))//if empty set blood
						map[wraparoundRow(randomRow + i)][wraparoundColumn(randomColumn
								+ j)].setState(CellState.Blood);
					else
						//if slime, set goop
						map[wraparoundRow(randomRow + i)][wraparoundColumn(randomColumn
								+ j)].setState(CellState.Goop);
				}
				//if neither slime nor empty, must be pit or wumpus. so do not set anything
			}
		}

		//6. random location for hunter
		boolean isHunterSet = false;
		while (!isHunterSet) {
			int randomRowForHunter = rand.nextInt(nRows);
			int randomColumnForHunter = rand.nextInt(nColumns);
			if (map[randomRowForHunter][randomColumnForHunter].getStaticState()
					.equals(" ")) {//find a random empty spot for hunter
				rHunter = randomRowForHunter;
				cHunter = randomColumnForHunter;
				isHunterSet = true;
			}
		}
	}

	// Returns if the Hunter is present in the coordinates given.
	private boolean isHunterPresent(int row, int column) {
		return ((row == rHunter) && (column == cHunter));
	}

	// Returns the String representation of the current state of the cell coordinates given.
	public String getState(int row, int column) {//X O or enum value, used for map construction
		if (isHunterPresent(row, column)) {//if hunter present, visited will never be reinitialized to be false
			map[row][column].setVisited(true);
			return "O";
		} else if (map[row][column].getVisited())//if hunter has visited area, but is not present, return state (" " "S" "G")
			return map[row][column].getStaticState();
		return "X";//hunter has not visited and is not present
	}

	// Returns the string representation of the whole map.
	public String toString() {
		if(gameOver)//if gameOver = true
			showMap();
		String result = "";
		for (int r = 0; r < nRows; r++) {
			for (int c = 0; c < nColumns; c++) {
				result += "[" + getState(r, c) + "] ";
			}
			result = result.trim();
			result += "\n";
		}
		result += returnText;
		return result;
	}

	/* The argument is the row you want to change to.
	* Returns a row location applying wraparound, if needed
	*/
	private int wraparoundRow(int row) {
		int tempR = row;
		if (row < 0)
			tempR = nRows + row;
		else if (row > nRows - 1)
			tempR = row - nRows;
		return tempR;
	}

	/* The argument is the column you want to change to.
	* Returns a column location applying wraparound, if needed
	*/
	private int wraparoundColumn(int column) {
		int tempC = column;
		if (column < 0)
			tempC = nColumns + column;
		else if (column > nColumns - 1)
			tempC = column - nColumns;
		return tempC;
	}

	/* Returns true if the user moves into a Wumpus or Pit and dies.
	 * Returns false if the user moves and does not die.
	 * A statement is always printed for the user to know where he/she currently stands.
	 */
	public boolean move(String direction) {
		direction = direction.toLowerCase();
		int tempR = rHunter;
		int tempC = cHunter;
		if (direction.equals("left"))
			tempC -= 1;
		else if (direction.equals("right"))
			tempC += 1;
		else if (direction.equals("up"))
			tempR -= 1;
		else if (direction.equals("down"))
			tempR += 1;
		cHunter = wraparoundColumn(tempC);
		rHunter = wraparoundRow(tempR);
		if (map[rHunter][cHunter].getStaticState().equals("W")) {
			returnText = "\nOh no, you were killed by the Wumpus!\nGame Over.\n";
			gameOver = true;
			this.setChanged();
		    notifyObservers();
			return true;
		} else if (map[rHunter][cHunter].getStaticState().equals("P")) {
			returnText = "\nOh no, you fell into a Pit!\nGame Over.\n";
			gameOver = true;
			this.setChanged();
		    notifyObservers();
			return true;
		} else if (map[rHunter][cHunter].getStaticState().equals("S")) {
			returnText = "\nYou are in Slime.\nA Pit is near!\n";
			this.setChanged();
		    notifyObservers();
		} else if (map[rHunter][cHunter].getStaticState().equals("G")) {
			returnText = "\nYou are in Goop.\nThe Wumpus and a Pit are near!\n";
			this.setChanged();
		    notifyObservers();
		} else if (map[rHunter][cHunter].getStaticState().equals("B")) {
			returnText = "\nYou are in Blood.\nThe Wumpus is near!\n";
			this.setChanged();
		    notifyObservers();
		} else if (map[rHunter][cHunter].getStaticState().equals(" ")) {
			returnText = "\nYou are in an empty room.\nSafe!\n";
			this.setChanged();
		    notifyObservers();
		}
		return false;
	}

	/*
	 * map[rHunter][cHunter] values in the [] are used
	 * arbitrarily to access rWumpus get method.
	 * 
	 * If the row/column of the Hunter is the same as the Wumpus' row/column, the user wins.
	 * A win/lose statement is always printed.
	 */
	public void shoot(String direction) {
		direction = direction.toLowerCase();

		if (direction.equals("left") || direction.equals("right")) {
			if (rHunter == rWumpus)
				returnText = "\nYay, you shot the Wumpus!\nGame Over.\n";
			else
				returnText = "\nOh no, you killed yourself!\nGame Over.\n";
		} else if (direction.equals("up") || direction.equals("down")) {
			if (cHunter == cWumpus)
				returnText = "\nYay, you shot the Wumpus!\nGame Over.\n";
			else
				returnText = "\nOh no, you killed yourself!\nGame Over.\n";
		}
		gameOver = true;
		this.setChanged();
	    notifyObservers();
	}
	
	// All cells in the map become visited to reveal the static states.
	public void showMap() {
		for (int r = 0; r < nRows; r++) {
			for (int c = 0; c < nColumns; c++) {
				map[r][c].setVisited(true);
			}
		}
	}

	
	/* WumpusMap currently is the model for the WumpusPlayer GUI.
	 * The user plays the game in the console, using shoot and move.
*	public static void main(String[] args) {
*		WumpusMap map = new WumpusMap(10, 10);
*		map.setMap();
*		Scanner user = new Scanner(System.in);
*		System.out.println("Welcome to Hunt the Wumpus!\n");
*		boolean over = false;
*		while (!over) {
*			System.out.println(map.toString());
*			System.out.println("Would you like to shoot or move?");
*			String choice = user.next().toLowerCase();
*			if (choice.equals("shoot")) {
*				System.out
*						.println("Where would you like to shoot?: up, down, left, right");
*				String shootChoice = user.next();
*				System.out.println("----------------------------------------------------");
*				map.shoot(shootChoice);
*				over = true;
*			} else if (choice.equals("move")) {
*				System.out
*						.println("Where would you like to move?: up, down, left, right");
*				String moveChoice = user.next();
*				System.out.println("----------------------------------------------------");
*				over = map.move(moveChoice);
*			}
*		}
*		map.showMap();// reveal the gameboard
*		System.out.println(map.toString());
*		user.close();// closes the scanner
*	}
	*/
}
