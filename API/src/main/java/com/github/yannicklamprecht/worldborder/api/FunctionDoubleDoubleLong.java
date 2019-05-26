package com.github.yannicklamprecht.worldborder.api;

/**
 * Created by ysl3000
 */
@FunctionalInterface
public interface FunctionDoubleDoubleLong {
    void lerp(double oldSize, double newSize, long time);
}
