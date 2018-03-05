package fr.earthfight.launcher;

import java.io.File;

import fr.theshark34.openlauncherlib.launcher.GameInfos;
import fr.theshark34.openlauncherlib.launcher.GameTweak;
import fr.theshark34.openlauncherlib.launcher.GameType;
import fr.theshark34.openlauncherlib.launcher.GameVersion;

public class Launcher {
	
	public static final GameVersion EF_VERSION = new GameVersion("1.7.10", GameType.V1_7_10);
	public static final GameInfos EF_INFOS = new GameInfos("EarthFight", EF_VERSION, true, new GameTweak[] (GameTweak.FORGE));
	public static final File EF_DIR = EF_INFOS.getGameDir();
}
