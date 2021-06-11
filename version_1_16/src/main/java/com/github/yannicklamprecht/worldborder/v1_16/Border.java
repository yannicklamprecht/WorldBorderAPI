package com.github.yannicklamprecht.worldborder.v1_16;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApiImpl;

public class Border extends WorldBorderApiImpl {

  public Border() {
    super(WorldBorder::new, WorldBorder::new);
  }
}
