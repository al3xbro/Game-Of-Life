package main;
import java.awt.Color;

public class Cell {
	
	private boolean state; // true = alive, false = dead
	private Color color; // color of cell, dead or alive
	
	public Cell(Color color) {
		this.color = color;
		state = false;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean getState() {
		return state;
	}
	
	public Color getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		if (state) {
			return "1";
		}
		else {
			return "0";
		}
	}
}
