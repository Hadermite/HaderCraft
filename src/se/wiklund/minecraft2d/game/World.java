package se.wiklund.minecraft2d.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.game.block.Block;
import se.wiklund.minecraft2d.game.entity.Entity;
import se.wiklund.minecraft2d.game.entity.Player;

public class World {

	public static final int HEIGHT = 256;
	private static final int PRELOAD_CHUNKS = 16;
	
	private int chunksY;
	private List<Chunk> chunks;
	private List<Entity> entities;
	private Player player;
	private Camera camera;
	private Rectangle screenBounds;

	public World() {
		chunksY = HEIGHT / Chunk.SIZE;
		chunks = new ArrayList<>();
		entities = new ArrayList<>();
		player = new Player();
		camera = new Camera();
		screenBounds = new Rectangle();
		
		for (int xPos = -PRELOAD_CHUNKS; xPos < PRELOAD_CHUNKS; xPos++) {
			for (int yPos = 0; yPos < chunksY; yPos++) {
				chunks.add(new Chunk(xPos, yPos));
			}
		}
		
		System.out.println("Preloaded " + chunks.size() + " chunks!");
		
		entities.add(player);
	}

	public void tick() {
		for (Chunk chunk : chunks) {
			if (chunk.getBounds().intersects(screenBounds)) {
				chunk.tick();
			}
		}
		
		for (Entity entity : entities) {
			entity.tick();
		}
		
		camera.tick(player);

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
		
		for (Entity entity : entities) {
			entity.render(g);
		}
		
		g.translate(-camera.getRenderOffsetX(), -camera.getRenderOffsetY());
	}

	public void placeBlock(Block block, double x, double y) {
		for (Chunk chunk : chunks) {
			if (chunk.containsCoord(x, y)) {
				chunk.placeBlock(block, (int) (x / Block.SIZE) - (chunk.getXPos() * Chunk.SIZE), (int) (y / Block.SIZE) - (chunk.getXPos() * Chunk.SIZE));
			}
		}
	}

	public Block getBlock(double x, double y) {
		for (Chunk chunk : chunks) {
			if (chunk.containsCoord(x, y)) {
				return chunk.getBlock((int) (x / Block.SIZE) - (chunk.getXPos() * Chunk.SIZE), (int) (y / Block.SIZE) - (chunk.getXPos() * Chunk.SIZE));
			}
		}
		return null;
	}
}
