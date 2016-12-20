package se.wiklund.minecraft2d.game;

import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.game.entity.Entity;

public class Camera {
	
	private double x, y;
	private double renderOffsetX, renderOffsetY;
	
	public void tick(double x, double y) {
		this.x = x;
		this.y = y;
		this.renderOffsetX = Main.WIDTH / 2 - x;
		this.renderOffsetY = Main.HEIGHT / 2 - y;
	}
	
	public void tick(Entity entity) {
		this.x = entity.getX();
		this.y = entity.getY();
		this.renderOffsetX = (Main.WIDTH / 2) - x - (entity.getWidth() / 2);
		this.renderOffsetY = (Main.HEIGHT / 2) - y - (entity.getHeight() / 2);
//		this.renderOffsetX = x - (Main.WIDTH / 2) + (entity.getWidth() / 2);
//		this.renderOffsetY = y - (Main.HEIGHT / 2) + (entity.getHeight() / 2);
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
