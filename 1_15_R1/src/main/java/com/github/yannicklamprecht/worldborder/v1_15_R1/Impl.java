package com.github.yannicklamprecht.worldborder.v1_15_R1;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;

public class Impl extends WorldBorderApi {

    public Impl() {
        super(WorldBorder::new, WorldBorder::new);
    }
}
