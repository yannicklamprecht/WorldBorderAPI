package com.github.yannicklamprecht.worldborder.plugin;

import com.github.yannicklamprecht.worldborder.api.BorderAPI;
import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import com.github.yannicklamprecht.worldborder.api.WorldBorderApiImpl;

import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ysl3000
 */
public class WorldBorderPlugin extends JavaPlugin {

    @SuppressWarnings("deprecation")
    @Override
    public void onEnable() {

        String version = Bukkit.getServer().getClass().getPackage().getName().replace('.', ',').split(",")[3];
        System.out.println("Version: " + version);
        WorldBorderApi worldBorderApi;
        switch (version) {
            case "v1_16_R3" -> worldBorderApi = new com.github.yannicklamprecht.worldborder.v1_16.Border();
            case "v1_17_R1" -> worldBorderApi = new com.github.yannicklamprecht.worldborder.v1_17.Border();
            default -> {
                getLogger().info("Unsupported version of Minecraft");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }
        }

        worldBorderApi = new PersistenceWrapper(this, worldBorderApi);

        getServer().getServicesManager().register(WorldBorderApi.class, worldBorderApi, this, ServicePriority.High);

        BorderAPI.setWorldBorderApi(worldBorderApi);
    }

}
