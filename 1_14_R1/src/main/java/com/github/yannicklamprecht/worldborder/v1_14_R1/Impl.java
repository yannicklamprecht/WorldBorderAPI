package com.github.yannicklamprecht.worldborder.v1_14_R1;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApiImpl;

/**
 * Created by ysl3000
 */
public class Impl extends WorldBorderApiImpl {

    public Impl() {
        super(WorldBorder::new, WorldBorder::new);
    }
}
