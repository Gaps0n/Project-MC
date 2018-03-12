
package fr.earthfight.launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.theshark34.openauth.AuthenticationException;
import fr.theshark34.swinger.colored.SColoredBar;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.openlauncherlib.launcher.util.UsernameSaver;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.textured.STexturedButton;

@SuppressWarnings("serial")
public class LauncherPanel extends JPanel implements SwingerEventListener {
	
	private Image background = Swinger.getResource("background.png");
	
	private UsernameSaver saver = new UsernameSaver(Launcher.EF_INFOS);
	
	private JTextField usernameField = new JTextField(saver.getUsername(""));
	private JPasswordField passwordField = new JPasswordField();

	private STexturedButton playButton = new StexturedButton(Swinger.getResource("play.png"));
	private STexturedButton quitButton = new StexturedButton(Swinger.getResource("quit.png"));
	private STexturedButton hideButton = new StexturedButton(Swinger.getResource("hide.png"));

	private SColoredBar progressBar = new SColoredBar(getTransparentWhite(100), getTransparentWhite(10));
	private JLabel infoLabel = new JLabel("CLique");
	
	public LauncherPanel() {

		this.setLayout(null);
		
		usernameField.setForeground(Color.WHITE);
		usernameField.setFont(usernameField.getFont().deriveFont(20F));
		usernameField.setCaretColor(Color.WHITE);
		usernameField.setBorder(null);
		usernameField.setOpaque(false);
		usernameField.setBounds(11, 11, 11, 11);
		this.add(usernameField);

		passwordField.setForeground(Color.WHITE);
		passwordField.setFont(passwordField.getFont());
		passwordField.setCaretColor(Color.WHITE);
		passwordField.setBorder(null);
		passwordField.setOpaque(false);
		passwordField.setBounds(11, 21, 11, 21);
		this.add(passwordField);

		playButton.setBounds(200, 200);
		playButton.addEventListener(this);
		this.add(playButton);

		quitButton.setBounds(971, 19);
		quitButton.addEventListener(this);
		this.add(quitButton);

		hideButton.setBounds(971, 30);
		hideButton.addEventListener(this);
		this.add(hideButton);


		progressBar.setBounds(12, 12, 12, 12);
		this.add(progressBar);

		infoLabel.setBounds(12, 12, 12, 12);
		infoLabel.setForeground(Color.WHITE);
		this.add(infoLabel);
	}

	@Overide
	public void onEvent (SwingerEvent e) {
		if (e.getSource() == playButton) {
			setFieldsEnabled(false);

			if (usernameField.getText().replaceAll(" ", "").length() == 0 || passwordField.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "Entre pseudo et mdp", "Erreur", JOptionPane.ERROR_MESSAGE);
				setFieldsEnabled(true);

				return;
			}

			Thread t = new Thread() {
				@Override
				public void run () {
					try {
						Launcher.auth(usernameField.getText(), passwordFieldField.getText());
					} catch (AuthenticationException e) {
						JOptionPane.showMessageDialog(LauncherPanel.this, "Impossible de se co :" + e.getErrorModel().getErrorMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
						setFieldsEnabled(true);

						return;
					}
				}
			}
		} else if (e.getSource() == quitButton) {
			System.exit(0);
		} else if (e.getSource() == hideButton) {
			LauncherFrame.getInstance().setState(JFrame.ICONIFIED) ;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	private void setFieldsEnabled(boolean enabled) {
		usernameField.setEnabled(enabled);
		passwordField.setEnabled(enabled);
		playButton.setEnabled(enabled);
	}

	public SColoredBar getProgressBar() {

		return progressBar;
	}

	public void setInfoText(String text) {
		infoLabel.settext(text);
	}
}
