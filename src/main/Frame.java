package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
	
	JButton startButton;
	
	public Frame() {
		// creates and configures new frame
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Game of Life");
		this.setSize(1000,700);
		this.getContentPane().setBackground(Color.gray);
		
		// sets window icon
		ImageIcon image = new ImageIcon("assets/icon.jpg");
		this.setIconImage(image.getImage());
		
		// start button
		startButton = new JButton();
		startButton.addActionListener(this);	
		startButton.setText("Continue");
		startButton.setFont(new Font("Montserrat", Font.PLAIN, 20));
		startButton.setBounds(450, 0, 1000, 100);
		
		// creates the control panel
		JPanel controls = new JPanel();
		controls.setBackground(Color.green);	
		controls.setPreferredSize(new Dimension(this.getWidth(), 100));
		controls.add(startButton); // adds button
		
		// addition of every element
		this.add(controls, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			System.out.println("hi");
		}
		
	}
	
}
