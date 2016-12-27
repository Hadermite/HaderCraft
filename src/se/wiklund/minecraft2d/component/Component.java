package se.wiklund.minecraft2d.component;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import se.wiklund.minecraft2d.Main;

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
	
	public void center() {
		x = (Main.WIDTH - width) / 2;
		y = (Main.HEIGHT - height) / 2;
	}
	
	public void centerX() {
		x = (Main.WIDTH - width) / 2;
	}
	
	public void centerY() {
		y = (Main.HEIGHT - height) / 2;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}
	
	public boolean containsCoord(double x, double y) {
		return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
	}
}
