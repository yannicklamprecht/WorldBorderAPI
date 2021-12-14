package com.github.yannicklamprecht.worldborder.api;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public record ConsumerSupplierTupel<T>(Consumer<T> consumer, Supplier<T> supplier) {
    public ConsumerSupplierTupel {
        Objects.requireNonNull(consumer);
        Objects.requireNonNull(supplier);
    }

    public static <T> ConsumerSupplierTupel<T> of(Consumer<T> consumer, Supplier<T> supplier) {
        return new ConsumerSupplierTupel<>(consumer, supplier);
    }

    public T get() {
        return supplier.get();
    }

    public void set(T value) {
        consumer.accept(value);
    }
}
