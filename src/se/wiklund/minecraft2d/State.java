package se.wiklund.minecraft2d;

import java.awt.Graphics2D;

public abstract class State {
	
	public abstract void tick();
	public abstract void render(Graphics2D g);
	public abstract void onMouseClick(int button, int x, int y);
}
