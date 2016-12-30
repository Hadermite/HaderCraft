package se.wiklund.minecraft2d.util;

import se.wiklund.minecraft2d.game.Camera;

public class WorldUtils {

	private static Camera camera;
	
	public static void setCamera(Camera camera) {
		WorldUtils.camera = camera;
	}
	
	public static double getWorldX(double screenX) {
		if (camera == null) return -1;
		return screenX - camera.getRenderOffsetX();
	}
	
	public static double getWorldY(double screenY) {
		if (camera == null) return -1;
		return screenY - camera.getRenderOffsetY();
	}
	
	public static double getScreenX(double worldX) {
		if (camera == null) return -1;
		return worldX + camera.getRenderOffsetX();
	}
	
	public static double getScreenY(double worldY) {
		if (camera == null) return -1;
		return worldY + camera.getRenderOffsetY();
	}
}
