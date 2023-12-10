package com.github.yannicklamprecht.worldborder.api;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * Represents a position in a 2-dimensional space, defined by its x and z coordinates.
 */
public record Position(double x, double z) {

    /**
     * Returns a new position object based on the given vector.
     *
     * @param vector the vector containing x and z coordinates
     * @return a new Position object
     */
    public static Position of(Vector vector) {
        return new Position(vector.getX(), vector.getZ());
    }

    /**
     * Returns a new {@link Position} object based on the given {@link Location}.
     *
     * @param location the location to create a position from
     * @return a new Position object
     */
    public static Position of(Location location) {
        return new Position(location.getX(), location.getZ());
    }

}
