package se.wiklund.minecraft2d;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import se.wiklund.minecraft2d.graphics.Screen;
import se.wiklund.minecraft2d.graphics.Window;
import se.wiklund.minecraft2d.input.Keyboard;
import se.wiklund.minecraft2d.input.Mouse;
import se.wiklund.minecraft2d.menu.Menu;
import se.wiklund.minecraft2d.menu.Settings;
import se.wiklund.minecraft2d.menu.SplashScreen;
import se.wiklund.minecraft2d.util.UIUtils;

public class Main {
	
	public static final int WIDTH = 1920, HEIGHT = WIDTH / 16 * 9;
	public static final int TICKRATE = 64;
	public static final String NAME = "Minecraft2D";
	
	public static int canvasWidth, canvasHeight;
	public static double scale;
	
	private static Window window;
	private static Screen screen;
	private static int tps, fps;
	private static int lastWindowWidth, lastWindowHeight;
	
	private static State state;
	
	public static void start() {
		window = new Window(true);
		screen = window.getScreen();
		
		updateCanvasSize();
		
		UIUtils.setGraphics((Graphics2D) screen.getGraphics());
		if (!Settings.noSplash) {
			setState(new SplashScreen());
		} else {
			setState(new Menu());
		}
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				startLoop();
			}
		}).start();
	}
	
	public static void updateCanvasSize() {
		if (screen == null) return;
		if (lastWindowWidth == window.getWidth() && lastWindowHeight == window.getHeight()) return;
		lastWindowWidth = window.getWidth();
		lastWindowHeight = window.getHeight();
		
		double scaleX = (double) window.getWidth() / WIDTH;
		double scaleY = (double) window.getHeight() / HEIGHT;
		scale = Math.min(scaleX, scaleY);
		
		canvasWidth = (int) (WIDTH * scale);
		canvasHeight = (int) (HEIGHT * scale);
		
		Dimension canvasSize = new Dimension(canvasWidth, canvasHeight);
		screen.setSize(canvasSize);
		screen.setPreferredSize(canvasSize);
		screen.setMinimumSize(canvasSize);
		screen.setMaximumSize(canvasSize);
		
		window.validate();
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
				if ((Keyboard.isKeyDown(KeyEvent.VK_ALT) && Keyboard.isKeyPressed(KeyEvent.VK_ENTER)) || Settings.fullscreenChangeQueued) {
					Settings.fullscreenChangeQueued = false;
					if (window.isFullscreen()) {
						createNewWindow(false);
					} else {
						createNewWindow(true);
					}
				}
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
	
	public static void createNewWindow(boolean fullscreen) {
		window.dispose();
		window = new Window(fullscreen);
		screen = window.getScreen();
	}
	
	public static void setState(State state) {
		if (Main.state != null) Mouse.removeMouseReader(Main.state);
		Main.state = state;
		Mouse.addMouseReader(state);
	}
	
	public static Window getWindow() {
		return window;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (arg.equalsIgnoreCase("-noSplash")) Settings.noSplash = true;
		}
		start();
	}
}
