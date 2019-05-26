package com.github.yannicklamprecht.worldborder.v1_14_R1;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;

/**
 * Created by ysl3000
 */
public class Impl extends WorldBorderApi {

    public Impl() {
        super(WorldBorder::new, WorldBorder::new);
    }
}
