package main;
import java.awt.Color;
import java.util.Arrays;

public class GameBoard {
	
	Cell[][] board;
	
	public GameBoard(int x, int y) {
		board = new Cell[x][y];
		Arrays.fill(board, new Cell(Color.WHITE));
	}
	
}
