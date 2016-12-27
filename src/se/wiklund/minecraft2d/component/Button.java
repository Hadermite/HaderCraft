package se.wiklund.minecraft2d.component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import se.wiklund.minecraft2d.Assets;

public class Button extends Component {
	
	public static final int BTN_BACK_Y = 210;
	
	protected Label label;
	protected BufferedImage background;
	
	public Button(String text, double x, double y) {
		this(text, x, y, 140, 30);
	}
	
	public Button(String text, double x, double y, int width, int height) {
		super(x, y, width, height);
		label = new Label(text, Assets.FONT_BUTTON, x, y);
		calculateLabel();
	}
	
	@Override
	public void tick() {
		label.tick();
	}
	
	@Override
	public void render(Graphics2D g) {
		if (background != null) {
			g.drawImage(background, (int) x, (int) y, width, height, null);
		} else {
			g.setColor(Color.YELLOW);
			g.fillRect((int) x, (int) y, width, height);
		}
		label.render(g);
	}
	
	private void calculateLabel() {
		label.setX(x + (width - label.getWidth()) /  2);
		label.setY(y + (height - label.getHeight()) / 2);
	}
	
	@Override
	public void center() {
		super.center();
		calculateLabel();
	}
	
	@Override
	public void centerX() {
		super.centerX();
		calculateLabel();
	}
	
	@Override
	public void centerY() {
		super.centerY();
		calculateLabel();
	}
}
