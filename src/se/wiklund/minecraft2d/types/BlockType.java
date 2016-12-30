package se.wiklund.minecraft2d.types;

import java.awt.image.BufferedImage;

import se.wiklund.minecraft2d.Assets;

public enum BlockType {
	
	DIRT("Dirt", Assets.BLOCK_DIRT),
	GRASS("Grass", Assets.BLOCK_GRASS);
	
	private String name;
	private BufferedImage texture;
	
	private BlockType(String name, BufferedImage texture) {
		this.name = name;
		this.texture = texture;
	}
	
	public String getName() {
		return name;
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
}
