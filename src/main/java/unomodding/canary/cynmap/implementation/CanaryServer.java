package unomodding.canary.cynmap.implementation;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import net.canarymod.Canary;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.TextFormat;
import net.canarymod.config.Configuration;

import org.dynmap.DynmapChunk;
import org.dynmap.DynmapWorld;
import org.dynmap.common.DynmapListenerManager.EventType;
import org.dynmap.common.DynmapPlayer;
import org.dynmap.common.DynmapServerInterface;
import org.dynmap.utils.MapChunkCache;

public class CanaryServer extends DynmapServerInterface
{
    private Server server;

    public CanaryServer(Server server) {
        this.server = server;
    }

    @Override
    public void broadcastMessage(String msg)
    {
        server.broadcastMessage(msg);
    }

    @Override
    public <T> Future<T> callSyncMethod(Callable<T> task)
    {
        return null;
    }

    @Override
    public boolean checkPlayerPermission(String player, String perm)
    {
        return server.getPlayer(player).hasPermission(perm);
    }

    @Override
    public Set<String> checkPlayerPermissions(String player, Set<String> perms)
    {
        return null;
    }

    @Override
    public MapChunkCache createMapChunkCache(DynmapWorld w, List<DynmapChunk> chunks, boolean blockdata,
            boolean highesty, boolean biome, boolean rawbiome)
    {
        return null;
    }

    @Override
    public String[] getBiomeIDs()
    {
        return null;
    }

    @Override
    public int getBlockIDAt(String wname, int x, int y, int z)
    {
        return server.getWorld(wname).getBlockAt(x, y, z).getIdDropped();
    }

    @Override
    public double getCacheHitRate()
    {
        return 0;
    }

    @Override
    public int getCurrentPlayers()
    {
        return server.getNumPlayersOnline();
    }

    @Override
    public Set<String> getIPBans()
    {
        return null;
    }

    @Override
    public int getMaxPlayers()
    {
        return server.getMaxPlayers();
    }

    @Override
    public DynmapPlayer getOfflinePlayer(String player)
    {
        return new CanaryPlayer(server.getPlayer(player));
    }

    @Override
    public DynmapPlayer[] getOnlinePlayers()
    {
        DynmapPlayer[] players = new DynmapPlayer[server.getPlayerList().size()];
        int i = 0;
        for (Player p : server.getPlayerList()) {
            players[i] = new CanaryPlayer(p);
            i++;
        }
        return players;
    }

    @Override
    public DynmapPlayer getPlayer(String player)
    {
        return new CanaryPlayer(server.getPlayer(player));
    }

    @Override
    public String getServerIP()
    {
        return Configuration.getServerConfig().getBindIp();
    }

    @Override
    public String getServerName()
    {
        return server.getName();
    }

    @Override
    public double getServerTPS()
    {
        return server.getTicksPerSecond();
    }

    @Override
    public DynmapWorld getWorldByName(String world)
    {
        return new CanaryWorld(server.getWorld(world));
    }

    @Override
    public boolean isPlayerBanned(String player)
    {
        return Canary.bans().isBanned(player);
    }

    @Override
    public void reload()
    {

    }

    @Override
    public boolean requestEventNotification(EventType type)
    {
        return false;
    }

    @Override
    public void resetCacheStats()
    {

    }

    @Override
    public void scheduleServerTask(Runnable run, long delay)
    {

    }

    @Override
    public boolean sendWebChatEvent(String source, String name, String msg)
    {
        return false;
    }

    @Override
    public String stripChatColor(String s)
    {
        return TextFormat.removeFormatting(s);
    }
}
