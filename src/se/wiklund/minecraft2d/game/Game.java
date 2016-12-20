package se.wiklund.minecraft2d.game;

import java.awt.Color;
import java.awt.Graphics2D;

import se.wiklund.minecraft2d.Assets;
import se.wiklund.minecraft2d.Main;

public class Game {
	
	private World world;
	
	public Game() {
		world = new World();
	}
	
	public void tick() {
		world.tick();
	}
	
	public void render(Graphics2D g) {
		world.render(g);
		
		g.setColor(Color.BLACK);
		g.setFont(Assets.FONT_SIDEBAR);
		g.drawString("TPS: " + Main.getTps(), 7, 15);
		g.drawString("FPS: " + Main.getFps(), 7, 30);
	}
}
