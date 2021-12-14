package com.github.yannicklamprecht.worldborder.api;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * Created by ysl3000
 */
public record Position(double x, double z) {

    public static Position of(Vector vector) {
        return new Position(vector.getX(), vector.getZ());
    }

    public static Position of(Location location) {
        return new Position(location.getX(), location.getZ());
    }

}
