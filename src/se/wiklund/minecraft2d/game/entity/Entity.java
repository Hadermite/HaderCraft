package se.wiklund.minecraft2d.game.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Entity {
	
	protected BufferedImage texture;
	protected double x, y;
	protected int width, height;
	
	public Entity(BufferedImage texture, double x, double y, int width, int height) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Entity(BufferedImage texture, double x, double y) {
		this(texture, x, y, texture.getWidth(), texture.getHeight());
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		g.drawImage(texture, (int) x, (int) y, width, height, null);
	}
	
	public void move(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
