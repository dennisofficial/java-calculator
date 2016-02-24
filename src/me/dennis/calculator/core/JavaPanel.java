package me.dennis.calculator.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import me.dennis.calculator.listeners.Keyboard;
import me.dennis.calculator.objects.Button;

@SuppressWarnings("serial")
public class JavaPanel extends JPanel implements ActionListener {

	public static final Integer WIDTH = 750;
	public static final Integer HEIGHT = 1060;
	
	BufferedImage image;
	Keyboard keyboard = new Keyboard();
	List<Button> buttons = new ArrayList<Button>();
	
	public JavaPanel() {
		super();
		setFocusable(true);
		requestFocus();
		
		keyboard.setup();
		
		addKeyListener(keyboard);
		
		setupButtons();
		
		new Timer(1000/60, this).start();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		keyboard.reset();
		for (Button button : buttons) {
			button.update();
		}
	}
	
	public void setupButtons() {
		Integer num = 9;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				final Integer parm = num;
				buttons.add(new Button(String.valueOf(num--), x + ((WIDTH / 4) * x), y + ((WIDTH / 4) * (y + 1)), WIDTH / 4, WIDTH / 4, new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println(parm - 1);
					}
				})));
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g1 = (Graphics2D) g;
		Graphics2D g2 = image.createGraphics();
		setRenderingHints(g1);
		setRenderingHints(g2);
		g2.setColor(new Color(0xE0E0E0));
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		
		for (Button button : buttons) {
			button.draw(g2);
		}
		
		g1.drawImage(image, 0, 0, WIDTH / 2, HEIGHT / 2, null);
		repaint();
	}
	
	public void setRenderingHints(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	}
	
}
