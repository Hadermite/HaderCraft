package se.wiklund.minecraft2d.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.minecraft2d.Assets;
import se.wiklund.minecraft2d.Main;
import se.wiklund.minecraft2d.component.Button;
import se.wiklund.minecraft2d.component.Component;
import se.wiklund.minecraft2d.component.Label;
import se.wiklund.minecraft2d.game.block.Block;
import se.wiklund.minecraft2d.types.BlockType;

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
				}
			}
		}
	}
}
