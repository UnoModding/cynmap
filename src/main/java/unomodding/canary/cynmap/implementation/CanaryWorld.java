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

import java.util.List;

import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.World;
import org.dynmap.DynmapChunk;
import org.dynmap.DynmapLocation;
import org.dynmap.DynmapWorld;
import org.dynmap.utils.MapChunkCache;

public class CanaryWorld extends DynmapWorld
{
    private World world;
    private boolean skylight;
    private String env;
    private DynmapLocation spawnloc = new DynmapLocation();

    public CanaryWorld(World world) {
        super(world.getName(), world.getHeight(), 0);
        this.world = world;
        this.env = world.getType().getName();
        skylight = (env == DimensionType.NORMAL.getName());
    }

    @Override
    public boolean isNether()
    {
        return env == DimensionType.NETHER.getName();
    }

    @Override
    public DynmapLocation getSpawnLocation()
    {
        if (world != null) {
            spawnloc = new CanaryLocation(world.getSpawnLocation());
        }
        return spawnloc;
    }

    @Override
    public long getTime()
    {
        if (world != null) {
            return world.getTotalTime();
        } else {
            return -1;
        }
    }

    @Override
    public boolean hasStorm()
    {
        return false;
    }

    @Override
    public boolean isThundering()
    {
        return world.isThundering();
    }

    @Override
    public boolean isLoaded()
    {
        return (world != null);
    }

    @Override
    public void setWorldUnloaded()
    {
        getSpawnLocation(); /* Remember spawn location before unload */
        world = null;
    }

    @Override
    public int getLightLevel(int x, int y, int z)
    {
        if (world != null) {
            return world.getLightLevelAt(x, y, z);
        } else {
            return -1;
        }
    }

    @Override
    public int getHighestBlockYAt(int x, int z)
    {
        if (world != null) {
            return world.getHighestBlockAt(x, z);
        } else {
            return -1;
        }
    }

    @Override
    public boolean canGetSkyLightLevel()
    {
        return skylight && (world != null);
    }

    @Override
    public int getSkyLightLevel(int x, int y, int z)
    {
        return 0;
    }

    @Override
    public String getEnvironment()
    {
        return env;
    }

    @Override
    public MapChunkCache getChunkCache(List<DynmapChunk> chunks)
    {
        return null;
    }
}
