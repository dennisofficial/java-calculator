package me.dennis.calculator.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.IOException;
import me.dennis.calculator.listeners.Mouse;

public class Button {

	public String text;
	public Integer x, y, width, height;
	public Thread thread;
	public Rectangle bounds;
	
	public Button(String text, Integer x, Integer y, Integer width, Integer height, Thread thread) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.thread = thread;
	}
	
	public void update() {
		bounds = new Rectangle(x, y, width, height);
		if (Mouse.isPressed(MouseEvent.BUTTON1)) {
			if (bounds.contains(Mouse.getPoint())) {
				thread.run();
			}
		}
	}
	
	public void draw(Graphics2D g) {
		g.setColor(new Color(0x0));
		g.fillRect(x, y, width, height);
		g.setColor(new Color(0xFFFFFF));
		Font font = null;
		try {
			font = Font.createFont(Font.PLAIN, getClass().getResourceAsStream("/fonts/SFUIDisplay-Thin.ttf")).deriveFont(Font.PLAIN, 75f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		g.drawString(text, x - (fm.stringWidth(text) / 2) + (width / 2), y + (fm.getHeight() / 3) + (height / 2));
	}
	
}
