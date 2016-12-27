package se.wiklund.minecraft2d;

import java.awt.Font;
import java.awt.image.BufferedImage;

import se.wiklund.minecraft2d.util.Loader;

public class Assets {
	
	//Blocks
	public static final BufferedImage BLOCK_DIRT = Loader.loadImage("/textures/blocks/dirt.png");
	
	//Sprites
	public static final BufferedImage PLAYER = Loader.loadImage("/textures/sprites/player.png");
	
	//Fonts
	public static final Font FONT_SIDEBAR = Loader.loadFont("/fonts/comfortaa.ttf", 12);
	public static final Font FONT_BUTTON = Loader.loadFont("/fonts/comfortaa.ttf", 20);
	public static final Font FONT_TITLE = Loader.loadFont("/fonts/comfortaa.ttf", 40);
}
