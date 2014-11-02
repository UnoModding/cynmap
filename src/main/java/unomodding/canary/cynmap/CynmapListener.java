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

import org.dynmap.common.DynmapListenerManager.EventType;

import unomodding.canary.cynmap.implementation.CanaryPlayer;
import unomodding.canary.cynmap.implementation.CanaryWorld;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.player.ConnectionHook;
import net.canarymod.hook.player.DisconnectionHook;
import net.canarymod.hook.system.LoadWorldHook;
import net.canarymod.hook.system.UnloadWorldHook;
import net.canarymod.plugin.PluginListener;

public class CynmapListener implements PluginListener
{
    private CynmapPlugin plugin;

    public CynmapListener(CynmapPlugin plugin) {
        this.plugin = plugin;
    }
    
    @HookHandler
    public void onWorldLoad(LoadWorldHook hook)
    {
        plugin.getCore().listenerManager.processWorldEvent(EventType.WORLD_LOAD, new CanaryWorld(hook.getWorld()));
    }
    
    @HookHandler
    public void onWorldUnload(UnloadWorldHook hook)
    {
        plugin.getCore().listenerManager.processWorldEvent(EventType.WORLD_UNLOAD, new CanaryWorld(hook.getWorld()));
    }
    
    @HookHandler
    public void onPlayerJoin(ConnectionHook hook)
    {
        plugin.getCore().listenerManager.processPlayerEvent(EventType.PLAYER_JOIN, new CanaryPlayer(hook.getPlayer()));
    }
    
    @HookHandler
    public void onPlayerQuit(DisconnectionHook hook)
    {
        plugin.getCore().listenerManager.processPlayerEvent(EventType.PLAYER_QUIT, new CanaryPlayer(hook.getPlayer()));
    }
}
