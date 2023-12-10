package com.github.yannicklamprecht.worldborder.api;

import org.bukkit.entity.Player;

/**
 * This interface extends WorldBorderApi and provides an additional method to retrieve WorldBorderData for a player.
 */
public interface PersistentWorldBorderApi extends WorldBorderApi {
    /**
     * Retrieves the WorldBorderData for a player.
     *
     * @param p the player
     * @return the WorldBorderData object containing the size, center coordinates, damage buffer, warning time, and warning distance of the world border
     */
    WorldBorderData getWorldBorderData(Player p);
}
