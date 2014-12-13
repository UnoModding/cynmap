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

import java.net.InetSocketAddress;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;

import org.dynmap.DynmapLocation;
import org.dynmap.common.DynmapPlayer;

public class CanaryPlayer implements DynmapPlayer {
    private Player player;

    public CanaryPlayer(Player player) {
        this.player = player;
    }

    public boolean hasPermissionNode(String perm) {
        return player.hasPermission(perm);
    }

    public boolean hasPrivilege(String priv) {
        return player.hasPermission(priv);
    }

    public boolean isConnected() {
        return player.isOnline();
    }

    public boolean isOp() {
        return Canary.ops().isOpped(player);
    }

    public void sendMessage(String msg) {
        player.message(msg);
    }

    public InetSocketAddress getAddress() {
        return new InetSocketAddress(player.getIP(), 0);
    }

    public int getArmorPoints() {
        return 0;
    }

    public DynmapLocation getBedSpawnLocation() {
        return new CanaryLocation(player.getSpawnPosition());
    }

    public String getDisplayName() {
        return player.getDisplayName();
    }

    public long getFirstLoginTime() {
        return 0;
    }

    public int getHealth() {
        return Math.round(player.getHealth());
    }

    public long getLastLoginTime() {
        return 0;
    }

    public DynmapLocation getLocation() {
        return new CanaryLocation(player.getLocation());
    }

    public String getName() {
        return player.getName();
    }

    public int getSortWeight() {
        return 0;
    }

    public String getWorld() {
        return player.getWorld().getName();
    }

    public boolean isInvisible() {
        return player.isInvisible();
    }

    public boolean isOnline() {
        return player.isOnline();
    }

    public boolean isSneaking() {
        return player.isSneaking();
    }

    public void setSortWeight(int arg0) {

    }
}
