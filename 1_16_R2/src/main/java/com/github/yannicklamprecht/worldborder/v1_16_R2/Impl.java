package com.github.yannicklamprecht.worldborder.v1_16_R2;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApiImpl;

public class Impl extends WorldBorderApiImpl {

    public Impl() {
        super(WorldBorder::new, WorldBorder::new);
    }
}
