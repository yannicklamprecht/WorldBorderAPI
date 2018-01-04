package de.craftstuebchen.api.worldborder;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldBorderPlugin extends JavaPlugin implements Listener {

	private static WorldBorderPlugin	inst;

	public WorldBorderPlugin() {
		inst = this;
	}

	public static WorldBorderPlugin inst() {
		return inst;
	}

	@Override
	public void onEnable() {


		Bukkit.getPluginManager().registerEvents(this, this);
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
			WorldBorderAPI.inst().setBorder(ev.getPlayer(), radius, time);
		} else if (ev.getMessage().startsWith("fuck")) {

			int time = 30;
			if (ev.getMessage().split(" ").length > 1) {
				time = Integer.parseInt(ev.getMessage().split(" ")[1]);
			}

			WorldBorderAPI.inst().sendRedScreen(ev.getPlayer(), time);
		}

	}

}
