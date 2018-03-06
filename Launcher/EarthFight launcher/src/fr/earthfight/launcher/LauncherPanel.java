
package fr.earthfight.launcher;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import fr.theshark34.swinger.Swinger;

@SuppressWarnings("serial")
public class LauncherPanel extends JPanel {
	
	private Image background = Swinger.getResource("background.png");
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
