package com.github.yannicklamprecht.worldborder.api;


import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldBorderApi {

    private Function<Player, IWorldBorder> getWorldBorderPlayer;

    private Function<World, IWorldBorder> getWorldBorder;

    public WorldBorderApi(Function<Player, IWorldBorder> getWorldBorderPlayer, Function<World, IWorldBorder> getWorldBorder) {
        this.getWorldBorderPlayer = getWorldBorderPlayer;
        this.getWorldBorder = getWorldBorder;
    }

    public IWorldBorder getWorldBorder(Player p) {
        return getWorldBorderPlayer.apply(p);
    }

    public IWorldBorder getWorldBorder(World world) {
        return getWorldBorder.apply(world);
    }

    public void resetWorldBorderToGlobal(Player player) {
        getWorldBorder(player.getWorld()).send(player, WorldBorderAction.INITIALIZE);
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

    public void setBorder(Player player, double size, long milliSeconds) {
        IWorldBorder worldBorder = getWorldBorder(player);
        worldBorder.lerp(worldBorder.getSize(), size, milliSeconds);
        worldBorder.send(player, WorldBorderAction.LERP_SIZE);
    }

    public void setBorder(Player player, double size, long time, TimeUnit timeUnit){
        setBorder(player, size, timeUnit.toMillis(time));
    }



}
