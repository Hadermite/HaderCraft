package se.wiklund.minecraft2d.game.entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import se.wiklund.minecraft2d.Assets;
import se.wiklund.minecraft2d.game.block.Block;
import se.wiklund.minecraft2d.input.Keyboard;
import se.wiklund.minecraft2d.types.Direction;

public class Player extends Entity {
	
	public Player() {
		super(Assets.PLAYER, 0, 127 * Block.SIZE);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (Keyboard.isKeyDown(KeyEvent.VK_A)) {
			move(Direction.LEFT);
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_D)) {
			move(Direction.RIGHT);
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			jump();
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		if (facing == Direction.LEFT) {
			super.render(g);
		} else {
			g.drawImage(texture, (int) x + width, (int) y, -width, height, null);
		}
	}
}
