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
	public GameBoard(Cell[][] premade) { // premade board
		board = new Cell[premade.length][premade[0].length];
		for (int row = 0; row < premade.length; row++) {
			for (int col = 0; col < premade[0].length; col++) {
				board[row][col] = premade[row][col];
			}
		}
		rowCount = premade.length;
		colCount = premade[0].length;
	}
	
	public void editCell(int cRow, int cCol) {
		board[cRow][cCol].setState(true);
	}
	
	/**
	 * finds all live neighbors of a cell
	 * 
	 * @param cRow: row number of cell
	 * @param cCol: col number of cell
	 * @return ArrayList<Cell>: ArrayList of all live neighbors
	 */
	private ArrayList<Cell> getNeighbors(int cRow, int cCol) {
		ArrayList<Cell> aliveNeighbors = new ArrayList<>();
		for (int row = -1; row <= 1; row++) {
			for (int col = -1; col <= 1; col++) {
				if (((cRow+row) >= 0 && (cRow+row) < rowCount) && // checking within xDim
					((cCol+col) >= 0 && (cCol+col) < colCount) && // checking within yDim
					!(row == 0 && col == 0)) { // checking not original cell
					if (board[cRow+row][cCol+col].getState()) {
						aliveNeighbors.add(board[cRow+row][cCol+col]);
					}
				}
			}
		}
		return aliveNeighbors;
	}
	
	/**
	 * finds the new state and color of a cell
	 * 
	 * @param cRow: row number of cell
	 * @param cCol: col number of cell
	 * @return Cell: new cell with updated properties
	 */
	private Cell newState(int cRow, int cCol) {
		Color currColor = board[cRow][cCol].getColor();
		ArrayList<Cell> aliveNeighbors = getNeighbors(cRow, cCol);
		float[] cellHSB = Color.RGBtoHSB(currColor.getRed(), currColor.getGreen(), currColor.getBlue(), null);
		
		if (board[cRow][cCol].getState()) {
			if (aliveNeighbors.size() < 2 || aliveNeighbors.size() > 3) { // it just died
				return new Cell(Color.getHSBColor(cellHSB[0], cellHSB[1]-30, cellHSB[2]), false);
			}
			else { // it stays alive
				float[] averageNeighborRGB = {0, 0, 0};
				for (Cell neighbor : aliveNeighbors) {
					averageNeighborRGB[0] += neighbor.getColor().getRed();
					averageNeighborRGB[1] += neighbor.getColor().getGreen();
					averageNeighborRGB[2] += neighbor.getColor().getBlue();
				}
				averageNeighborRGB[0] /= aliveNeighbors.size();
				averageNeighborRGB[1] /= aliveNeighbors.size();
				averageNeighborRGB[2] /= aliveNeighbors.size();
				
				return new Cell(new Color((averageNeighborRGB[0] + currColor.getRed())/2, (averageNeighborRGB[1] + currColor.getGreen())/2, (averageNeighborRGB[2] + currColor.getBlue())/2), true);
			}
		}
		
		else {
			if (aliveNeighbors.size() == 3) { // it just livened
				float[] averageNeighborRGB = {0, 0, 0};
				for (Cell neighbor : aliveNeighbors) {
					averageNeighborRGB[0] += neighbor.getColor().getRed();
					averageNeighborRGB[1] += neighbor.getColor().getGreen();
					averageNeighborRGB[2] += neighbor.getColor().getBlue();
				}
				averageNeighborRGB[0] /= aliveNeighbors.size();
				averageNeighborRGB[1] /= aliveNeighbors.size();
				averageNeighborRGB[2] /= aliveNeighbors.size();
				return new Cell(new Color(averageNeighborRGB[0], averageNeighborRGB[1], averageNeighborRGB[2]), true);
				
			}
			else { // it stays dead
				if (cellHSB[1]-10 > 0) {
					return new Cell(Color.getHSBColor(cellHSB[0], cellHSB[1]-10, cellHSB[2]), false);
				}
				else {
					return new Cell(Color.WHITE, false);
				}
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
