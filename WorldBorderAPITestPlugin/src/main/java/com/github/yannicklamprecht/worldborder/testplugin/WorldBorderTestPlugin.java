package com.github.yannicklamprecht.worldborder.testplugin;

import co.aikar.commands.BukkitCommandManager;
import com.github.yannicklamprecht.worldborder.api.*;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ysl3000
 */
public class WorldBorderTestPlugin extends JavaPlugin implements Listener {

    private WorldBorderApi worldBorderApi;

    @Override
    public void onEnable() {

        Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldBorderAPI");

        if (plugin == null || !plugin.isEnabled()) {
            getLogger().info("API not found");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        worldBorderApi = BorderAPI.getApi();

        BukkitCommandManager commandManager = new BukkitCommandManager(this);
        // optional: enable unstable api to use help
        commandManager.enableUnstableAPI("help");

        commandManager.registerCommand(new BorderTestCommands(worldBorderApi, this));
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        if (worldBorderApi instanceof PersistentWorldBorderApi) {
            Bukkit.getScheduler().runTaskLater(this, () -> {
                PersistentWorldBorderApi persistentWorldBorderApi = ((PersistentWorldBorderApi) worldBorderApi);
                WorldBorderData worldBorderData = persistentWorldBorderApi.getWorldBorderData(playerJoinEvent.getPlayer());
                if (worldBorderData != null) {
                    IWorldBorder worldBorder = worldBorderApi.getWorldBorder(playerJoinEvent.getPlayer());
                    worldBorderData.applyAll(worldBorder);
                    worldBorder.send(playerJoinEvent.getPlayer(), WorldBorderAction.INITIALIZE);
                }
            }, 20 * 3);
        }
    }
}
