package main;

public class Game {

	public static void main(String[] args) throws InterruptedException {
		
		GameBoard board = new GameBoard(3, 3);
		board.editCell(0, 1);
		board.editCell(1, 1);
		board.editCell(2, 1);
		while (true) {
			Thread.sleep(500);
			board.iterate();
			board.printBoard();
		}
	}
}
