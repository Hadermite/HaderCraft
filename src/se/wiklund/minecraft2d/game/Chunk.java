package se.wiklund.minecraft2d.game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import se.wiklund.minecraft2d.game.block.Block;
import se.wiklund.minecraft2d.type.BlockType;

public class Chunk {
	
	public static final int SIZE = 16;
	
	private int xPos, yPos;
	private Rectangle bounds;
	private Block[] blocks;
	
	public Chunk(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		
		bounds = new Rectangle(xPos * SIZE * Block.SIZE, yPos * SIZE * Block.SIZE, SIZE * Block.SIZE, SIZE * Block.SIZE);
		blocks = new Block[SIZE * SIZE];
		
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				blocks[x + y * SIZE] = new Block(BlockType.DIRT, xPos * SIZE + x, yPos * SIZE + y);
			}
		}
	}
	
	public void tick() {
		for (Block block : blocks) {
			block.tick();
		}
	}
	
	public void render(Graphics2D g) {
		for (Block block : blocks) {
			block.render(g);
		}
	}
	
	public void placeBlock(Block block, int xPos, int yPos) {
		if (xPos < 0 || xPos >= SIZE || yPos < 0 || yPos >= SIZE) {
			System.err.println("Tried to place a block outside of the chunk!");
			return;
		}
		blocks[xPos + yPos * SIZE] = block;
	}
	
	public Block getBlock(int xPos, int yPos) {
		if (xPos < 0 || xPos >= SIZE || yPos < 0 || yPos >= SIZE) return null;
		return blocks[xPos + yPos * SIZE];
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public boolean containsCoord(double x, double y) {
		int minX = xPos * SIZE;
		int minY = yPos * SIZE;
		int maxX = minX + SIZE * Block.SIZE;
		int maxY = minY + SIZE * Block.SIZE;
		
		return x >= minX && x <= maxX && y >= minY && y <= maxY;
	}
}
