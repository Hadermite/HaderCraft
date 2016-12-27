package se.wiklund.minecraft2d.game;

import java.awt.Graphics2D;

import se.wiklund.minecraft2d.State;

public class Game extends State {
	
	private HUD hud;
	private World world;
	
	public Game() {
		hud = new HUD();
		world = new World(this);
	}
	
	public void tick() {
		world.tick();
		hud.tick();
	}
	
	public void render(Graphics2D g) {
		world.render(g);
		hud.render(g);
	}

	@Override
	public void onMouseClick(int button, int x, int y) {
		world.onMouseClick(button, x, y);
	}
	
	public World getWorld() {
		return world;
	}
	
	public HUD getHUD() {
		return hud;
	}
}