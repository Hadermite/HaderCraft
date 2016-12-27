package se.wiklund.minecraft2d;

import java.awt.Graphics2D;

import se.wiklund.minecraft2d.game.Game;
import se.wiklund.minecraft2d.graphics.Screen;
import se.wiklund.minecraft2d.graphics.Window;
import se.wiklund.minecraft2d.util.UIUtils;

public class Main {
	
	public static final int WIDTH = 480, HEIGHT = WIDTH / 16 * 9;
	public static final double SCALE = 2;
	public static final int TICKRATE = 64;
	public static final String NAME = "Minecraft2D";
	
	private static Window window;
	private static Screen screen;
	private static int tps, fps;
	
	private static State state;
	
	public static void start() {
		window = new Window();
		screen = window.getScreen();
		
		UIUtils.setGraphics((Graphics2D) screen.getGraphics());
		state = new Menu();
		
		new Thread(()-> startLoop()).start();
	}
	
	private static void startLoop() {
		final double UPDATE_INTERVAL = 1000000000 / TICKRATE;
		
		long lastTime = System.nanoTime();
		long timer = System.nanoTime();
		double delta = 0;
		int ticks = 0;
		int frames = 0;
		
		while (true) {
			long now = System.nanoTime();
			delta += now - lastTime;
			lastTime = now;
			
			while (delta >= UPDATE_INTERVAL) {
				delta -= UPDATE_INTERVAL;
				screen.tick();
				ticks++;
			}
			
			screen.render();
			frames++;
			
			if (timer + 1000000000 <= System.nanoTime()) {
				timer += 1000000000;
				tps = ticks;
				fps = frames;
				ticks = 0;
				frames = 0;
			}
		}
	}
	
	public static int getTPS() {
		return tps;
	}
	
	public static int getFPS() {
		return fps;
	}
	
	public static State getState() {
		return state;
	}
	
	public static void setState(State state) {
		Main.state = state;
	}
	
	public static void main(String[] args) {
		start();
	}
}
