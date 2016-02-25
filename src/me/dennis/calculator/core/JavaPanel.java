package me.dennis.calculator.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import me.dennis.calculator.listeners.Keyboard;
import me.dennis.calculator.listeners.Mouse;
import me.dennis.calculator.objects.Button;

@SuppressWarnings("serial")
public class JavaPanel extends JPanel implements ActionListener {

	public static final Integer WIDTH = 225;
	public static final Integer HEIGHT = 400;
	
	BufferedImage image;
	Keyboard keyboard = new Keyboard();
	Mouse mouse = new Mouse();
	List<Button> buttons = new ArrayList<Button>();
	
	int tick = 0;
	int fps = 0;
	
	public JavaPanel() {
		setFocusable(true);
		requestFocus();
		
		keyboard.setup();
		mouse.setupKeys();
		
		addKeyListener(keyboard);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		setupButtons();
		
		new Timer(1000 / 60, this).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.printf("FPS: %d - Ticks: %d\n", fps, tick);
					fps = 0;
					tick = 0;
				}
			}
			
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Button button : buttons) {
			button.update();
		}
		keyboard.reset();
		mouse.reset();
		tick++;
		repaint();
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
		Font font = null;
		try {
			if (font == null) {
				font = Font.createFont(Font.PLAIN, getClass().getResourceAsStream("/fonts/SFUIDisplay-Thin.ttf")).deriveFont(Font.PLAIN, 50f);
			}
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		g2.setFont(font);

		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).draw(g2);
		}
		
		g1.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		fps++;
	}
	
	public void setupButtons() {
		Integer num = 9;
		for (int y = 4; y > 0; y--) {
			for (int x = 3; x > 0; x--) {
				final Integer parm = num;
				if (num > 0) {
					buttons.add(new Button(String.valueOf(num--), (x - 1) + (55 * (x - 1)), HEIGHT - (y + 2) - (55 * (y + 2)) - (55 / 2), 55, 55, new Thread(new Runnable() {
						@Override
						public void run() {
							System.out.println(parm);
						}
					})));
				}
				else {
					buttons.add(new Button(String.valueOf(num--), (x - 3) + (55 * (x - 3)), HEIGHT - (y + 2) - (55 * (y + 2)) - (55 / 2), 110, 55, new Thread(new Runnable() {
						@Override
						public void run() {
							System.out.println(parm);
						}
					})));
					break;
				}
			}
		}
	}
	
	public void setRenderingHints(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	}
	
}
