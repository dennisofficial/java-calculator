package me.dennis.calculator.core;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Calculator");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new JavaPanel());
		f.setVisible(true);
		f.setResizable(false);
		f.setSize(JavaPanel.WIDTH, JavaPanel.HEIGHT);
		f.setLocationRelativeTo(null);
	}
	
}
