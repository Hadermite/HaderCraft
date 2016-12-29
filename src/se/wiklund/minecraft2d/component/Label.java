package se.wiklund.minecraft2d.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import se.wiklund.minecraft2d.util.UIUtils;

public class Label extends Component {
	
	public static final int TITLE_Y = 70;
	
	protected String text;
	protected Font font;
	protected Color color;
	
	public Label(String text, Font font, double x, double y) {
		this(text, font, Color.BLACK, x, y);
	}
	
	public Label(String text, Font font, Color color, double x, double y) {
		super(x, y, UIUtils.getStringWidth(text, font), (int) ((double) font.getSize() / 10 * 8));
		this.text = text;
		this.font = font;
		this.color = color;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, (int) x, (int) y + height);
	}
	
	public void setText(String text) {
		this.text = text;
		width = UIUtils.getStringWidth(text, font);
	}
}
