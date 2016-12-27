package se.wiklund.minecraft2d.util;

import java.awt.Font;
import java.awt.Graphics2D;

public class UIUtils {
	
	private static Graphics2D g;
	
	public static void setGraphics(Graphics2D g) {
		UIUtils.g = g;
	}
	
	public static int getStringWidth(String string, Font font) {
		return g.getFontMetrics(font).stringWidth(string);
	}
}
