package se.wiklund.minecraft2d.game;

import java.awt.Graphics2D;

public class Game {
	
	private World world;
	
	public Game() {
		world = new World(64);
	}
	
	public void tick() {
		world.tick();
	}
	
	public void render(Graphics2D g) {
		world.render(g);
	}
}
