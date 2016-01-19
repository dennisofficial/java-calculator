package me.dennis.calculator.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class JavaPanel extends JPanel implements ActionListener {

	public static final Integer WIDTH = 100;
	public static final Integer HEIGHT = 100;
	
	public JavaPanel() {
		super();
		setFocusable(true);
		requestFocus();
		
		new Timer(1000/60, this).start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(0x0));
		g.fillRect(0, 0, getWidth(), getHeight());
		repaint();
	}
	
}
