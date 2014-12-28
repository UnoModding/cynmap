/**
 * Copyright 2014 UnoModding
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package unomodding.canary.cynmap.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import net.canarymod.Canary;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.bansystem.Ban;
import net.canarymod.chat.TextFormat;
import net.canarymod.config.Configuration;
import net.canarymod.exceptions.InvalidPluginException;
import net.canarymod.exceptions.PluginLoadFailedException;
import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;

import net.visualillusionsent.utils.TaskManager;
import org.dynmap.DynmapChunk;
import org.dynmap.DynmapCommonAPIListener;
import org.dynmap.DynmapWorld;
import org.dynmap.common.DynmapListenerManager.EventType;
import org.dynmap.common.BiomeMap;
import org.dynmap.common.DynmapPlayer;
import org.dynmap.common.DynmapServerInterface;
import org.dynmap.utils.MapChunkCache;

public class CanaryServer extends DynmapServerInterface {
    private Server server;
    private Plugin plugin;

    public CanaryServer(Server server, Plugin plugin) {
        this.server = server;
        this.plugin = plugin;
    }

    @Override
    public void broadcastMessage(String msg) {
        server.broadcastMessage(msg);
    }

    @Override
    public <T> Future<T> callSyncMethod(Callable<T> task) {
        return TaskManager.submitTask(task);
    }

    @Override
    public boolean checkPlayerPermission(String player, String perm) {
        return server.getPlayer(player).hasPermission(perm);
    }

    @Override
    public Set<String> checkPlayerPermissions(String playername, Set<String> perms) {
        Set<String> permissions = new HashSet<String>();
        Player player = server.getPlayer(playername);
        if(!Canary.bans().isBanned(playername)) {
            for(String perm : perms) {
                if(player.hasPermission(perm)) {
                    permissions.add(perm);
                }
            }
        }
        return permissions;
    }

    @Override
    public MapChunkCache createMapChunkCache(DynmapWorld w, List<DynmapChunk> chunks, boolean blockdata,
            boolean highesty, boolean biome, boolean rawbiome) {
        return null;
    }

    @Override
    public String[] getBiomeIDs() {
        BiomeMap[] b = BiomeMap.values();
        String[] bname = new String[b.length];
        for (int i = 0; i < bname.length; i++)
            bname[i] = b[i].toString();
        return bname;
    }

    @Override
    public int getBlockIDAt(String wname, int x, int y, int z) {
        return server.getWorld(wname).getBlockAt(x, y, z).getTypeId();
    }

    @Override
    public double getCacheHitRate() {
        return 0;
    }

    @Override
    public int getCurrentPlayers() {
        return server.getNumPlayersOnline();
    }

    @Override
    public Set<String> getIPBans() {
        Set<String> ipBans = new HashSet<String>();
        for (Ban ban : Canary.bans().getAllBans()) {
            if (Canary.bans().isIpBanned(ban.getIp())) {
                ipBans.add(ban.getIp());
            }
        }
        return ipBans;
    }

    @Override
    public int getMaxPlayers() {
        return server.getMaxPlayers();
    }

    @Override
    public DynmapPlayer getOfflinePlayer(String player) {
        Player p = Canary.getServer().getPlayer(player);
        if (p != null) {
            return new CanaryPlayer(p);
        }
        return null;
    }

    @Override
    public DynmapPlayer[] getOnlinePlayers() {
        DynmapPlayer[] players = new DynmapPlayer[server.getPlayerList().size()];
        int i = 0;
        for (Player p : server.getPlayerList()) {
            players[i] = new CanaryPlayer(p);
            i++;
        }
        return players;
    }

    @Override
    public DynmapPlayer getPlayer(String player) {
        Player p = Canary.getServer().getPlayer(player);
        if (p != null) {
            return new CanaryPlayer(p);
        }
        return null;
    }

    @Override
    public String getServerIP() {
        return Configuration.getServerConfig().getBindIp();
    }

    @Override
    public String getServerName() {
        return server.getName();
    }

    @Override
    public double getServerTPS() {
        return server.getTicksPerSecond();
    }

    @Override
    public DynmapWorld getWorldByName(String world) {
        return new CanaryWorld(server.getWorld(world));
    }

    @Override
    public boolean isPlayerBanned(String player) {
        return Canary.bans().isBanned(player);
    }

    @Override
    public void reload() {
        try {
            Canary.manager().reloadPlugin("cynmap");
        } catch (PluginLoadFailedException e) {
            Logman.getLogman("cynmap").error("Failed to reload", e);
        } catch (InvalidPluginException e) {
            Logman.getLogman("cynmap").error("Failed to reload", e);
        }
    }

    @Override
    public boolean requestEventNotification(EventType type) {
        return false;
    }

    @Override
    public void resetCacheStats() {

    }

    @Override
    public void scheduleServerTask(Runnable run, long delay) {
        TaskManager.scheduleDelayedTaskInMillis(run, delay*50);
    }

    @Override
    public boolean sendWebChatEvent(String source, String name, String msg) {
        return DynmapCommonAPIListener.fireWebChatEvent(source, name, msg);
    }

    @Override
    public String stripChatColor(String s) {
        return TextFormat.removeFormatting(s);
    }
}
