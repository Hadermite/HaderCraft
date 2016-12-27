package se.wiklund.minecraft2d;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.minecraft2d.component.Button;
import se.wiklund.minecraft2d.component.Component;
import se.wiklund.minecraft2d.component.Label;
import se.wiklund.minecraft2d.game.Game;

public class Menu extends State {

	private List<Component> components;

	private Label lblTitle;
	private Button btnPlay, btnSettings, btnQuit;

	public Menu() {
		components = new ArrayList<>();

		lblTitle = new Label("Minecraft2D", Assets.FONT_TITLE, 0, 10);
		btnPlay = new Button("Play", 0, 75);
		btnSettings = new Button("Settings", 0, 115);
		btnQuit = new Button("Quit", 0, 155);

		btnPlay.centerX();
		lblTitle.centerX();
		btnSettings.centerX();
		btnQuit.centerX();

		components.add(lblTitle);
		components.add(btnPlay);
		components.add(btnSettings);
		components.add(btnQuit);
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
			if (btnPlay.containsCoord(x, y)) {
				Main.setState(new Game());
			}
			if (btnSettings.containsCoord(x, y)) {
				System.out.println("Settings");
			}
			if (btnQuit.containsCoord(x, y)) {
				System.exit(0);
			}
		}
	}
}
