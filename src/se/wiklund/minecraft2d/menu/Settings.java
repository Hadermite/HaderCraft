package se.wiklund.minecraft2d.menu;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.minecraft2d.Assets;
import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.State;
import se.wiklund.minecraft2d.component.Button;
import se.wiklund.minecraft2d.component.CheckBox;
import se.wiklund.minecraft2d.component.Component;
import se.wiklund.minecraft2d.component.Label;
import se.wiklund.minecraft2d.handler.FileHandler;
import se.wiklund.minecraft2d.listener.CheckBoxListener;

public class Settings extends State {
	
	public static boolean fullscreen = true;
	public static boolean antiAliasing = true;
	public static boolean splashScreen = true;
	
	public static boolean fullscreenChangeQueued = false;
	
	private List<Component> components;
	
	private Label lblTitle;
	private CheckBox chkFullscreen, chkAA, chkSS;
	private Button btnBack;
	
	public Settings() {
		components = new ArrayList<Component>();
		
		int spaceY = 20 + Assets.FONT_CHECKBOX.getSize();
		lblTitle = new Label("Settings", Assets.FONT_TITLE, 0, Label.TITLE_Y);
		chkFullscreen = new CheckBox("Fullscreen", 500, 250 + spaceY * 0);
		chkAA = new CheckBox("Anti-Aliasing", 500, 250 + spaceY * 1);
		chkSS = new CheckBox("Splash Screen", 500, 250 + spaceY * 2);
		btnBack = new Button("Back", 0, Button.BTN_BACK_Y);
		
		lblTitle.centerX();
		btnBack.centerX();
		
		chkFullscreen.setChecked(Main.getWindow().isFullscreen());
		chkAA.setChecked(antiAliasing);
		chkSS.setChecked(splashScreen);
		
		SettingsListener listener = new SettingsListener();
		chkFullscreen.addListener(listener);
		chkAA.addListener(listener);
		chkSS.addListener(listener);
		
		components.add(lblTitle);
		components.add(chkFullscreen);
		components.add(chkAA);
		components.add(chkSS);
		components.add(btnBack);
	}
	
	@Override
	public void tick() {
		for (Component component : components) {
			component.tick();
		}
	}

	@Override
	public void render(Graphics2D g) {
		for (Component component : components) {
			component.render(g);
		}
	}

	@Override
	public void onMouseClick(int button, int x, int y) {
		if (button == 1) {
			if (btnBack.containsCoord(x, y)) {
				Main.setState(new Menu());
			}
		}
	}
	
	class SettingsListener implements CheckBoxListener {

		@Override
		public void onChangeValue(CheckBox box, boolean checked) {
			if (box == chkFullscreen && !fullscreenChangeQueued) {
				fullscreenChangeQueued = true;
				fullscreen = !Main.getWindow().isFullscreen();
				chkFullscreen.setChecked(fullscreen);
			}
			if (box == chkAA) {
				antiAliasing = checked;
			}
			if (box == chkSS) {
				splashScreen = checked;
			}
			FileHandler.save();
		}
	}
}
