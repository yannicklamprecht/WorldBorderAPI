package com.github.yannicklamprecht.worldborder.testplugin;

import com.github.yannicklamprecht.worldborder.api.BorderAPI;
import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
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

        this.worldBorderApi = BorderAPI.getApi();

        getServer().getPluginManager().registerEvents(this, this);

    }


    @EventHandler
    public void chat(AsyncPlayerChatEvent ev) {

        if (ev.getMessage().startsWith("text")) {
            int radius = 30;
            int time = 60;
            if (ev.getMessage().split(" ").length > 1) {
                radius = Integer.parseInt(ev.getMessage().split(" ")[1]);

                if (ev.getMessage().split(" ").length > 2) {
                    time = Integer.parseInt(ev.getMessage().split(" ")[2]);
                }

            }
            this.worldBorderApi.setBorder(ev.getPlayer(), radius, time);
        } else if (ev.getMessage().startsWith("fuck")) {

            int time = 30;
            if (ev.getMessage().split(" ").length > 1) {
                time = Integer.parseInt(ev.getMessage().split(" ")[1]);
            }

            this.worldBorderApi.sendRedScreenForSeconds(ev.getPlayer(), time, this);
        } else if( ev.getMessage().startsWith("yeah")){

            int size = 30;

            if (ev.getMessage().split(" ").length > 1) {
                size = Integer.parseInt(ev.getMessage().split(" ")[1]);
            }
            this.worldBorderApi.setBorder(ev.getPlayer(),size,ev.getPlayer().getLocation());
        }

    }


}
