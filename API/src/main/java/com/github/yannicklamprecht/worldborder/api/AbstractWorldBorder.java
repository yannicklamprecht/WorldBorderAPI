package com.github.yannicklamprecht.worldborder.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.bukkit.Location;

/**
 * Created by ysl3000
 */
public abstract class AbstractWorldBorder implements IWorldBorder {

    private Supplier<Position> centerSupplier;
    private Consumer<Position> centerConsumer;

    private Supplier<Position> minSupplier;
    private Supplier<Position> maxSupplier;

    private Supplier<Double> sizeSupplier;
    private Consumer<Double> sizeConsumer;

    private Supplier<Integer> damageBufferInBlockSupplier;
    private Consumer<Integer> damageBufferInBlockConsumer;

    private Supplier<Double> damagePerSecondsPerBlockSupplier;
    private Consumer<Double> damagePerSecondsPerBlockConsumer;


    private Supplier<Integer> warningTimerInSecondsSupplier;
    private Consumer<Integer> warningTimerInSecondsConsumer;

    private Supplier<Integer> warningDistanceInBlocksSupplier;
    private Consumer<Integer> warningDistanceInBlocksConsumer;

    private Function<Location, Boolean> inBoundsSupplier;

    private FunctionDoubleDoubleLong lerpConsumer;

    public AbstractWorldBorder(Supplier<Position> centerSupplier, Consumer<Position> centerConsumer, Supplier<Position> minSupplier, Supplier<Position> maxSupplier, Supplier<Double> sizeSupplier, Consumer<Double> sizeConsumer,
    Supplier<Integer> damageBufferInBlockSupplier, Consumer<Integer> damageBufferInBlockConsumer, Supplier<Double> damagePerSecondsPerBlockSupplier, Consumer<Double> damagePerSecondsPerBlockConsumer, Supplier<Integer> warningTimerInSecondsSupplier,
    Consumer<Integer> warningTimerInSecondsConsumer, Supplier<Integer> warningDistanceInBlocksSupplier, Consumer<Integer> warningDistanceInBlocksConsumer, Function<Location, Boolean> inBoundsSupplier, FunctionDoubleDoubleLong lerpConsumer) {
        this.centerSupplier = centerSupplier;
        this.centerConsumer = centerConsumer;
        this.minSupplier = minSupplier;
        this.maxSupplier = maxSupplier;
        this.sizeSupplier = sizeSupplier;
        this.sizeConsumer = sizeConsumer;
        this.damageBufferInBlockSupplier = damageBufferInBlockSupplier;
        this.damageBufferInBlockConsumer = damageBufferInBlockConsumer;
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

    public double getDamagePerSecondPerBlock() {
        return damagePerSecondsPerBlockSupplier.get();
    }

    public void setDamagerPerSecondPerBlock(double damage) {
        damagePerSecondsPerBlockConsumer.accept(damage);
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
