package se.wiklund.minecraft2d.handler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import se.wiklund.minecraft2d.menu.Settings;

public class FileHandler {
	
	public static final String DIR;
	
	private static final String K_FULLSCREEN = "fullscreen";
	private static final String K_ANTI_ALIASING = "anti-aliasing";
	private static final String K_SPLASH_SCREEN = "splash-screen";
	
	private static SaveFile config;
	private static Properties properties;
	
	static {
		String os = System.getProperty("os.name").toUpperCase();
		if (os.contains("WIN")) {
			DIR = System.getenv("APPDATA") + "\\Wiklund PALL\\Minecraft2D";
		} else if (os.contains("MAC")) {
			DIR = System.getProperty("user.home") + "/Library/Wiklund PALL/Minecraft2D";
		} else {
			DIR = System.getProperty("user.home") + "/Wiklund PALL/Minecraft2D";
		}
		
		config = new SaveFile("config.properties");
		properties = new Properties();
		
		if (config.isJustCreated()) {
			saveConfig();
		}
	}
	
	public static void load() {
		try {
			InputStream input = new FileInputStream(config.getFile());
			properties.load(input);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Settings.fullscreen = (properties.getProperty(K_FULLSCREEN, "true").trim().equalsIgnoreCase("true"));
		Settings.antiAliasing = (properties.getProperty(K_ANTI_ALIASING, "true").trim().equalsIgnoreCase("true"));
		Settings.splashScreen = (properties.getProperty(K_SPLASH_SCREEN, "true").trim().equalsIgnoreCase("true"));
	}
	
	public static void save() {
		saveConfig();
	}
	
	private static void saveConfig() {
		try {
			InputStream input = new FileInputStream(config.getFile());
			properties.load(input);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		properties.setProperty(K_FULLSCREEN, Settings.fullscreen + "");
		properties.setProperty(K_ANTI_ALIASING, Settings.antiAliasing + "");
		properties.setProperty(K_SPLASH_SCREEN, Settings.splashScreen + "");
		
		try {
			OutputStream output = new FileOutputStream(config.getFile());
			properties.store(output, null);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
