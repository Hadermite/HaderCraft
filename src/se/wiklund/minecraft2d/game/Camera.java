package se.wiklund.minecraft2d.game;

import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.game.entity.Entity;

public class Camera {
	
	private int x, y;
	private int renderOffsetX, renderOffsetY;
	private Entity focusEntity;
	
	public void tick(Entity entity) {
		this.focusEntity = entity;
		this.x = (int) entity.getX();
		this.y = (int) entity.getY();
		this.renderOffsetX = (Main.WIDTH / 2) - x - (entity.getWidth() / 2);
		this.renderOffsetY = (Main.HEIGHT / 2) - y - (entity.getHeight() / 2);
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
	
	public Entity getFocusEntity() {
		return focusEntity;
	}
}
