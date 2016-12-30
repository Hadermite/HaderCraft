package se.wiklund.minecraft2d.game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.minecraft2d.Assets;
import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.component.Button;
import se.wiklund.minecraft2d.component.Component;
import se.wiklund.minecraft2d.component.Label;
import se.wiklund.minecraft2d.menu.Menu;

public class PauseMenu {
	
	private Game game;
	private List<Component> components;
	private Button btnResume, btnMenu;
	
	public PauseMenu(Game game) {
		this.game = game;
		components = new ArrayList<Component>();
		
		int startX = 400;
		Label lblTitle = new Label("Paused", Assets.FONT_TITLE, 0, Label.TITLE_Y);
		btnResume = new Button("Resume", 0, startX + 175 * 0);
		btnMenu = new Button("Back to Menu", 0, startX + 175 * 1);
		
		lblTitle.centerX();
		btnResume.centerX();
		btnMenu.centerX();
		
		components.add(lblTitle);
		components.add(btnResume);
		components.add(btnMenu);
	}
	
	public void tick() {
		for (Component component : components) {
			component.tick();
		}
	}
	
	public void render(Graphics2D g) {
		for (Component component : components) {
			component.render(g);
		}
	}
	
	public void onMouseClick(int button, int x, int y) {
		if (button != 1) return;
		if (btnResume.containsCoord(x, y)) {
			game.setPaused(false);
		}
		if (btnMenu.containsCoord(x, y)) {
			Main.setState(new Menu());
		}
	}
}
