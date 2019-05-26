package com.github.yannicklamprecht.worldborder.api;


import java.util.function.Function;
import java.util.function.Supplier;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldBorderApi {

    private Function<Player, IWorldBorder> getWorldBorderPlayer;

    private Supplier<IWorldBorder> getWorldBorder;

    public WorldBorderApi(Function<Player, IWorldBorder> getWorldBorderPlayer, Supplier<IWorldBorder> getWorldBorder) {
        this.getWorldBorderPlayer = getWorldBorderPlayer;
        this.getWorldBorder = getWorldBorder;
    }

    public IWorldBorder getWorldBorder(Player p) {
        return getWorldBorderPlayer.apply(p);
    }

    public IWorldBorder getWorldBorder() {
        return getWorldBorder.get();
    }

    public void resetWorldBorderToGlobal(Player player) {
        getWorldBorder().send(player, WorldBorderAction.INITIALIZE);
    }

    public void setBorder(Player player, double size) {
        setBorder(player, size, player.getWorld().getSpawnLocation());
    }

    public void setBorder(Player player, double size, Location location) {
        IWorldBorder border = getWorldBorder(player);
        border.setSize(size);
        border.setCenter(new Position(location));
        border.send(player, WorldBorderAction.SET_SIZE);
        border.send(player, WorldBorderAction.SET_CENTER);
    }

    public void sendRedScreenForSeconds(Player player, long timeSeconds, JavaPlugin javaPlugin) {
        IWorldBorder border = getWorldBorder(player);

        border.setWarningDistanceInBlocks((int) border.getSize() / 2);

        border.send(player, WorldBorderAction.SET_WARNING_BLOCKS);

        Bukkit.getScheduler().runTaskLater(javaPlugin,
        () -> {
            border.setWarningDistanceInBlocks(0);
            border.send(player, WorldBorderAction.SET_WARNING_BLOCKS);
        }, timeSeconds * 20L);
    }

    public void setBorder(Player player, IWorldBorder border, WorldBorderAction action) {

    }

    public void setBorder(Player player, double radius, long seconds) {

    }


}
