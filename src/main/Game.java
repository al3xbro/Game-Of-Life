package main;

public class Game {

	public static void main(String[] args) throws InterruptedException {
		
		int[][] premade = new int[][]{{0, 1, 0},
		           				   	  {0, 1, 0},
		           				   	  {0, 1, 0}};
		GameBoard board = new GameBoard(premade);
		while (true) {
			Thread.sleep(500);
			board.printBoard();
			board.iterate();
		}
	}
}
