package com.github.yannicklamprecht.worldborder.v1_15_R1;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApiImpl;

public class Impl extends WorldBorderApiImpl {

    public Impl() {
        super(WorldBorder::new, WorldBorder::new);
    }
}
