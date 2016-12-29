package se.wiklund.minecraft2d.menu;

import java.awt.Graphics2D;

import se.wiklund.minecraft2d.Assets;
import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.State;

public class SplashScreen extends State {
	
	private int timer = 0;
	
	@Override
	public void tick() {
		timer++;
		
		if (timer >= 2 * Main.TICKRATE) {
			Main.setState(new Menu());
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(Assets.SPLASH_SCREEN, 0, 0, Main.WIDTH, Main.HEIGHT, null);
	}

	@Override
	public void onMouseClick(int button, int x, int y) {
		
	}
}
