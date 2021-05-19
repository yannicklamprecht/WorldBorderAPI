package com.github.yannicklamprecht.worldborder.api;

import java.util.function.BiConsumer;

public class WorldBorderData {

    private double size;
    private double x;
    private double z;
    private double damageBufferInBlocks;
    private double damageAmount;
    private int warningTimeSeconds;
    private int warningDistance;


    public double getSize() {
        return size;
    }

    public void setSize(double newSize) {
        this.size = newSize;
    }

    public void setCenter(double x, double z) {
        this.x = x;
        this.z = z;
    }

    public void applyCenter(BiConsumer<Double, Double> doubleBiConsumer) {
        doubleBiConsumer.accept(x, z);
    }


    public double getDamageBuffer() {
        return damageBufferInBlocks;
    }


    public void setDamageBuffer(double blocks) {
        this.damageBufferInBlocks = blocks;
    }


    public double getDamageAmount() {
        return damageAmount;
    }


    public void setDamageAmount(double damage) {
        this.damageAmount = damage;
    }


    public int getWarningTimeSeconds() {
        return warningTimeSeconds;
    }


    public void setWarningTimeSeconds(int seconds) {
        this.warningTimeSeconds = seconds;
    }


    public int getWarningDistance() {
        return warningDistance;
    }

    public void setWarningDistance(int distance) {
        this.warningDistance = distance;
    }

    public void applyAll(IWorldBorder worldBorder){
        worldBorder.setSize(size);
        worldBorder.setCenter(new Position(x, z));
        worldBorder.setDamageBufferInBlocks(damageBufferInBlocks);
        worldBorder.setDamagePerSecondPerBlock(damageAmount);
        worldBorder.setWarningTimeInSeconds(warningTimeSeconds);
        worldBorder.setWarningDistanceInBlocks(warningDistance);
    }
}
