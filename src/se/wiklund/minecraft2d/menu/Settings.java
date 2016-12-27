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
import se.wiklund.minecraft2d.listener.CheckBoxListener;

public class Settings extends State {
	
	public static boolean antialiasing;
	
	private List<Component> components;
	
	private Label lblTitle;
	private CheckBox chkFullscreen, chkAA;
	private Button btnBack;
	
	public Settings() {
		components = new ArrayList<>();
		
		lblTitle = new Label("Settings", Assets.FONT_TITLE, 0, 10);
		chkFullscreen = new CheckBox("Fullscreen", 50, 60);
		chkAA = new CheckBox("Anti-Aliasing", 50, 80);
		btnBack = new Button("Back", 0, Button.BTN_BACK_Y);
		
		lblTitle.centerX();
		btnBack.centerX();
		
		chkFullscreen.setChecked(Main.getWindow().isFullscreen());
		chkAA.setChecked(antialiasing);

		chkFullscreen.addListener(new SettingsListener());
		chkAA.addListener(new SettingsListener());
		
		components.add(lblTitle);
		components.add(chkFullscreen);
		components.add(chkAA);
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
			if (box == chkFullscreen) {
				Main.createNewWindow(checked);
			}
			if (box == chkAA) {
				antialiasing = checked;
			}
		}
	}
}
