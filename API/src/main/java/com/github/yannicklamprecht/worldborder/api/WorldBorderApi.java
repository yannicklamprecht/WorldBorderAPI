package com.github.yannicklamprecht.worldborder.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public interface WorldBorderApi {

    IWorldBorder getWorldBorder(Player p);

    IWorldBorder getWorldBorder(World world);

    void resetWorldBorderToGlobal(Player player);

    void setBorder(Player player, double size);

    void setBorder(Player player, double size, Location location);

    void sendRedScreenForSeconds(Player player, long timeSeconds, JavaPlugin javaPlugin);

    void setBorder(Player player, double size, long milliSeconds);

    void setBorder(Player player, double size, long time, TimeUnit timeUnit);
}
