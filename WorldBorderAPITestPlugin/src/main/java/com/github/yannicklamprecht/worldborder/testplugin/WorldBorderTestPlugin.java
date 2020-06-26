package com.github.yannicklamprecht.worldborder.testplugin;

import co.aikar.commands.BukkitCommandManager;
import com.github.yannicklamprecht.worldborder.api.BorderAPI;
import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ysl3000
 */
public class WorldBorderTestPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldBorderAPI");

        if (plugin == null || !plugin.isEnabled()) {
            getLogger().info("API not found");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        WorldBorderApi worldBorderApi = BorderAPI.getApi();

        BukkitCommandManager commandManager = new BukkitCommandManager(this);
        // optional: enable unstable api to use help
        commandManager.enableUnstableAPI("help");

        commandManager.registerCommand(new BorderTestCommands(worldBorderApi, this));


    }
}
