package main;

import java.awt.*;

public class Game {

	public static void main(String[] args) throws InterruptedException {
		GameFrame MainFrame = new GameFrame();
		GameBoard b = new GameBoard(25, 25); // default starting values
		Cell[][] board = b.getBoard();
		for (int row = 0; row < b.rowCount; row++) {
			for (int col = 0; col < b.colCount; col++) {
				Cell toPaint = board[row][col];
				// MainFrame.gPanel.paint(new Rectangle());
			}
		}

		/*
		 * Cell[][] premade = new Cell[][]{{new Cell(Color.blue, false), new
		 * Cell(Color.green, true), new Cell(Color.blue, false)},
		 * {new Cell(Color.blue, false), new Cell(Color.blue, true), new
		 * Cell(Color.blue, false)},
		 * {new Cell(Color.blue, false), new Cell(Color.blue, true), new
		 * Cell(Color.blue, false)}};
		 * GameBoard board = new GameBoard(premade);
		 * while (true) {
		 * Thread.sleep(500);
		 * board.printBoard();
		 * board.iterate();
		 * }
		 */
	}
}
