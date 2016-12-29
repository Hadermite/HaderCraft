package se.wiklund.minecraft2d.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import se.wiklund.minecraft2d.game.block.Block;
import se.wiklund.minecraft2d.types.Direction;
import se.wiklund.minecraft2d.util.MathUtils;

public class Entity {

	private static final int COL_MARGIN = 10;
	private static final int COL_SIZE = 10;

	protected BufferedImage texture;
	protected double x, y;
	protected int width, height;
	protected double velX, velY;
	protected double acceleration, breakForce, maxSpeed, jumpForce, gravity;
	private boolean hasMoved, inAir;

	public Entity(BufferedImage texture, double x, double y, int width, int height) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		// Setup variables for a default entity. Subclasses can change these
		// later on in their own constructor.
		acceleration = 2.0;
		breakForce = 2.0;
		maxSpeed = 7.0;
		jumpForce = 10.0;
		gravity = 0.6;
		inAir = true;
	}

	public Entity(BufferedImage texture, double x, double y) {
		this(texture, x, y, texture.getWidth(), texture.getHeight());
	}

	public void tick() {
		if (!hasMoved) {
			velX = MathUtils.encounterZero(velX, breakForce);
		}
		
		velY += gravity;

		velX = MathUtils.clamp(velX, -maxSpeed, maxSpeed);
		
		x += velX;
		y += velY;
		
		hasMoved = false;
	}

	public void render(Graphics2D g) {
		g.drawImage(texture, (int) x, (int) y, width, height, null);
		g.setColor(Color.BLUE);
		g.draw(getBoundsTop());
		g.setColor(Color.YELLOW);
		g.draw(getBoundsBottom());
		g.setColor(Color.RED);
		g.draw(getBoundsLeft());
		g.setColor(Color.GREEN);
		g.draw(getBoundsRight());
	}

	public void move(Direction dir) {
		if (dir == Direction.LEFT)
			velX -= acceleration;
		else if (dir == Direction.RIGHT)
			velX += acceleration;
		else {
			System.err.println("Can't move entities in directions other than LEFT or RIGHT!");
		}

		hasMoved = true;
	}
	
	public void jump() {
		if (inAir) return;
		inAir = true;
		velY = -jumpForce;
	}

	public Rectangle getBounds() {
		int xa = (int) x;
		int ya = (int) y;
		return new Rectangle(xa, ya, width, height);
	}

	public Rectangle getBoundsTop() {
		int xa = (int) x;
		int ya = (int) y;
		return new Rectangle(xa + COL_MARGIN, ya, width - COL_MARGIN * 2, COL_SIZE);
	}

	public Rectangle getBoundsBottom() {
		int xa = (int) x;
		int ya = (int) y;
		return new Rectangle(xa + COL_MARGIN, ya + height - COL_SIZE, width - COL_MARGIN * 2, COL_SIZE);
	}

	public Rectangle getBoundsLeft() {
		int xa = (int) x;
		int ya = (int) y;
		return new Rectangle(xa, ya + COL_MARGIN, COL_SIZE, height - COL_MARGIN * 2);
	}

	public Rectangle getBoundsRight() {
		int xa = (int) x;
		int ya = (int) y;
		return new Rectangle(xa + width - COL_SIZE, ya + COL_MARGIN, COL_SIZE, height - COL_MARGIN * 2);
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
	
	public double getVelX() {
		return velX;
	}
	
	public double getVelY() {
		return velY;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setInAir(boolean inAir) {
		this.inAir = inAir;
	}
	
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	public double getXPos() {
		return x / Block.SIZE;
	}
	
	public double getYPos() {
		return y / Block.SIZE;
	}
}
