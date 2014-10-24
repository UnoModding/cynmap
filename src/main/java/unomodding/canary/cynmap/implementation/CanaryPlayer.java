package unomodding.canary.cynmap.implementation;

import java.net.InetSocketAddress;

import net.canarymod.api.entity.living.humanoid.Player;

import org.dynmap.DynmapLocation;
import org.dynmap.common.DynmapPlayer;

public class CanaryPlayer implements DynmapPlayer
{
    private Player player;

    public CanaryPlayer(Player player) {
        this.player = player;
    }

    public boolean hasPermissionNode(String perm)
    {
        return player.hasPermission(perm);
    }

    public boolean hasPrivilege(String arg0)
    {
        return false;
    }

    public boolean isConnected()
    {
        return player.isOnline();
    }

    public boolean isOp()
    {
        return player.isOperator();
    }

    public void sendMessage(String msg)
    {
        player.message(msg);
    }

    public InetSocketAddress getAddress()
    {
        return null;
    }

    public int getArmorPoints()
    {
        return 0;
    }

    public DynmapLocation getBedSpawnLocation()
    {
        return new CanaryLocation(player.getSpawnPosition());
    }

    public String getDisplayName()
    {
        return player.getDisplayName();
    }

    public long getFirstLoginTime()
    {
        return 0;
    }

    public int getHealth()
    {
        return 0;
    }

    public long getLastLoginTime()
    {
        return 0;
    }

    public DynmapLocation getLocation()
    {
        return new CanaryLocation(player.getLocation());
    }

    public String getName()
    {
        return player.getName();
    }

    public int getSortWeight()
    {
        return 0;
    }

    public String getWorld()
    {
        return player.getWorld().getName();
    }

    public boolean isInvisible()
    {
        return player.isInvisible();
    }

    public boolean isOnline()
    {
        return player.isOnline();
    }

    public boolean isSneaking()
    {
        return player.isSneaking();
    }

    public void setSortWeight(int arg0)
    {

    }
}
