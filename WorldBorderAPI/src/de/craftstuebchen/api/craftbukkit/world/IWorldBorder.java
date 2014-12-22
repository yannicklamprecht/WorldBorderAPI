package de.craftstuebchen.api.craftbukkit.world;
import org.bukkit.Location;

public interface IWorldBorder {

	public double getCenterX();

	public double getCenterY();

	public double getMinX();

	public double getMaxX();

	public double getMinY();

	public double getMaxY();

	public void setCenter(double x, double z);

	public double getWith();

	public double getLength();

	public void setRadius(double radius);

	public int getDamageBufferInBlocks();

	public void setDamageBufferInBlocks(int blocks);

	public double getDamagePerSecondPerBlock();

	public void setDamagerPerSecondPerBlock(double damage);

	public int getWarningTimerInSeconds();

	public void setWarningTimeInSeconds(int seconds);

	public int getWarningDistanceInBlocks();

	public void setWarningDistanceInBlocks(int blocks);

	public boolean isInBounds(Location location);

	public void lerp(double oldSize, double newSize, long time);

}
