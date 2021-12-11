package com.github.yannicklamprecht.worldborder.plugin;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import com.github.yannicklamprecht.worldborder.impl.Border;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ysl3000
 */
public class WorldBorderPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        WorldBorderApi worldBorderApi = new PersistenceWrapper(this, new Border());
        getServer().getServicesManager().register(WorldBorderApi.class, worldBorderApi, this, ServicePriority.High);
    }
}
