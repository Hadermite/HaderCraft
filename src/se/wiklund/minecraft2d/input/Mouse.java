package se.wiklund.minecraft2d.input;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import se.wiklund.minecraft2d.Main;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	private static double x, y;
	private static boolean down;
	private static Rectangle bounds = new Rectangle();
	private static List<MouseReader> readers = new CopyOnWriteArrayList<MouseReader>();
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX() / Main.scale;
		y = e.getY() / Main.scale;
		bounds.setBounds((int) x, (int) y, 1, 1);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX() / Main.scale;
		y = e.getY() / Main.scale;
		bounds.setBounds((int) x, (int) y, 1, 1);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		down = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		down = false;
		
		for (MouseReader reader : readers) {
			reader.onMouseClick(e.getButton(), (int) x, (int) y);
		}
	}

	public static double getX() {
		return x;
	}

	public static double getY() {
		return y;
	}

	public static boolean isDown() {
		return down;
	}
	
	public static void addMouseReader(MouseReader reader) {
		readers.add(reader);
	}
	
	public static void removeMouseReader(MouseReader reader) {
		readers.remove(reader);
	}
	
	public static Rectangle getBounds() {
		return bounds;
	}
}
