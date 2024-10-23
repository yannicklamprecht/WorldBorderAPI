package com.github.yannicklamprecht.worldborder.impl;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApiImpl;

/**
 * NMS impl
 */
public class Border extends WorldBorderApiImpl {

    /**
     * ctor
     */
    public Border() {
        super(WorldBorder::new, WorldBorder::new);
    }
}
