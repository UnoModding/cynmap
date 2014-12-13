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
package unomodding.canary.cynmap.data;

import java.io.File;

import net.canarymod.Canary;

public class Constants {
    // Canary
    public static final File canary = Canary.getWorkingDirectory();
    // cynmap
    public static final File dataFolder = new File(canary, "cynmap");

    public static void checkDirs() {
        File[] folders = new File[] { dataFolder };
        for (File f : folders) {
            f.mkdirs();
        }
    }
}
