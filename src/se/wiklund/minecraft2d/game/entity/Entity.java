package se.wiklund.minecraft2d.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import se.wiklund.minecraft2d.game.block.Block;

public class Entity {
	
	private static final int COL_MARGIN = 3;
	
	protected BufferedImage texture;
	protected double xPos, yPos;
	protected int width, height;
	
	public Entity(BufferedImage texture, double xPos, double yPos, int width, int height) {
		this.texture = texture;
		this.xPos = xPos;
		this.yPos = yPos;
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
		g.setColor(Color.BLUE);
		g.draw(getBoundsTop());
		g.setColor(Color.YELLOW);
		g.draw(getBoundsBottom());
		g.setColor(Color.RED);
		g.draw(getBoundsLeft());
		g.setColor(Color.GREEN);
		g.draw(getBoundsRight());
	}
	
	public void move(double xPos, double yPos) {
		this.xPos += xPos;
		this.yPos += yPos;
	}
	
	public Rectangle getBounds() {
		int x = (int) (xPos * Block.SIZE);
		int y = (int) (yPos * Block.SIZE);
		return new Rectangle(x, y, width, height);
	}
	
	public Rectangle getBoundsTop() {
		int x = (int) (xPos * Block.SIZE);
		int y = (int) (yPos * Block.SIZE);
		return new Rectangle(x + COL_MARGIN, y, width - COL_MARGIN * 2, COL_MARGIN);
	}
	
	public Rectangle getBoundsBottom() {
		int x = (int) (xPos * Block.SIZE);
		int y = (int) (yPos * Block.SIZE);
		return new Rectangle(x + COL_MARGIN, y + height - COL_MARGIN, width - COL_MARGIN * 2, COL_MARGIN);
	}
	
	public Rectangle getBoundsLeft() {
		int x = (int) (xPos * Block.SIZE);
		int y = (int) (yPos * Block.SIZE);
		return new Rectangle(x, y + COL_MARGIN, COL_MARGIN, height - COL_MARGIN * 2);
	}
	
	public Rectangle getBoundsRight() {
		int x = (int) (xPos * Block.SIZE);
		int y = (int) (yPos * Block.SIZE);
		return new Rectangle(x + width - COL_MARGIN, y + COL_MARGIN, COL_MARGIN, height - COL_MARGIN * 2);
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
