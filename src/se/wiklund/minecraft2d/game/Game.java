package se.wiklund.minecraft2d.game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.State;
import se.wiklund.minecraft2d.input.Keyboard;
import se.wiklund.minecraft2d.util.blur.GaussianFilter;

public class Game extends State {
	
	private HUD hud;
	private World world;
	private PauseMenu pauseMenu;
	private boolean paused;
	private BufferedImage pausedBackground;
	
	public Game() {
		hud = new HUD();
		world = new World(this);
		pauseMenu = new PauseMenu(this);
	}
	
	public void tick() {
		if (!paused) {
			world.tick();
			hud.tick();
		} else {
			pauseMenu.tick();
		}
		
		if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			if (!paused) {
				BufferedImage screenshot = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = (Graphics2D) screenshot.getGraphics();
				world.render(g);
				hud.render(g);
				
				GaussianFilter blur = new GaussianFilter(5);
				pausedBackground = blur.filter(screenshot, null);
			}
			
			paused = !paused;
		}
	}
	
	public void render(Graphics2D g) {
		world.render(g);
		hud.render(g);
		if (paused) {
			g.drawImage(pausedBackground, 0, 0, Main.WIDTH, Main.HEIGHT, null);
			pauseMenu.render(g);
		}
	}

	@Override
	public void onMouseClick(int button, int x, int y) {
		if (!paused) {
			world.onMouseClick(button, x, y);
		} else {
			pauseMenu.onMouseClick(button, x, y);
		}
	}
	
	public World getWorld() {
		return world;
	}
	
	public HUD getHUD() {
		return hud;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}