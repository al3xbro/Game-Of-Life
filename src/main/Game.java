package main;

import java.awt.Color;

public class Game {

	public static void main(String[] args) throws InterruptedException {
		
		Cell[][] premade = new Cell[][]{{new Cell(Color.blue, false), new Cell(Color.green, true), new Cell(Color.blue, false)},
		           				   	  	{new Cell(Color.blue, false), new Cell(Color.blue, true), new Cell(Color.blue, false)},
		           				   	  	{new Cell(Color.blue, false), new Cell(Color.blue, true), new Cell(Color.blue, false)}};
		GameBoard board = new GameBoard(premade);
		while (true) {
			Thread.sleep(500);
			board.printBoard();
			board.iterate();
		}
	}
}
