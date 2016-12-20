package se.wiklund.minecraft2d;

import se.wiklund.minecraft2d.graphics.Screen;
import se.wiklund.minecraft2d.graphics.Window;

public class Main {
	
	public static final int WIDTH = 480, HEIGHT = WIDTH / 16 * 9;
	public static final double SCALE = 2;
	public static final String NAME = "Minecraft2D";
	
	private static Window window;
	private static Screen screen;
	
	public static void start() {
		window = new Window();
		screen = window.getScreen();
		
		new Thread(()-> startLoop()).start();
	}
	
	private static void startLoop() {
		
		final double UPDATE_INTERVAL = 1000000000 / 64;
		
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
				window.setTitle(NAME + " | TPS: " + ticks + ", FPS: " + frames);
				ticks = 0;
				frames = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		start();
	}
}
