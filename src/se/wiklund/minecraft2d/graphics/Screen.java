package se.wiklund.minecraft2d.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.input.Keyboard;
import se.wiklund.minecraft2d.input.Mouse;

public class Screen extends Canvas {
	
	public Screen() {
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addMouseWheelListener(mouse);
		addKeyListener(new Keyboard());
	}

	public void tick() {
		Main.getState().tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			requestFocusInWindow();
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.scale(Main.scale, Main.scale);
		// Add to Settings. Default should be off because of performance. Maybe check system?
		// g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		
		Main.getState().render(g);
		
		g.dispose();
		bs.show();
	}
}
