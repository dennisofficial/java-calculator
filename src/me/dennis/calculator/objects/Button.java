package me.dennis.calculator.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import me.dennis.calculator.listeners.Mouse;

public class Button {

	public String text;
	public Integer x, y, width, height;
	public Thread thread;
	public Rectangle bounds;

	private Color color = new Color(0xD4D5D9);

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
		if (bounds.contains(Mouse.getPoint())) {
			color = new Color(0xE4E5E9);
			if (Mouse.isDirect(MouseEvent.BUTTON1)) {
				color = new Color(0xC4C5C9);
			}
			if (Mouse.isPressed(MouseEvent.BUTTON1)) {
				thread.run();
			}
		}
		else {
			color = new Color(0xD4D5D9);
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(new Color(0x0));
		Font font = g.getFont().deriveFont(Font.PLAIN, 27f);
		FontMetrics fm = g.getFontMetrics(font);
		g.setFont(font);
		g.drawString(text, x - (fm.stringWidth(text) / 2) + (width / 2), y + (fm.getHeight() / 3) + (height / 2));
	}

}
