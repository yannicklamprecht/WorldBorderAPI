package com.github.yannicklamprecht.worldborder.v1_8_R3;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;

public class Impl extends WorldBorderApi {
    public Impl() {
        super(WorldBorder::new, WorldBorder::new);
    }
}
