package se.wiklund.minecraft2d.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.game.Game;
import se.wiklund.minecraft2d.input.Keyboard;

public class Screen extends Canvas {

	private Game game;

	public Screen() {
		setPreferredSize(new Dimension((int) (Main.WIDTH * Main.SCALE), (int) (Main.HEIGHT * Main.SCALE)));

		addKeyListener(new Keyboard());

		game = new Game();
	}

	public void tick() {
		game.tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			requestFocusInWindow();
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.scale(Main.SCALE, Main.SCALE);
		// Add to Settings. Default should be off because of performance. Maybe check system?
		// g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

		game.render(g);

		g.dispose();
		bs.show();
	}
}