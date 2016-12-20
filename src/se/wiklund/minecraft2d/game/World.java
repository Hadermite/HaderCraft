package se.wiklund.minecraft2d.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.game.block.Block;
import se.wiklund.minecraft2d.input.Keyboard;

public class World {

	private int size;
	private int sizeChunks;
	private Chunk[] chunks;
	private Camera camera;
	private Rectangle screenBounds;

	public World(int size) {
		this.size = size;
		sizeChunks = size / Chunk.SIZE;
		chunks = new Chunk[sizeChunks * sizeChunks];
		camera = new Camera();
		screenBounds = new Rectangle();

		for (int xPos = 0; xPos < sizeChunks; xPos++) {
			for (int yPos = 0; yPos < sizeChunks; yPos++) {
				chunks[xPos + yPos * sizeChunks] = new Chunk(xPos, yPos);
			}
		}
	}

	public void tick() {
		for (Chunk chunk : chunks) {
			if (chunk.getBounds().intersects(screenBounds)) {
				chunk.tick();
			}
		}

		double x = camera.getX();
		double y = camera.getY();
		
		if (Keyboard.isKeyDown(KeyEvent.VK_A)) {
			x += 10;
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_W)) {
			y += 10;
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_D)) {
			x -= 10;
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_S)) {
			y -= 10;
		}
		
		camera.tick(x, y);

		screenBounds.setBounds((int) -camera.getRenderOffsetX(), (int) -camera.getRenderOffsetY(), Main.WIDTH, Main.HEIGHT);
	}

	public void render(Graphics2D g) {
		g.translate(camera.getRenderOffsetX(), camera.getRenderOffsetY());
		for (Chunk chunk : chunks) {
			if (chunk.getBounds().intersects(screenBounds)) {
				chunk.render(g);
				g.setColor(Color.YELLOW);
				g.draw(chunk.getBounds());
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
		if (xPos < 0 || xPos >= size || yPos < 0 || yPos >= size)
			return null;
		Chunk chunk = chunks[(xPos / Chunk.SIZE) + (yPos / Chunk.SIZE) * (size / Chunk.SIZE)];
		return chunk.getBlock(xPos - (chunk.getXPos() * Chunk.SIZE), yPos - (chunk.getXPos() * Chunk.SIZE));
	}
}
