package main;
import java.awt.Color;
import java.util.Stack;

public class GameBoard {
	
	Cell[][] board;
	int xDim, yDim; // max board indices
	
	public GameBoard(int xDim, int yDim) {
		board = new Cell[xDim][yDim];
		this.xDim = xDim - 1;
		this.yDim = yDim - 1;
		for (int i = 0; i < xDim; i++) {
			for (int j = 0; j < yDim; j++) {
				board[i][j] = new Cell(Color.WHITE);
			}
		}
	}
	
	public void editCell(int x, int y) {
		board[x][y].setState(true);
	}
	
	/**
	 * @param x: x coordinate of cell
	 * @param y: y coordinate of cell
	 * @return int: the number of alive neighbors
	 */
	private int getAliveNeighbors(int x, int y) {
		int neighbors = 0;
		
		try { // testing every neighbor
			if (board[x-1][y-1].getState()) {neighbors++;}
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if (board[x][y-1].getState()) {neighbors++;}
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if (board[x+1][y-1].getState()) {neighbors++;}
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if (board[x-1][y].getState()) {neighbors++;}
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if (board[x+1][y].getState()) {neighbors++;}
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if (board[x-1][y+1].getState()) {neighbors++;}
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if (board[x][y+1].getState()) {neighbors++;}
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if (board[x+1][y+1].getState()) {neighbors++;}
		} catch (ArrayIndexOutOfBoundsException e) {}
			
		return neighbors;
	}
	
	/**
	 * @param x: x coordinate of cell
	 * @param y: y coordinate of cell
	 * @return boolean: whether or not to change the state of the cell
	 */
	private boolean change(int x, int y) {
		boolean newState; // the cell's updated state
		int numNeighbors = getAliveNeighbors(x, y);
		
		if (numNeighbors < 2 || numNeighbors >= 4) { // find new state of the cell
			newState = false;
		}
		else if (numNeighbors == 3) {
			newState = true;
		}
		else {
			return false;
		}
		
		return newState != board[x][y].getState();
	}
	
	public void iterate() {
		Stack<Cell> toChange = new Stack<>();
		for (int i = 0; i <= xDim; i++) {
			for (int j = 0; j <= yDim; j++) {
				if (change(i, j)) {
					toChange.push(board[i][j]);
				}
			}
		}
		while (!toChange.empty()) {
			Cell cellChange = toChange.pop();
			cellChange.setState(!cellChange.getState());
		}
	}
	
	public void printBoard() {
		for (int i = 0; i <= xDim; i++) {
			for (int j = 0; j <= yDim; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}
