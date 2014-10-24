package unomodding.canary.cynmap.data;

import java.io.File;

import net.canarymod.Canary;

public class Constants {
	public static final File canary = Canary.getWorkingDirectory();
	public static final File dataFolder = new File(canary, "dynmap");
}
