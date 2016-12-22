package se.wiklund.minecraft2d.game.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import se.wiklund.minecraft2d.game.block.Block;

public class Entity {
	
	protected BufferedImage texture;
	protected double xPos, yPos;
	protected int width, height;
	
	public Entity(BufferedImage texture, double xPos, double yPos, int width, int height) {
		this.texture = texture;
		this.xPos = xPos;
		this.yPos = xPos;
		this.width = width;
		this.height = height;
	}
	
	public Entity(BufferedImage texture, double xPos, double yPos) {
		this(texture, xPos, yPos, texture.getWidth(), texture.getHeight());
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		g.drawImage(texture, (int) (xPos * Block.SIZE), (int) (yPos * Block.SIZE), width, height, null);
	}
	
	public void move(double xPos, double yPos) {
		this.xPos += xPos;
		this.yPos += yPos;
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
	
	public double getX() {
		return xPos;
	}
	
	public double getY() {
		return yPos;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
