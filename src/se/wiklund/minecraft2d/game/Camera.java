package se.wiklund.minecraft2d.game;

import se.wiklund.minecraft2d.Main;

public class Camera {
	
	private double x, y;
	private double renderOffsetX, renderOffsetY;
	
	public void tick(double x, double y) {
		this.x = x;
		this.y = y;
		this.renderOffsetX = x - Main.WIDTH / 2;
		this.renderOffsetY = y - Main.HEIGHT / 2;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getRenderOffsetX() {
		return renderOffsetX;
	}
	
	public double getRenderOffsetY() {
		return renderOffsetY;
	}
}
