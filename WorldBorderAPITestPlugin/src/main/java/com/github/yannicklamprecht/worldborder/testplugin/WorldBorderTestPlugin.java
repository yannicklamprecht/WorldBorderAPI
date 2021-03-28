package com.github.yannicklamprecht.worldborder.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.yannicklamprecht.worldborder.api.IWorldBorder;
import com.github.yannicklamprecht.worldborder.api.PersistentWorldBorderApi;
import com.github.yannicklamprecht.worldborder.api.WorldBorderAction;
import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import com.github.yannicklamprecht.worldborder.api.WorldBorderData;

import co.aikar.commands.BukkitCommandManager;

/**
 * Created by ysl3000
 */
public class WorldBorderTestPlugin extends JavaPlugin implements Listener {

    private WorldBorderApi worldBorderApi;

    @Override
    public void onEnable() {

        RegisteredServiceProvider<WorldBorderApi> worldBorderApiRegisteredServiceProvider = getServer().getServicesManager().getRegistration(WorldBorderApi.class);

        if (worldBorderApiRegisteredServiceProvider == null) {
            getLogger().info("API not found");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        worldBorderApi = worldBorderApiRegisteredServiceProvider.getProvider();

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
