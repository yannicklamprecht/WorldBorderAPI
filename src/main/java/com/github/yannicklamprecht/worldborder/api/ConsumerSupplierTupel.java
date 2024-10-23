package com.github.yannicklamprecht.worldborder.api;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A tuple containing a Consumer and Supplier for a specific data type.
 *
 * @param <T> the type of data
 * @param consumer the consumer
 * @param supplier the supplier
 */
public record ConsumerSupplierTupel<T>(Consumer<T> consumer, Supplier<T> supplier) {
    /**
     * Companion ctor
     *
     * @param consumer the consumer
     * @param supplier the supplier
     */
    public ConsumerSupplierTupel {
        Objects.requireNonNull(consumer);
        Objects.requireNonNull(supplier);
    }

    /**
     * A tuple containing a Consumer and Supplier for a specific data type.
     *
     * @param <T> the type of data
     *
     * @param consumer the consumer function that accepts a value of type T
     * @param supplier the supplier function that supplies a value of type T
     * @return an instance of consumer supplier tupel
     */
    public static <T> ConsumerSupplierTupel<T> of(Consumer<T> consumer, Supplier<T> supplier) {
        return new ConsumerSupplierTupel<>(consumer, supplier);
    }

    /**
     * Retrieves the value from the supplier.
     *
     * @return the value retrieved from the supplier
     */
    public T get() {
        return supplier.get();
    }

    /**
     * Sets the value of the ConsumerSupplierTuple.
     *
     * @param value the value to set
     */
    public void set(T value) {
        consumer.accept(value);
    }
}
