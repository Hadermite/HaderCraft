package se.wiklund.minecraft2d.game.entity;

import java.awt.event.KeyEvent;

import se.wiklund.minecraft2d.Assets;
import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.game.block.Block;
import se.wiklund.minecraft2d.input.Keyboard;

public class Player extends Entity {
	
	private static final double SPEED = 10.0 / Main.TICKRATE;
	
	public Player() {
		super(Assets.PLAYER, 0, 126, Block.SIZE, Block.SIZE * 2);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (Keyboard.isKeyDown(KeyEvent.VK_A)) {
			move(-SPEED, 0);
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_W)) {
			move(0, -SPEED);
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_D)) {
			move(SPEED, 0);
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_S)) {
			move(0, SPEED);
		}
	}
}
