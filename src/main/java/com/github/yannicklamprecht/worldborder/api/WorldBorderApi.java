package com.github.yannicklamprecht.worldborder.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.concurrent.TimeUnit;

/**
 * This interface defines a set of methods for interacting with world borders.
 */
public interface WorldBorderApi {

    /**
     * Retrieves the world border for a specific player.
     *
     * @param p the player for which to retrieve the world border
     * @return the world border for the specified player
     */
    IWorldBorder getWorldBorder(Player p);

    /**
     * Retrieves the world border for a specific world.
     *
     * @param world the world for which to retrieve the world border
     * @return the world border for the specified world
     */
    IWorldBorder getWorldBorder(World world);

    /**
     * Resets the world border for a specific player to the global world border.
     *
     * @param player the player for which to reset the world border to global
     */
    void resetWorldBorderToGlobal(Player player);

    /**
     * Sets the border size for a specific player.
     *
     * @param player the player for which to set the border size
     * @param size   the size of the border to set
     */
    void setBorder(Player player, double size);

    /**
     * Sets the border size and center position for a specific player.
     *
     * @param player the player for which to set the border size and center position
     * @param size   the size of the border to set
     * @param vector the vector representing the center position of the border
     */
    void setBorder(Player player, double size, Vector vector);

    /**
     * Sets the border size and center position for a specific player.
     *
     * @param player    the player for which to set the border size and center position
     * @param size      the size of the border to set
     * @param location  the location representing the center position of the border
     */
    void setBorder(Player player, double size, Location location);

    /**
     * Sets the border size and center position for a specific player.
     *
     * @param player    the player for which to set the border size and center position
     * @param size      the size of the border to set
     * @param position  the position representing the center position of the border
     */
    void setBorder(Player player, double size, Position position);

    /**
     * Sends a red screen to the specified player for a given number of seconds.
     *
     * @param player      the player to send the red screen to
     * @param timeSeconds the number of seconds to display the red screen
     * @param javaPlugin  the JavaPlugin instance
     */
    void sendRedScreenForSeconds(Player player, long timeSeconds, JavaPlugin javaPlugin);

    /**
     * Sets the border size and duration for a specific player.
     *
     * @param player  the player for which to set the border size and duration
     * @param size    the size of the border to set
     * @param milliSeconds the duration of the border animation in milliseconds
     */
    void setBorder(Player player, double size, long milliSeconds);

    /**
     * Sets the border size and duration for a specific player.
     *
     * @param player    the player for which to set the border size and duration
     * @param size      the size of the border to set
     * @param time      the duration of the border animation
     * @param timeUnit  the time unit of the duration parameter
     */
    void setBorder(Player player, double size, long time, TimeUnit timeUnit);
}
