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
package unomodding.canary.cynmap;

import java.io.File;

import net.canarymod.Canary;
import net.canarymod.plugin.Plugin;

import org.dynmap.DynmapCommonAPI;
import org.dynmap.DynmapCommonAPIListener;
import org.dynmap.DynmapCore;
import org.dynmap.markers.MarkerAPI;
import org.dynmap.modsupport.ModSupportImpl;

import unomodding.canary.cynmap.data.Constants;
import unomodding.canary.cynmap.implementation.CanaryServer;

public class CynmapPlugin extends Plugin implements DynmapCommonAPI
{
    private DynmapCore core;
    private CanaryServer server = new CanaryServer(Canary.getServer());
    private CanaryEnableCoreCallback config = new CanaryEnableCoreCallback();
    
    public CynmapPlugin() {
        ModSupportImpl.init();
    }

    @Override
    public boolean enable()
    {
        // init data folder
        if (!Constants.dataFolder.exists()) {
            Constants.dataFolder.mkdir();
        }
        // init core
        if (core == null) {
            core = new DynmapCore();
        }
        core.setPluginJarFile(new File(getPath()));
        core.setPluginVersion(getDescriptor().getVersion(), "CanaryLib");
        core.setMinecraftVersion(Canary.getServer().getServerVersion());
        core.setDataFolder(Constants.dataFolder);
        core.setServer(server);
        core.setTriggerDefault(null);
        core.setBiomeNames(null);
        core.setBlockNames(null);
        core.setBlockMaterialMap(null);

        // Load configuration
        if (!core.initConfiguration(config)) {
            return false;
        }
        // Core is ready - notify API availability
        DynmapCommonAPIListener.apiInitialized(this);

        // Enable core
        if (!core.enableCore(config)) {
            return false;
        }
        core.serverStarted();
        
        // Register listener
        Canary.hooks().registerListener(new CynmapListener(this), this);
        
        return true;
    }

    @Override
    public void disable()
    {
        // Core is being disabled - notify API disable
        DynmapCommonAPIListener.apiTerminated();

        // Disable core
        core.disableCore();
    }
    
    public DynmapCore getCore() {
        return core;
    }

    public MarkerAPI getMarkerAPI()
    {
        return core.getMarkerAPI();
    }

    public boolean markerAPIInitialized()
    {
        return core.markerAPIInitialized();
    }

    public boolean sendBroadcastToWeb(String sender, String msg)
    {
        return core.sendBroadcastToWeb(sender, msg);
    }

    public int triggerRenderOfVolume(String wid, int minx, int miny, int minz, int maxx, int maxy, int maxz)
    {
        return core.triggerRenderOfVolume(wid, minx, miny, minz, maxx, maxy, maxz);
    }

    public int triggerRenderOfBlock(String wid, int x, int y, int z)
    {
        return core.triggerRenderOfBlock(wid, x, y, z);
    }

    public void setPauseFullRadiusRenders(boolean dopause)
    {
        core.setPauseFullRadiusRenders(dopause);
    }

    public boolean getPauseFullRadiusRenders()
    {
        return core.getPauseFullRadiusRenders();
    }

    public void setPauseUpdateRenders(boolean dopause)
    {
        core.setPauseFullRadiusRenders(dopause);
    }

    public boolean getPauseUpdateRenders()
    {
        return core.getPauseUpdateRenders();
    }

    public void setPlayerVisiblity(String player, boolean is_visible)
    {
        core.setPlayerVisiblity(player, is_visible);
    }

    public boolean getPlayerVisbility(String player)
    {
        return core.getPlayerVisbility(player);
    }

    public void assertPlayerInvisibility(String player, boolean is_invisible, String plugin_id)
    {
        core.assertPlayerInvisibility(player, is_invisible, plugin_id);
    }

    public void assertPlayerVisibility(String player, boolean is_visible, String plugin_id)
    {
        core.assertPlayerVisibility(player, is_visible, plugin_id);
    }

    public void postPlayerMessageToWeb(String playerid, String playerdisplay, String message)
    {
        core.postPlayerMessageToWeb(playerid, playerdisplay, message);
    }

    public void postPlayerJoinQuitToWeb(String playerid, String playerdisplay, boolean isjoin)
    {
        core.postPlayerJoinQuitToWeb(playerid, playerdisplay, isjoin);
    }

    public String getDynmapCoreVersion()
    {
        return core.getDynmapCoreVersion();
    }

    public boolean setDisableChatToWebProcessing(boolean disable)
    {
        return core.setDisableChatToWebProcessing(disable);
    }

    public boolean testIfPlayerVisibleToPlayer(String player, String player_to_see)
    {
        return core.testIfPlayerVisibleToPlayer(player, player_to_see);
    }

    public boolean testIfPlayerInfoProtected()
    {
        return core.testIfPlayerInfoProtected();
    }

    public void processSignChange(int blkid, String world, int x, int y, int z, String[] lines,
            String playerid)
    {
        core.processSignChange(blkid, world, x, y, z, lines, playerid);
    }

    private class CanaryEnableCoreCallback extends DynmapCore.EnableCoreCallbacks
    {
        @Override
        public void configurationLoaded()
        {
        }
    }
}
