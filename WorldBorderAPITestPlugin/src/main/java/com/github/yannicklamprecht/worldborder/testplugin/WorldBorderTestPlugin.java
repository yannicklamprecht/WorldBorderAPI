package com.github.yannicklamprecht.worldborder.testplugin;

import com.github.yannicklamprecht.worldborder.api.*;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

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

        this.worldBorderApi = BorderAPI.getApi();

        getServer().getPluginManager().registerEvents(this, this);

    }


    @EventHandler
    public void chat(AsyncPlayerChatEvent ev) {
        String[] args = ev.getMessage().split(" ");
        if (ev.getMessage().startsWith("set")) {
            int radius = 30;
            int time = 60;
            if (args.length > 1) {
                radius = Integer.parseInt(args[1]);

                if (args.length > 2) {
                    time = Integer.parseInt(args[2]);
                }

            }
            this.worldBorderApi.setBorder(ev.getPlayer(), radius, time);
        } else if (ev.getMessage().startsWith("redscreen")) {

            int time = 30;
            if (args.length > 1) {
                time = Integer.parseInt(args[1]);
            }

            this.worldBorderApi.sendRedScreenForSeconds(ev.getPlayer(), time, this);
        } else if (ev.getMessage().startsWith("reset")) {

            int size = 30;

            if (args.length > 1) {
                size = Integer.parseInt(args[1]);
            }
            this.worldBorderApi.setBorder(ev.getPlayer(), size, ev.getPlayer().getLocation());
        } else if (ev.getMessage().startsWith("green")) {
            IWorldBorder iWorldBorder = this.worldBorderApi.getWorldBorder(ev.getPlayer());
            iWorldBorder.lerp(20, 22, TimeUnit.SECONDS.toMillis(10));
            iWorldBorder.setCenter(new Position(ev.getPlayer().getLocation()));
            iWorldBorder.send(ev.getPlayer(), WorldBorderAction.LERP_SIZE);
        } else if (ev.getMessage().startsWith("red")) {
            IWorldBorder iWorldBorder = this.worldBorderApi.getWorldBorder(ev.getPlayer());
            iWorldBorder.lerp(22, 20, TimeUnit.SECONDS.toMillis(10));
            iWorldBorder.setCenter(new Position(ev.getPlayer().getLocation()));
            iWorldBorder.send(ev.getPlayer(), WorldBorderAction.LERP_SIZE);
        }


    }


}
