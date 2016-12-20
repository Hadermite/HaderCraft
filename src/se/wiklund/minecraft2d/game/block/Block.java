package se.wiklund.minecraft2d.game.block;

import java.awt.Graphics2D;

import se.wiklund.minecraft2d.type.BlockType;

public class Block {
	
	public static final int SIZE = 32;
	
	private BlockType type;
	private int xPos, yPos;
	
	public Block(BlockType type, int xPos, int yPos) {
		this.type = type;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		g.drawImage(type.getTexture(), xPos * SIZE, yPos * SIZE, SIZE, SIZE, null);
	}
}
