package se.wiklund.minecraft2d.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import se.wiklund.minecraft2d.Main;

public class Window extends JFrame {
	
	private Screen screen;
	private boolean fullscreen;
	
	public Window(boolean fullscreen) {
		this.fullscreen = fullscreen;
		screen = new Screen();
		
		setTitle(Main.NAME);
		if (!fullscreen) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension windowSize = new Dimension(screenSize.width / 2, screenSize.height / 2);
			setSize(windowSize);
			setPreferredSize(windowSize);
			setMinimumSize(windowSize);
			setMaximumSize(windowSize);
			setLocationRelativeTo(null);
		}
		getContentPane().setLayout(new GridBagLayout());
		getContentPane().add(screen);
		if (fullscreen) {
			setUndecorated(true);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		
		getContentPane().setBackground(Color.BLACK);
		
		addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				Main.updateCanvasSize();
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				
			}
		});
	}
	
	public boolean isFullscreen() {
		return fullscreen;
	}
	
	public Screen getScreen() {
		return screen;
	}
}
