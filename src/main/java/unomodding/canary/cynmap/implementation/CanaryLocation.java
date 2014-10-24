package unomodding.canary.cynmap.implementation;

import net.canarymod.api.world.position.Location;

import org.dynmap.DynmapLocation;

public class CanaryLocation extends DynmapLocation {
	public CanaryLocation(Location loc) {
		super(loc.getWorldName(), loc.getX(), loc.getY(), loc.getZ());
	}
}
