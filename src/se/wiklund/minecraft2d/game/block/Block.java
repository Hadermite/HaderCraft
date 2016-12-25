package se.wiklund.minecraft2d.game.block;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import se.wiklund.minecraft2d.types.BlockType;

public class Block {
	
	public static final int SIZE = 32;
	
	private BlockType type;
	private int x, y;
	
	public Block(BlockType type, int xPos, int yPos) {
		this.type = type;
		this.x = xPos * SIZE;
		this.y = yPos * SIZE;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		if (type != null)
			g.drawImage(type.getTexture(), x, y, SIZE, SIZE, null);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, SIZE, SIZE);
	}
	
	public BlockType getType() {
		return type;
	}
	
	public void setType(BlockType type) {
		this.type = type;
	}
}
