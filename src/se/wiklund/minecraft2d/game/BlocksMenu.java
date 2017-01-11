package se.wiklund.minecraft2d.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.minecraft2d.Assets;
import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.component.Button;
import se.wiklund.minecraft2d.component.Component;
import se.wiklund.minecraft2d.component.Label;
import se.wiklund.minecraft2d.game.block.Block;
import se.wiklund.minecraft2d.input.Mouse;
import se.wiklund.minecraft2d.types.BlockType;
import se.wiklund.minecraft2d.util.UIUtils;

public class BlocksMenu {
	
	private static final int X = Main.WIDTH / 10 * 2;
	private static final int Y = Main.HEIGHT / 10 * 2;
	public static final int WIDTH = Main.WIDTH - X * 2;
	public static final int HEIGHT = Main.HEIGHT - Y * 2;
	
	private Game game;
	private List<Component> components;
	private BlockType[] blockTypes;
	
	public BlocksMenu(Game game) {
		this.game = game;
		components = new ArrayList<Component>();
		
		Label lblTitle = new Label("Block Selector", Assets.FONT_BUTTON, 0, 20);
		lblTitle.centerX(WIDTH);
		components.add(lblTitle);
		
		blockTypes = BlockType.values();
		
		for (int i = 0; i < blockTypes.length; i++) {
			BlockType blockType = blockTypes[i];
			Button btn = new Button(blockType.getTexture(), 50 + i * (int) (Block.SIZE * 1.5 + 30), 100, (int) (Block.SIZE * 1.5), (int) (Block.SIZE * 1.5));
			btn.setId(100 + i);
			components.add(btn);
		}
	}
	
	public void tick() {
		for (Component component : components) {
			component.tick();
		}
	}
	
	public void render(Graphics2D g) {
		g.translate(X, Y);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for (Component component : components) {
			component.render(g);
		}
		
		Rectangle mouse = (Rectangle) Mouse.getBounds().clone();
		mouse.setLocation(mouse.x - X, mouse.y - Y);
		for (Component component : components) {
			if (mouse.intersects(component.getBounds())) {
				if (component instanceof Button) {
					if (component.getId() >= 100 &&component.getId() < 200) {
						String name = blockTypes[component.getId() - 100].getName();
						int x = mouse.x + 25;
						int y = mouse.y - 10;
						int width = UIUtils.getStringWidth(name, Assets.FONT_SIDEBAR) + 40;
						int height = Assets.FONT_SIDEBAR.getSize() + 20;
						g.setColor(Color.GRAY);
						g.fillRect(x, y, width, height);
						g.setColor(Color.WHITE);
						g.setFont(Assets.FONT_SIDEBAR);
						g.drawString(name, x + 20, y + height - 16);
					}
				}
			}
		}
		
		g.translate(-X, -Y);
	}
	
	public void onMouseClick(int button, int x, int y) {
		int sx = x - X;
		int sy = y - Y;
		for (int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			if (c.containsCoord(sx, sy)) {
				if (c.getId() >= 100) {
					game.setSelectedBlock(blockTypes[c.getId() - 100]);
					game.updateHUD();
					game.closeBlockMenu();
				}
			}
		}
	}
}
