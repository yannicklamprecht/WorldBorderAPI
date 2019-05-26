package com.github.yannicklamprecht.worldborder.api;


import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface WorldBorderApi {

    void setBorder(double radius, long seconds);

    void setBorder(Player player, double radius, long seconds);

    void setBorder(Player player, IWorldBorder border, WorldBorderAction action);

    IWorldBorder getWorldBorder(Player p);

    void setBorder(double radius);

    void sendRedScreen(Player player, long time);

    void setBorder(Player player, double radius);

    void setBorder(Player player, double radius, Location location);

}
