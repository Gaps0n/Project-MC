package fr.earthfight.launcher;

import fr.theshark34.openauth.Authenticator;
import fr.theshark34.openauth.AuthenticationException;
import fr.theshark34.openauth.model.response.AuthResponse;
import fr.theshark34.openlauncherlib.launcher.AuthInfos;
import java.io.File;

import fr.theshark34.openauth.model.AuthAgent;
import fr.theshark34.openlauncherlib.launcher.GameInfos;
import fr.theshark34.openlauncherlib.launcher.GameTweak;
import fr.theshark34.openlauncherlib.launcher.GameType;
import fr.theshark34.openlauncherlib.launcher.GameVersion;
import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;

public class Launcher {
	
	public static final GameVersion EF_VERSION = new GameVersion("1.7.10", GameType.V1_7_10);
	public static final GameInfos EF_INFOS = new GameInfos("EarthFight", EF_VERSION, true, new GameTweak[] (GameTweak.FORGE));
	public static final File EF_DIR = EF_INFOS.getGameDir();

	private static AuthInfos authInfos;

	public static void auth(username, password) throws AuthenticationException {
		Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);
		AuthResponse response = new authenticator.authenticate(AuthAgent.MINECRAFT, username, password, "");
		AuthInfos = new AuthInfos(response.getSelectedProfile().getName(), response.getAccessToken(), response.getSelectedProfile().getId());
	}

	public static void update() throws Exception {
		SUpdate su = new SUpdate("http://", EF_DIR);
        BarAPI.
		su.start();
	}
}
