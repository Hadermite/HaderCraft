package se.wiklund.minecraft2d.game;

import java.awt.Color;
import java.awt.Graphics2D;

import se.wiklund.minecraft2d.Assets;
import se.wiklund.minecraft2d.Main;

public class Game {
	
	private World world;
	private int sidebarY;
	
	public Game() {
		world = new World(this);
	}
	
	public void tick() {
		world.tick();
	}
	
	public void render(Graphics2D g) {
		sidebarY = 15;
		world.render(g);
		
		drawSidebarRow("TPS: " + Main.getTPS(), g);
		drawSidebarRow("FPS: " + Main.getFPS(), g);
	}
	
	public void drawSidebarRow(String text, Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setFont(Assets.FONT_SIDEBAR);
		g.drawString(text, 7, sidebarY);
		sidebarY += 15;
	}
}