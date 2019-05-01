package com.astromc.borderapi.type;

import org.bukkit.Location;

public interface SupportedWorldBorder {

	double getCenterX();

	double getCenterZ();

	double getMinX();

	double getMaxX();

	double getMinY();

	double getMaxY();

	void setCenter(double x, double z);

	double getWith();

	double getLength();

	void setRadius(double radius);

	int getDamageBufferInBlocks();

	void setDamageBufferInBlocks(int blocks);

	double getDamagePerSecondPerBlock();

	void setDamagerPerSecondPerBlock(double damage);

	int getWarningTimerInSeconds();

	void setWarningTimeInSeconds(int seconds);

	int getWarningDistanceInBlocks();

	void setWarningDistanceInBlocks(int blocks);

	boolean isInBounds(Location location);

	void lerp(double oldSize, double newSize, long time);

}
