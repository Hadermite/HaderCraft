package se.wiklund.minecraft2d.graphics;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import se.wiklund.minecraft2d.Main;

public class Window extends JFrame {
	
	private Screen screen;
	
	public Window() {
		screen = new Screen();
		
		setTitle(Main.NAME);
		add(screen);
		setSize((int) (Main.WIDTH * Main.SCALE), (int) (Main.HEIGHT * Main.SCALE));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public Screen getScreen() {
		return screen;
	}
}
