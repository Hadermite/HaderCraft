package se.wiklund.minecraft2d;

import java.awt.Graphics2D;

import se.wiklund.minecraft2d.input.MouseReader;

public abstract class State implements MouseReader {
	
	public abstract void tick();
	public abstract void render(Graphics2D g);
	public abstract void onMouseClick(int button, int x, int y);
}
