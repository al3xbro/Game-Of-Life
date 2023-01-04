package main;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class GameBoard {
	
	Cell[][] board;
	int rowCount, colCount; // board size
	
	/**
	 * creates a new board with no live cells
	 * 
	 * @param xDim: number of rowCounts
	 * @param yDim: number of columns
	 */
	public GameBoard(int rowCount, int colCount) {
		board = new Cell[rowCount][colCount];
		this.rowCount = rowCount;
		this.colCount = colCount;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				board[i][j] = new Cell(Color.WHITE);
			}
		}
	}
	
	/**
	 * creates a new board with a premade array of cells
	 * 
	 * @param premade: premade 2d array of Cells
	 */
	public GameBoard(int[][] premade) { // premade board
		board = new Cell[premade.length][premade[0].length];
		for (int i = 0; i < premade.length; i++) {
			for (int j = 0; j < premade[0].length; j++) {
				if (premade[i][j] == 1) {
					board[i][j] = new Cell(Color.WHITE, true);
				}
				else {
					board[i][j] = new Cell(Color.WHITE, false);
				}
			}
		}
		rowCount = premade.length;
		colCount = premade[0].length;
	}
	
	public void editCell(int x, int y) {
		board[x][y].setState(true);
	}
	
	/**
	 * finds all live neighbors of a cell
	 * 
	 * @param x: x coordinate of cell
	 * @param y: y coordinate of cell
	 * @return ArrayList<Cell>: ArrayList of all live neighbors
	 */
	private ArrayList<Cell> getNeighbors(int x, int y) {
		ArrayList<Cell> aliveNeighbors = new ArrayList<>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (((x+i) >= 0 && (x+i) < rowCount) && // checking within xDim
					((y+j) >= 0 && (y+j) < colCount) && // checking within yDim
					!(i == 0 && j == 0)) { // checking not original cell
					if (board[x+i][y+j].getState()) {
						aliveNeighbors.add(board[x+i][y+j]);
					}
				}
			}
		}
		return aliveNeighbors;
	}
	
	/**
	 * finds the new state and color of a cell
	 * 
	 * @param x: x coordinate of cell
	 * @param y: y coordinate of cell
	 * @return Cell: new cell with updated properties
	 */
	private Cell newState(int x, int y) {
		ArrayList<Cell> aliveNeighbors = getNeighbors(x, y);
		
		if (board[x][y].getState()) {
			if (aliveNeighbors.size() < 2 || aliveNeighbors.size() > 3) { // it just died
				return new Cell(null, false);
			}
			else { // it stays alive
				return new Cell(null, true);
			}
		}
		
		else {
			if (aliveNeighbors.size() == 3) { // it just livened
				return new Cell(null, true);
			}
			else { // it stays dead
				return new Cell(null, false); 
			}
		}
		
	}
	
	/**
	 * iterates through the whole board
	 */
	public void iterate() {
		Queue<Cell> toChange = new LinkedList<>();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				toChange.add(newState(i, j));
			}
		}
		
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				board[i][j] = toChange.remove();
			}
		}
	}
	/**
	 * prints the board in its current state
	 */
	public void printBoard() {
		System.out.println("----------------------------");
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}
