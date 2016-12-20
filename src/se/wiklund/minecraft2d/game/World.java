package se.wiklund.minecraft2d.game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.game.block.Block;

public class World {
	
	private int size;
	private Chunk[] chunks;
	private Camera camera;
	private Rectangle screenBounds;
	
	public World(int size) {
		this.size = size;
		chunks = new Chunk[(size / Chunk.SIZE) * (size / Chunk.SIZE)];
		camera = new Camera();
		screenBounds = new Rectangle();
		
		for (int xPos = 0; xPos < size / Chunk.SIZE; xPos++) {
			for (int yPos = 0; yPos < size / Chunk.SIZE; yPos++) {
				chunks[xPos + yPos * (size / Chunk.SIZE)] = new Chunk(xPos, yPos);
			}
		}
	}
	
	public void tick() {
		for (Chunk chunk : chunks) {
			if (chunk.getBounds().intersects(screenBounds)) {
				chunk.tick();
			}
		}
		
		screenBounds.setBounds((int) camera.getRenderOffsetX(), (int) camera.getRenderOffsetY(), Main.WIDTH, Main.HEIGHT);
	}
	
	public void render(Graphics2D g) {
		g.translate(camera.getRenderOffsetX(), camera.getRenderOffsetY());
		for (Chunk chunk : chunks) {
			if (chunk.getBounds().intersects(screenBounds)) {
				chunk.render(g);
			}
		}
		g.translate(-camera.getRenderOffsetX(), -camera.getRenderOffsetY());
	}
	
	public void placeBlock(Block block, int xPos, int yPos) {
		if (xPos < 0 || xPos >= size || yPos < 0 || yPos >= size) {
			System.err.println("Tried to place a block outside of the world!");
			return;
		}
		Chunk chunk = chunks[(xPos / Chunk.SIZE) + (yPos / Chunk.SIZE) * (size / Chunk.SIZE)];
		chunk.placeBlock(block, xPos - (chunk.getXPos() * Chunk.SIZE), yPos - (chunk.getXPos() * Chunk.SIZE));
	}
	
	public Block getBlock(int xPos, int yPos) {
		if (xPos < 0 || xPos >= size || yPos < 0 || yPos >= size) return null;
		Chunk chunk = chunks[(xPos / Chunk.SIZE) + (yPos / Chunk.SIZE) * (size / Chunk.SIZE)];
		return chunk.getBlock(xPos - (chunk.getXPos() * Chunk.SIZE), yPos - (chunk.getXPos() * Chunk.SIZE));
	}
}
