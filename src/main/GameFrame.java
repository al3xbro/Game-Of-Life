package main;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	
	public GamePanel gPanel;
	
	GameFrame() {
		gPanel = new GamePanel();	
		this.setTitle("Colorful Conway's Game of Life");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(gPanel);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
