package com.github.yannicklamprecht.worldborder.api;

import java.util.function.BiConsumer;

/**
 * This class represents the data of a world border, including its size, center position, damage buffer, warning time, and warning distance.
 */
public class WorldBorderData {

    private double size;
    private double x;
    private double z;
    private double damageBufferInBlocks;
    private int warningTimeSeconds;
    private int warningDistance;


    /**
     * Retrieves the size of the world border.
     *
     * @return The size of the world border as a double value.
     */
    public double getSize() {
        return size;
    }

    /**
     * Sets the size of the world border.
     *
     * @param newSize The new size of the world border as a double value.
     */
    public void setSize(double newSize) {
        this.size = newSize;
    }

    /**
     * Sets the center position of the world border.
     *
     * @param x The x coordinate of the center position as a double value.
     * @param z The z coordinate of the center position as a double value.
     */
    public void setCenter(double x, double z) {
        this.x = x;
        this.z = z;
    }

    /**
     * Applies the center position of the world border to the given BiConsumer.
     *
     * @param doubleBiConsumer The BiConsumer to accept the center position coordinates.
     */
    public void applyCenter(BiConsumer<Double, Double> doubleBiConsumer) {
        doubleBiConsumer.accept(x, z);
    }


    /**
     * Retrieves the damage buffer of the world border.
     *
     * @return The damage buffer of the world border as a double value.
     */
    public double getDamageBuffer() {
        return damageBufferInBlocks;
    }


    /**
     * Sets the damage buffer of the world border.
     *
     * @param blocks The damage buffer to set, in blocks.
     */
    public void setDamageBuffer(double blocks) {
        this.damageBufferInBlocks = blocks;
    }


    /**
     * Retrieves the warning time in seconds for the world border.
     *
     * @return The warning time in seconds as an integer value.
     */
    public int getWarningTimeSeconds() {
        return warningTimeSeconds;
    }


    /**
     * Sets the warning time in seconds for the world border.
     *
     * @param seconds The warning time in seconds to set.
     */
    public void setWarningTimeSeconds(int seconds) {
        this.warningTimeSeconds = seconds;
    }


    /**
     * Retrieves the warning distance for the world border.
     *
     * @return The warning distance as an integer value.
     */
    public int getWarningDistance() {
        return warningDistance;
    }

    /**
     * Sets the warning distance for the world border.
     *
     * @param distance The warning distance to set in blocks.
     */
    public void setWarningDistance(int distance) {
        this.warningDistance = distance;
    }

    /**
     * Applies the attributes of a world border to the given {@link IWorldBorder}.
     *
     * @param worldBorder The {@link IWorldBorder} object to apply the attributes to.
     */
    public void applyAll(IWorldBorder worldBorder) {
        worldBorder.setSize(size);
        worldBorder.setCenter(new Position(x, z));
        worldBorder.setDamageBufferInBlocks(damageBufferInBlocks);
        worldBorder.setWarningTimeInSeconds(warningTimeSeconds);
        worldBorder.setWarningDistanceInBlocks(warningDistance);
    }
}
