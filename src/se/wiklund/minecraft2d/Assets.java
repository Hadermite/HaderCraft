package se.wiklund.minecraft2d;

import java.awt.Font;
import java.awt.image.BufferedImage;

import se.wiklund.minecraft2d.util.Loader;

public class Assets {
	
	//Blocks
	public static final BufferedImage BLOCK_DIRT = Loader.loadImage("/textures/blocks/dirt.png");
	
	//Sprites
	public static final BufferedImage PLAYER = Loader.loadImage("/textures/sprites/player.png");
	
	//Other Textures
	public static final BufferedImage SPLASH_SCREEN = Loader.loadImage("/textures/splash_screen.png");
	
	//Fonts
	public static final Font FONT_SIDEBAR = Loader.loadFont("/fonts/comfortaa.ttf", 40);
	public static final Font FONT_BUTTON = Loader.loadFont("/fonts/comfortaa.ttf", 70);
	public static final Font FONT_TITLE = Loader.loadFont("/fonts/comfortaa.ttf", 140);
	public static final Font FONT_CHECKBOX = Loader.loadFont("/fonts/comfortaa.ttf", 50);
}
