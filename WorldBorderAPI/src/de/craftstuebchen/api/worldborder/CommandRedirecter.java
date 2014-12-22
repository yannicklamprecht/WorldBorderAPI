package de.craftstuebchen.api.worldborder;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandRedirecter implements Listener {

	public CommandRedirecter(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void rediredct(PlayerCommandPreprocessEvent event) {

		if (event.getMessage().startsWith("/worldborder")) {
			event.setCancelled(true);

			String[] args = event.getMessage().split(" ");

			if (args.length > 1) {
				subcommand(event.getPlayer(),
						Arrays.copyOfRange(args, 1, args.length));
			} else {
				event.getPlayer()
						.sendMessage(
								ChatColor.RED
										+ "Usage: /worldborder <set|center|damager|warnings|get|add> ...");
			}

		}

	}

	private void subcommand(Player p, String[] args) {

		if (args.length == 1) {

			switch (args[0]) {
				case "set":
					p.sendMessage(ChatColor.RED
							+ "Usage: /worldborder set <sizeinBlocks> [timeInSeconds]");
					break;
				case "center":
					p.sendMessage(ChatColor.RED
							+ "Usage: /worldborder center <x> <y>");

					break;
				case "damage":
					p.sendMessage(ChatColor.RED
							+ "Usage: /worldborder damage <buffer|amount>");

					break;
				case "warning":
					p.sendMessage(ChatColor.RED
							+ "Usage: /worldborder warning <time|distance>");
					break;
				case "get":
					p.sendMessage("World border is currently "
							+ WorldBorderAPI.inst().getWorldBorder(p)
									.getLength() + " blocks wide");

					break;
				case "add":
					p.sendMessage(ChatColor.RED
							+ "Usage: /worldborder center <sizeInBlocks> [timeInSeconds]");

					break;

				default:
					p.sendMessage(ChatColor.RED
							+ "Usage: /worldborder <set|center|damager|warnings|get|add> ...");
					break;
			}

		} else {

			switch (args[0]) {
				case "set":

					break;

				default:
					break;
			}

		}

	}

}
