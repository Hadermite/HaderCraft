package se.wiklund.minecraft2d.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.component.Label;
import se.wiklund.minecraft2d.game.block.Block;
import se.wiklund.minecraft2d.game.entity.Entity;
import se.wiklund.minecraft2d.game.entity.Player;

public class World {

	public static final int HEIGHT = Chunk.SIZE * 16;
	private static final int PRELOAD_CHUNKS = 16;

	private int chunksY;
	private List<Chunk> chunks;
	private List<Entity> entities;
	private Player player;
	private Camera camera;
	private Rectangle screenBounds;
	private Label lblPlayerCoords;
	private double lastXCoord, lastYCoord;

	public World(Game game) {

		chunksY = HEIGHT / Chunk.SIZE;
		chunks = new ArrayList<>();
		entities = new ArrayList<>();
		player = new Player();
		camera = new Camera();
		screenBounds = new Rectangle();
		lblPlayerCoords = game.getHUD().addSidebarRow("");

		for (int xPos = -PRELOAD_CHUNKS; xPos < PRELOAD_CHUNKS; xPos++) {
			for (int yPos = 0; yPos < chunksY; yPos++) {
				chunks.add(new Chunk(xPos, yPos));
			}
		}

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

			checkCollision(entity);
		}

		camera.tick(player);

		if (lastXCoord != player.getX() || lastYCoord != player.getY()) {
			lastXCoord = player.getX();
			lastYCoord = player.getY();
			lblPlayerCoords.setText("Player X, Y: " + (int) lastXCoord + ", " + (int) lastYCoord);
		}

		screenBounds.setBounds((int) -camera.getRenderOffsetX(), (int) -camera.getRenderOffsetY(), Main.WIDTH,
				Main.HEIGHT);
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
			if (entity != camera.getFocusEntity()) {
				entity.render(g);
			} else {
				g.translate(-camera.getRenderOffsetX(), -camera.getRenderOffsetY());
				g.drawImage(entity.getTexture(), (Main.WIDTH - entity.getWidth()) / 2,
						(Main.HEIGHT - entity.getHeight()) / 2, entity.getWidth(), entity.getHeight(), null);
				g.translate(camera.getRenderOffsetX(), camera.getRenderOffsetY());
			}
		}

		g.translate(-camera.getRenderOffsetX(), -camera.getRenderOffsetY());
	}

	public void onMouseClick(int button, int x, int y) {
		double xt = x - camera.getRenderOffsetX();
		double yt = y - camera.getRenderOffsetY();
		Block block = getBlock(xt, yt);
		if (block == null)
			return;

		block.setType(null);
	}

	public void placeBlock(Block block, double x, double y) {
		for (Chunk chunk : chunks) {
			if (chunk.containsCoord(x, y)) {
				chunk.placeBlock(block, (int) (x / Block.SIZE) - (chunk.getXPos() * Chunk.SIZE),
						(int) (y / Block.SIZE) - (chunk.getXPos() * Chunk.SIZE));
			}
		}
	}

	public Block getBlock(double x, double y) {
		for (Chunk chunk : chunks) {
			if (chunk.containsCoord(x, y)) {
				int xPos = 0;
				if (x >= 0)
					xPos = (int) (x / Block.SIZE);
				else
					xPos = (int) (x / Block.SIZE - 1);
				return chunk.getBlock(xPos - (chunk.getXPos() * Chunk.SIZE),
						(int) (y / Block.SIZE) - (chunk.getYPos() * Chunk.SIZE));
			}
		}
		return null;
	}

	private void checkCollision(Entity entity) {
		Rectangle bottom = entity.getBoundsBottom();
		Rectangle top = entity.getBoundsTop();
		Rectangle left = entity.getBoundsLeft();
		Rectangle right = entity.getBoundsRight();
		boolean inAir = true;

		for (Chunk chunk : chunks) {
			if (chunk.isEntityInside(entity)) {
				for (Block block : chunk.getBlocks()) {
					if (block.getType() == null)
						continue;
					if (bottom.intersects(block.getBounds())) {
						if (entity.getVelY() > 0)
							entity.setVelY(0);
						inAir = false;
						entity.setY(block.getY() - entity.getHeight() + 1);
					}
					if (top.intersects(block.getBounds())) {
						if (entity.getVelY() < 0)
							entity.setVelY(0);
						entity.setY(block.getY() + Block.SIZE);
					}
					if (left.intersects(block.getBounds())) {
						if (entity.getVelX() < 0)
							entity.setVelX(0);
						entity.setX(block.getX() + Block.SIZE);
					}
					if (right.intersects(block.getBounds())) {
						if (entity.getVelX() > 0)
							entity.setVelX(0);
						entity.setX(block.getX() - entity.getWidth());
					}
				}
			}
		}

		entity.setInAir(inAir);
	}
}
