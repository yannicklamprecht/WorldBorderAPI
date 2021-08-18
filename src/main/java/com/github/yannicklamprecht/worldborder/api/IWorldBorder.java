package com.github.yannicklamprecht.worldborder.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface IWorldBorder {


  default Position getCenter() {
    return center();
  }

  default void setCenter(Position center) {
    center(center);
  }

  default Position getMin() {
    return min();
  }

  default Position getMax() {
    return max();
  }

  default double getSize() {
    return size();
  }

  default void setSize(double radius) {
    size(radius);
  }

  default double getDamageBufferInBlocks() {
    return damageBufferInBlocks();
  }

  default void setDamageBufferInBlocks(double blocks) {
    damageBufferInBlocks(blocks);
  }

  default double getDamagePerSecondPerBlock() {
    return damagePerSecondPerBlock();
  }

  default void setDamagePerSecondPerBlock(double damage) {
    damagePerSecondPerBlock(damage);
  }

  default int getWarningTimerInSeconds() {
    return warningTimerInSeconds();
  }

  default void setWarningTimeInSeconds(int seconds) {
    warningTimeInSeconds(seconds);
  }

  default int getWarningDistanceInBlocks() {
    return warningDistanceInBlocks();
  }

  default void setWarningDistanceInBlocks(int blocks) {
    warningDistanceInBlocks(blocks);
  }

  void center(Position center);

  Position center();

  Position min();

  Position max();

  double size();

  void size(double radius);

  double damageBufferInBlocks();

  void damageBufferInBlocks(double blocks);

  double damagePerSecondPerBlock();

  void damagePerSecondPerBlock(double damage);

  int warningTimerInSeconds();

  void warningTimeInSeconds(int seconds);

  int warningDistanceInBlocks();

  void warningDistanceInBlocks(int blocks);

  boolean isInBounds(Location location);

  void lerp(double oldSize, double newSize, long time);

  void send(Player player, WorldBorderAction worldBorderAction);
}
