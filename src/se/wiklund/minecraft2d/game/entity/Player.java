package se.wiklund.minecraft2d.game.entity;

import java.awt.event.KeyEvent;

import se.wiklund.minecraft2d.Assets;
import se.wiklund.minecraft2d.game.block.Block;
import se.wiklund.minecraft2d.input.Keyboard;
import se.wiklund.minecraft2d.types.Direction;

public class Player extends Entity {
	
	public Player() {
		super(Assets.PLAYER, 0, 126 * Block.SIZE, Block.SIZE, Block.SIZE * 2);
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
}
