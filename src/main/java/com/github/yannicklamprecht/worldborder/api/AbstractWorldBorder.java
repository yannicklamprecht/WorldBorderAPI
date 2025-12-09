package com.github.yannicklamprecht.worldborder.api;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.function.Supplier;

/**
 * Created by ysl3000
 */
public abstract class AbstractWorldBorder implements IWorldBorder {

    private final ConsumerSupplierTupel<Position> center;

    private final Supplier<Position> minSupplier;
    private final Supplier<Position> maxSupplier;

    private final ConsumerSupplierTupel<Double> size;

    private final ConsumerSupplierTupel<Double> damaheBufferInBlocks;
    private final ConsumerSupplierTupel<Integer> warningTimerInSeconds;
    private final ConsumerSupplierTupel<Integer> warningDistanceInBlocks;

    private final FunctionDoubleDoubleLong lerpConsumer;

    /**
     * Ctor
     *
     * @param center                  the center
     * @param minSupplier             the minSupplier
     * @param maxSupplier             the maxSupplier
     * @param size                    the size
     * @param damaheBufferInBlocks    the damaheBufferInBlocks
     * @param warningTimerInSeconds   the warningTimerInSeconds
     * @param warningDistanceInBlocks the warningDistanceInBlocks
     * @param lerpConsumer            the lerpConsumer
     */
    public AbstractWorldBorder(ConsumerSupplierTupel<Position> center,
                               Supplier<Position> minSupplier, Supplier<Position> maxSupplier,
                               ConsumerSupplierTupel<Double> size,
                               ConsumerSupplierTupel<Double> damaheBufferInBlocks,
                               ConsumerSupplierTupel<Integer> warningTimerInSeconds,
                               ConsumerSupplierTupel<Integer> warningDistanceInBlocks,
                               FunctionDoubleDoubleLong lerpConsumer) {
        this.center = center;
        this.minSupplier = minSupplier;
        this.maxSupplier = maxSupplier;
        this.size = size;
        this.damaheBufferInBlocks = damaheBufferInBlocks;
        this.warningTimerInSeconds = warningTimerInSeconds;
        this.warningDistanceInBlocks = warningDistanceInBlocks;
        this.lerpConsumer = lerpConsumer;
    }


    @Override
    public Position center() {
        return this.center.get();
    }

    @Override
    public void center(Position center) {
        this.center.set(center);
    }

    @Override
    public Position min() {
        return minSupplier.get();
    }

    @Override
    public Position max() {
        return maxSupplier.get();
    }

    @Override
    public double size() {
        return this.size.get();
    }

    @Override
    public void size(double radius) {
        this.size.set(radius);
    }

    @Override
    public double damageBufferInBlocks() {
        return damaheBufferInBlocks.get();
    }

    @Override
    public void damageBufferInBlocks(double blocks) {
        damaheBufferInBlocks.set(blocks);
    }

    @Override
    public int warningTimerInSeconds() {
        return warningTimerInSeconds.get();
    }

    @Override
    public void warningTimeInSeconds(int seconds) {
        warningTimerInSeconds.set(seconds);
    }

    @Override
    public int warningDistanceInBlocks() {
        return warningDistanceInBlocks.get();
    }

    @Override
    public void warningDistanceInBlocks(int blocks) {
        warningDistanceInBlocks.set(blocks);
    }

    @Override
    public void lerp(double oldSize, double newSize, long time) {
        lerpConsumer.lerp(oldSize, newSize, time, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
    }

    @Override
    public void lerp(double oldSize, double newSize, Duration time, LocalDateTime startTime) {
        lerpConsumer.lerp(oldSize, newSize, time.getSeconds(), startTime.toEpochSecond(ZoneOffset.UTC));
    }
}
