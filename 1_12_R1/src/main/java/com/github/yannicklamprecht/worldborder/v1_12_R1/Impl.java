package com.github.yannicklamprecht.worldborder.v1_12_R1;


import com.github.yannicklamprecht.worldborder.api.IWorldBorder;
import com.github.yannicklamprecht.worldborder.api.WorldBorderAction;
import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Impl implements WorldBorderApi {

    @Override
    public void setBorder(double radius, long seconds) {

    }

    @Override
    public void setBorder(Player player, double radius, long seconds) {

    }

    @Override
    public void setBorder(Player player, IWorldBorder border, WorldBorderAction action) {

    }

    @Override
    public IWorldBorder getWorldBorder(Player p) {
        return null;
    }

    @Override
    public void setBorder(double radius) {

    }

    @Override
    public void sendRedScreen(Player player, long time) {

    }

    @Override
    public void setBorder(Player player, double radius) {

    }

    @Override
    public void setBorder(Player player, double radius, Location location) {

    }
}
