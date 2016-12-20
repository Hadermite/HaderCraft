package se.wiklund.minecraft2d.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(Loader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Font loadFont(String path, float size) {
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, Loader.class.getResourceAsStream(path)).deriveFont(size);;
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			return font;
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
