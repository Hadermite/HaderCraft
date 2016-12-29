package se.wiklund.minecraft2d.game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.minecraft2d.Assets;
import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.component.Component;
import se.wiklund.minecraft2d.component.Label;

public class HUD {

	private List<Component> components;
	private int sidebarY = 15;
	private Label lblTPS, lblFPS;
	private int lastTPS, lastFPS;
	
	public HUD() {
		components = new ArrayList<Component>();
		
		lblTPS = addSidebarRow("TPS: N/A");
		lblFPS = addSidebarRow("FPS: N/A");
	}
	
	public void tick() {
		for (Component component : components) {
			component.tick();
		}
		
		if (lastTPS != Main.getTPS()) {
			lastTPS = Main.getTPS();
			lblTPS.setText("TPS: " + lastTPS);
		}
		if (lastFPS != Main.getFPS()) {
			lastFPS = Main.getFPS();
			lblFPS.setText("FPS: " + lastFPS);
		}
	}
	
	public void render(Graphics2D g) {
		for (Component component : components) {
			component.render(g);
		}
	}
	
	public Label addSidebarRow(String text) {
		Label label = new Label(text, Assets.FONT_SIDEBAR, 7, sidebarY);
		components.add(label);
		sidebarY += Assets.FONT_SIDEBAR.getSize();
		return label;
	}
}
