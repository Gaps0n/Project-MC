package fr.earthfight.launcher; 

import javax.swing.JFrame;

import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.util.WindowMover;

@SuppressWarnings("serial")
public class LauncherFrame extends JFrame {
	
	private static LauncherFrame instance;
	private LauncherPanel launcherPanel;
	
	public LauncherFrame () {
		this.setTitle("EarthFight");
		this.setSize(975, 625);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setIconImage(Swinger.getResource("pack.png"));
		this.setContentPane(launcherPanel = new LauncherPanel());
		
		WindowMover mover = new WindowMover(this);
		this.addMouseListener(mover);
		this.addMouseMotionListener(mover);
		
		this.setVisible(true);
	}

	private void setContentPane(LauncherPanel launcherPanel2) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		Swinger.setSystemLookNFeel();
		Swinger.setResourcePath("/fr/earthfight/launcher/resources/");
		
		instance = new LauncherFrame ();
	}
	
	public static LauncherFrame getInstance () {
		
		return instance;
	}
	
	public LauncherPanel getLauncherPanel() {
		
		return this.launcherPanel;
	}

}
