package se.wiklund.minecraft2d.component;

import java.awt.Graphics2D;

public class Component {
	
	protected double x, y;
	protected int width, height;
	
	public Component(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		
	}
}
