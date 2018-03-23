package fr.earthfight.launcher;

import fr.theshark34.openauth.Authenticator;
import fr.theshark34.openauth.AuthenticationException;
import fr.theshark34.openauth.model.response.AuthResponse;
import fr.theshark34.openlauncherlib.launcher.*;

import java.io.File;

import fr.theshark34.openauth.model.AuthAgent;
import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.swinger.Swinger;

public class Launcher {

	public static final GameVersion EF_VERSION = new GameVersion("1.7.10", GameType.V1_7_10);
	public static final GameInfos EF_INFOS = new GameInfos("EarthFight", EF_VERSION, true, new GameTweak[] (GameTweak.FORGE));
	public static final File EF_DIR = EF_INFOS.getGameDir();

	private static AuthInfos authInfos;
	private static Thread updateThread;

	public static void auth(username, password) throws AuthenticationException {
		Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);
		AuthResponse response = new authenticator.authenticate(AuthAgent.MINECRAFT, username, password, "");
		AuthInfos = new AuthInfos(response.getSelectedProfile().getName(), response.getAccessToken(), response.getSelectedProfile().getId());
	}

	public static void update() throws Exception {
		SUpdate su = new SUpdate("http://", EF_DIR);

		updateThread = new Thread() {

			private int val;
			private int max;

			@Override
			public void run() {
				while (!this.isInterrupted()) {
					if (BarAPI.getNumberOfFileToDownload() == 0) {
						LauncherFrame.getInstance().getLauncherPanel().getInfoText("Verification");
						continue;
					}
					val = (int) (BarAPI.getNumberOfTotalDownloadedBytes() / 1000);
					max = (int) (BarAPI.getNumberOfTotalBytesToDownload() / 1000);

					LauncherFrame.getInstance().getLauncherPanel().getProgressBar().setMaximum(max);
					LauncherFrame.getInstance().getLauncherPanel().getProgressBar().setValue(val);

					LauncherFrame.getInstance().getLauncherPanel().getInfoText("Download" + BarAPI.getNumberOfDownloadedFiles() + "/" + BarAPI.getNumberOfFileToDownload() + " " +
							+ Swinger.percentage(val, max) + "%");

				}
			}
		};
		updateThread.start();

		su.start();
		updateThread.interrupt();
	}

	public static void launch() throws IOException {
		GameLauncher gameLauncher = new GameLauncher(EF_INFOS, GameFolder.BASIC, authInfos);
		process p = gameLauncher.launch();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		LauncherFrame.getInstance().setVisible(false);
		try {
			p.waitFor();
		} catch (InterruptedException e) {
		}
		System.exit(0);
	}

	public static void interruptThread() {

		updateThread.interrupt();
	}
}
