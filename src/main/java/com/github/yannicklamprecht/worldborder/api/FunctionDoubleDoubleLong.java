package com.github.yannicklamprecht.worldborder.api;

/**
 * This functional interface represents a function that accepts two doubles and a long value
 * and performs an operation with them.
 */
@FunctionalInterface
public interface FunctionDoubleDoubleLong {
    /**
     * Linearly interpolates the old size of the world border to the new size over a specified time.
     *
     * @param oldSize the old size of the world border
     * @param newSize the new size of the world border
     * @param time the time (in milliseconds) over which to interpolate the size
     */
    void lerp(double oldSize, double newSize, long time);
}
