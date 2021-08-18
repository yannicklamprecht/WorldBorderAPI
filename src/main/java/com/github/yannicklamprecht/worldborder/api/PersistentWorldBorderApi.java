package com.github.yannicklamprecht.worldborder.api;

import org.bukkit.entity.Player;

public interface PersistentWorldBorderApi extends WorldBorderApi{
    WorldBorderData getWorldBorderData(Player p);
}
