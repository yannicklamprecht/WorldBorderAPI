package com.github.yannicklamprecht.worldborder.api;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * This interface represents a world border in a 2-dimensional space.
 * A world border is defined by its center position, size, damage buffer, warning timer, and warning distance.
 */
public interface IWorldBorder {


    /**
     * Returns the center position of the world border.
     *
     * @return the center position
     */
    default Position getCenter() {
        return center();
    }

    /**
     * Sets the center position of the world border.
     *
     * @param center the center position of the world border
     */
    default void setCenter(Position center) {
        center(center);
    }

    /**
     * Returns the minimum position of the world border.
     *
     * @return the minimum position
     */
    default Position getMin() {
        return min();
    }

    /**
     * Returns the maximum position of the world border.
     *
     * @return the maximum position
     */
    default Position getMax() {
        return max();
    }

    /**
     * Returns the size of the world border.
     *
     * @return the size of the world border
     */
    default double getSize() {
        return size();
    }

    /**
     * Sets the size of the world border.
     *
     * @param radius the radius of the world border
     */
    default void setSize(double radius) {
        size(radius);
    }

    /**
     * Returns the damage buffer in blocks.
     *
     * @return the damage buffer in blocks
     */
    default double getDamageBufferInBlocks() {
        return damageBufferInBlocks();
    }

    /**
     * Sets the damage buffer in blocks.
     *
     * @param blocks the damage buffer value in blocks
     */
    default void setDamageBufferInBlocks(double blocks) {
        damageBufferInBlocks(blocks);
    }

    /**
     * Returns the warning timer in seconds.
     *
     * @return the warning timer in seconds
     */
    default int getWarningTimerInSeconds() {
        return warningTimerInSeconds();
    }

    /**
     * Sets the warning time in seconds for the world border.
     *
     * @param seconds the warning time in seconds
     */
    default void setWarningTimeInSeconds(int seconds) {
        warningTimeInSeconds(seconds);
    }

    /**
     * Retrieves the warning distance in blocks for the world border.
     *
     * @return the warning distance in blocks
     */
    default int getWarningDistanceInBlocks() {
        return warningDistanceInBlocks();
    }

    /**
     * Set the warning distance in blocks for the world border.
     *
     * @param blocks the warning distance in blocks
     */
    default void setWarningDistanceInBlocks(int blocks) {
        warningDistanceInBlocks(blocks);
    }

    /**
     * Sets the center position of the world border.
     *
     * @param center the center position of the world border
     */
    void center(Position center);

    /**
     * Returns the center position of the world border.
     *
     * @return the center position
     */
    Position center();

    /**
     * Returns the minimum position of the world border.
     *
     * @return the minimum position
     */
    Position min();

    /**
     * Returns the maximum position of the world border.
     *
     * @return the maximum position
     */
    Position max();

    /**
     * Returns the size of the world border.
     *
     * @return the size of the world border as a double value
     */
    double size();

    /**
     * Sets the size of the world border.
     *
     * @param radius the radius of the world border
     */
    void size(double radius);

    /**
     * Returns the damage buffer in blocks.
     *
     * @return the damage buffer in blocks
     */
    double damageBufferInBlocks();

    /**
     * Sets the damage buffer in blocks.
     *
     * @param blocks the damage buffer value in blocks
     */
    void damageBufferInBlocks(double blocks);

    /**
     * Returns the warning timer in seconds.
     *
     * @return the warning timer in seconds
     */
    int warningTimerInSeconds();

    /**
     * Sets the warning time in seconds for the world border.
     *
     * @param seconds the warning time in seconds
     */
    void warningTimeInSeconds(int seconds);

    /**
     * Retrieves the warning distance in blocks for the world border.
     *
     * @return the warning distance in blocks
     */
    int warningDistanceInBlocks();

    /**
     * Sets the warning distance in blocks for the world border.
     *
     * @param blocks the warning distance in blocks
     * @see IWorldBorder#setWarningDistanceInBlocks(int)
     * @see IWorldBorder#getWarningDistanceInBlocks()
     */
    void warningDistanceInBlocks(int blocks);

    /**
     * Linearly interpolates the old size of the world border to the new size over a specified time.
     *
     * @param oldSize  the old size of the world border
     * @param newSize  the new size of the world border
     * @param time     the time (in seconds) over which to interpolate the size
     */
    void lerp(double oldSize, double newSize, long time);


    /**
     * Linearly interpolates the old size of the world border to the new size over a specified time.
     *
     * @param oldSize  the old size of the world border
     * @param newSize  the new size of the world border
     * @param time     the duration of the interpolation
     * @param startTime the start time of the interpolation
     */
    void lerp(double oldSize, double newSize, Duration time, LocalDateTime startTime);

    /**
     * Sends a world border action to a player.
     *
     * @param player            the player to send the action to
     * @param worldBorderAction the world border action to send
     */
    void send(Player player, WorldBorderAction worldBorderAction);
}
