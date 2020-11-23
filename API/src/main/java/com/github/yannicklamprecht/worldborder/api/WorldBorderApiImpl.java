package com.github.yannicklamprecht.worldborder.api;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WorldBorderApiImpl implements WorldBorderApi {

    private final Function<Player, IWorldBorder> getWorldBorderPlayer;

    private final Function<World, IWorldBorder> getWorldBorder;

    public WorldBorderApiImpl(Function<Player, IWorldBorder> getWorldBorderPlayer, Function<World, IWorldBorder> getWorldBorder) {
        this.getWorldBorderPlayer = getWorldBorderPlayer;
        this.getWorldBorder = getWorldBorder;
    }

    @Override
    public IWorldBorder getWorldBorder(Player p) {
        return getWorldBorderPlayer.apply(p);
    }

    @Override
    public IWorldBorder getWorldBorder(World world) {
        return getWorldBorder.apply(world);
    }

    @Override
    public void resetWorldBorderToGlobal(Player player) {
        getWorldBorder(player.getWorld()).send(player, WorldBorderAction.INITIALIZE);
    }

    @Override
    public void setBorder(Player player, double size) {
        setBorder(player, size, player.getWorld().getSpawnLocation());
    }

    @Override
    public void setBorder(Player player, double size, Location location) {
        setBorder(player, size, new Position(location));
    }

    @Override
    public void setBorder(Player player, double size, Vector vector) {
        setBorder(player, size, new Position(vector));
    }

    @Override
    public void setBorder(Player player, double size, Position position) {
        IWorldBorder border = getWorldBorder(player);
        border.setSize(size);
        border.setCenter(position);
        border.send(player, WorldBorderAction.SET_SIZE);
        border.send(player, WorldBorderAction.SET_CENTER);
    }

    @Override
    public void sendRedScreenForSeconds(Player player, long timeSeconds, JavaPlugin javaPlugin) {
        IWorldBorder border = getWorldBorder(player);
        border.setWarningDistanceInBlocks((int) border.getSize());

        border.send(player, WorldBorderAction.SET_WARNING_BLOCKS);

        Bukkit.getScheduler().runTaskLater(javaPlugin,
                () -> {
                    border.setWarningDistanceInBlocks(0);
                    border.send(player, WorldBorderAction.SET_WARNING_BLOCKS);
                }, timeSeconds * 20L);
    }

    @Override
    public void setBorder(Player player, double size, long milliSeconds) {
        IWorldBorder worldBorder = getWorldBorder(player);
        worldBorder.lerp(worldBorder.getSize(), size, milliSeconds);
        worldBorder.send(player, WorldBorderAction.LERP_SIZE);
    }

    @Override
    public void setBorder(Player player, double size, long time, TimeUnit timeUnit) {
        setBorder(player, size, timeUnit.toMillis(time));
    }
}
