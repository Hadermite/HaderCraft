package se.wiklund.minecraft2d.game;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.State;
import se.wiklund.minecraft2d.input.Keyboard;
import se.wiklund.minecraft2d.menu.Settings;
import se.wiklund.minecraft2d.types.BlockType;
import se.wiklund.minecraft2d.util.blur.GaussianFilter;

public class Game extends State {
	
	private HUD hud;
	private BlocksMenu blocksMenu;
	private World world;
	private PauseMenu pauseMenu;
	private boolean paused, blocksMenuOpen;
	private BufferedImage pausedBackground;
	private BlockType selectedBlock;
	
	public Game() {
		hud = new HUD(this);
		blocksMenu = new BlocksMenu(this);
		world = new World(this);
		pauseMenu = new PauseMenu(this);
	}
	
	public void tick() {
		if (paused) {
			pauseMenu.tick();
		} else if (blocksMenuOpen) {
			blocksMenu.tick();
		} else {
			world.tick();
			hud.tick();
		}
		
		if (Keyboard.isKeyPressed(KeyEvent.VK_B) && !paused) {
			blocksMenuOpen = !blocksMenuOpen;
		}
		
		if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			if (!paused) {
				final BufferedImage screenshot = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = (Graphics2D) screenshot.getGraphics();
				if (Settings.antiAliasing)
					g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				world.render(g);
				hud.render(g);
				
				pausedBackground = screenshot;
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						long startTime = System.currentTimeMillis();
						GaussianFilter blur = new GaussianFilter(5);
						pausedBackground = blur.filter(screenshot, null);
						System.out.println("Everything took " + (System.currentTimeMillis() - startTime) + " milliseconds to process");
					}
				}).start();
			}
			
			paused = !paused;
		}
	}
	
	public void render(Graphics2D g) {
		world.render(g);
		hud.render(g);
		if (blocksMenuOpen) {
			blocksMenu.render(g);
		}
		if (paused) {
			g.drawImage(pausedBackground, 0, 0, Main.WIDTH, Main.HEIGHT, null);
			pauseMenu.render(g);
		}
	}

	@Override
	public void onMouseClick(int button, int x, int y) {
		if (paused) {
			pauseMenu.onMouseClick(button, x, y);
		} else if (blocksMenuOpen) {
			blocksMenu.onMouseClick(button, x, y);
		} else {
			world.onMouseClick(button, x, y);
			
			if (selectedBlock != null && button == 3) {
				world.placeBlockScreen(selectedBlock, x, y);
			}
		}
	}
	
	public void updateHUD() {
		hud.tick();
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
	
	public BlockType getSelectedBlock() {
		return selectedBlock;
	}
	
	public void setSelectedBlock(BlockType selectedBlock) {
		this.selectedBlock = selectedBlock;
	}
	
	public void closeBlockMenu() {
		blocksMenuOpen = false;
	}
}