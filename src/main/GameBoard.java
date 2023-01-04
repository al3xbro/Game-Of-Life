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
		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				board[row][col] = new Cell(Color.WHITE);
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
		for (int row = 0; row < premade.length; row++) {
			for (int col = 0; col < premade[0].length; col++) {
				if (premade[row][col] == 1) {
					board[row][col] = new Cell(Color.WHITE, true);
				}
				else {
					board[row][col] = new Cell(Color.WHITE, false);
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
		for (int row = -1; row <= 1; row++) {
			for (int col = -1; col <= 1; col++) {
				if (((x+row) >= 0 && (x+row) < rowCount) && // checking within xDim
					((y+col) >= 0 && (y+col) < colCount) && // checking within yDim
					!(row == 0 && col == 0)) { // checking not original cell
					if (board[x+row][y+col].getState()) {
						aliveNeighbors.add(board[x+row][y+col]);
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
				return new Cell(null, false); // TODO: make it leave a fresh trail
			}
			else { // it stays alive
				return new Cell(null, true); // TODO: make it change color based on surroundings
			}
		}
		
		else {
			if (aliveNeighbors.size() == 3) { // it just livened
				return new Cell(null, true); // TODO: make it inherit color
			}
			else { // it stays dead
				return new Cell(null, false); // TODO: make it slowly lose trail
			}
		}
		
	}
	
	/**
	 * iterates through the whole board
	 */
	public void iterate() {
		Queue<Cell> toChange = new LinkedList<>();
		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				toChange.add(newState(row, col));
			}
		}
		
		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				board[row][col] = toChange.remove();
			}
		}
	}
	
	/**
	 * prints the board in its current state
	 */
	public void printBoard() {
		System.out.println("----------------------------");
		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				System.out.print(board[row][col]);
			}
			System.out.println();
		}
	}
}
