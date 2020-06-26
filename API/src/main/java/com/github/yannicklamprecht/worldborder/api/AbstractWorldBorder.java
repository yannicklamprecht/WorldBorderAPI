package com.github.yannicklamprecht.worldborder.api;

import org.bukkit.Location;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by ysl3000
 */
public abstract class AbstractWorldBorder implements IWorldBorder {

    private final Supplier<Position> centerSupplier;
    private final Consumer<Position> centerConsumer;

    private final Supplier<Position> minSupplier;
    private final Supplier<Position> maxSupplier;

    private final Supplier<Double> sizeSupplier;
    private final Consumer<Double> sizeConsumer;

    private final Supplier<Integer> damageBufferInBlockSupplier;
    private final Consumer<Integer> damageBufferInBlockConsumer;
    private final Consumer<Double> damageBufferDoubleBlockConsumer;

    private final Supplier<Double> damagePerSecondsPerBlockSupplier;
    private final Consumer<Double> damagePerSecondsPerBlockConsumer;


    private final Supplier<Integer> warningTimerInSecondsSupplier;
    private final Consumer<Integer> warningTimerInSecondsConsumer;

    private final Supplier<Integer> warningDistanceInBlocksSupplier;
    private final Consumer<Integer> warningDistanceInBlocksConsumer;

    private final Function<Location, Boolean> inBoundsSupplier;

    private final FunctionDoubleDoubleLong lerpConsumer;

    public AbstractWorldBorder(Supplier<Position> centerSupplier, Consumer<Position> centerConsumer, Supplier<Position> minSupplier, Supplier<Position> maxSupplier, Supplier<Double> sizeSupplier, Consumer<Double> sizeConsumer,
                               Supplier<Integer> damageBufferInBlockSupplier, Consumer<Integer> damageBufferInBlockConsumer, Consumer<Double> damageBufferDoubleBlockConsumer, Supplier<Double> damagePerSecondsPerBlockSupplier, Consumer<Double> damagePerSecondsPerBlockConsumer, Supplier<Integer> warningTimerInSecondsSupplier,
                               Consumer<Integer> warningTimerInSecondsConsumer, Supplier<Integer> warningDistanceInBlocksSupplier, Consumer<Integer> warningDistanceInBlocksConsumer, Function<Location, Boolean> inBoundsSupplier, FunctionDoubleDoubleLong lerpConsumer) {
        this.centerSupplier = centerSupplier;
        this.centerConsumer = centerConsumer;
        this.minSupplier = minSupplier;
        this.maxSupplier = maxSupplier;
        this.sizeSupplier = sizeSupplier;
        this.sizeConsumer = sizeConsumer;
        this.damageBufferInBlockSupplier = damageBufferInBlockSupplier;
        this.damageBufferInBlockConsumer = damageBufferInBlockConsumer;
        this.damageBufferDoubleBlockConsumer = damageBufferDoubleBlockConsumer;
        this.damagePerSecondsPerBlockSupplier = damagePerSecondsPerBlockSupplier;
        this.damagePerSecondsPerBlockConsumer = damagePerSecondsPerBlockConsumer;
        this.warningTimerInSecondsSupplier = warningTimerInSecondsSupplier;
        this.warningTimerInSecondsConsumer = warningTimerInSecondsConsumer;
        this.warningDistanceInBlocksSupplier = warningDistanceInBlocksSupplier;
        this.warningDistanceInBlocksConsumer = warningDistanceInBlocksConsumer;
        this.inBoundsSupplier = inBoundsSupplier;
        this.lerpConsumer = lerpConsumer;
    }


    public Position getCenter() {
        return centerSupplier.get();
    }

    public void setCenter(Position center) {
        centerConsumer.accept(center);
    }

    public Position getMin() {
        return minSupplier.get();
    }

    public Position getMax() {
        return maxSupplier.get();
    }

    public double getSize() {
        return sizeSupplier.get();
    }

    public void setSize(double radius) {
        sizeConsumer.accept(radius);
    }

    public int getDamageBufferInBlocks() {
        return damageBufferInBlockSupplier.get();
    }

    public void setDamageBufferInBlocks(int blocks) {
        damageBufferInBlockConsumer.accept(blocks);
    }

    public void setDamageBufferInBlocks(double blocks) {
        damageBufferDoubleBlockConsumer.accept(blocks);
    }

    public double getDamagePerSecondPerBlock() {
        return damagePerSecondsPerBlockSupplier.get();
    }

    @Override
    public void setDamagePerSecondPerBlock(double damage) {
        damagePerSecondsPerBlockConsumer.accept(damage);
    }

    public void setDamagerPerSecondPerBlock(double damage) {
        setDamagePerSecondPerBlock(damage);
    }

    public int getWarningTimerInSeconds() {
        return warningTimerInSecondsSupplier.get();
    }

    public void setWarningTimeInSeconds(int seconds) {
        warningTimerInSecondsConsumer.accept(seconds);
    }

    public int getWarningDistanceInBlocks() {
        return warningDistanceInBlocksSupplier.get();
    }

    public void setWarningDistanceInBlocks(int blocks) {
        warningDistanceInBlocksConsumer.accept(blocks);
    }

    public boolean isInBounds(Location location) {
        return inBoundsSupplier.apply(location);
    }

    public void lerp(double oldSize, double newSize, long time) {
        lerpConsumer.lerp(oldSize, newSize, time);
    }
}
