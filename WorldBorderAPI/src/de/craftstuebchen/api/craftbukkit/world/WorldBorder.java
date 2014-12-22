package de.craftstuebchen.api.craftbukkit.world;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.entity.Player;

public class WorldBorder implements IWorldBorder{

	private net.minecraft.server.v1_8_R1.WorldBorder	border;

	public WorldBorder(World bukkitWorld) {
			this.border =((CraftWorld) bukkitWorld).getHandle().af();
	}
	
	public WorldBorder(Player player){
		try {
			this.border = net.minecraft.server.v1_8_R1.WorldBorder.class.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public net.minecraft.server.v1_8_R1.WorldBorder getHandle() {
		return border;
	}

	public double getCenterX() {
		return border.f();
	}

	public double getCenterY() {
		return border.g();
	}

	public double getMinX() {
		return border.b();
	}

	public double getMaxX() {
		return border.e();
	}

	public double getMinY() {
		return border.c();
	}

	public double getMaxY() {
		return border.e();
	}

	public void setCenter(double x, double z) {
		border.c(x, z);
	}

	public double getWith() {
		return border.h();
	}

	public double getLength() {
		return border.j();
	}

	public void setRadius(double radius) {
		border.a(radius);
	}

	public int getDamageBufferInBlocks() {
		return border.q();
	}

	public void setDamageBufferInBlocks(int blocks) {
		border.c(blocks);
	}

	public double getDamagePerSecondPerBlock() {
		return border.n();
	}

	public void setDamagerPerSecondPerBlock(double damage) {
		border.c(damage);
	}

	public int getWarningTimerInSeconds() {
		return border.p();
	}

	public void setWarningTimeInSeconds(int seconds) {
		border.b(seconds);
	}

	public int getWarningDistanceInBlocks() {
		return border.q();
	}

	public void setWarningDistanceInBlocks(int blocks) {
		border.c(blocks);
	}
	public boolean isInBounds(Location location){
		return border.isInBounds(location.getBlockX(), location.getBlockZ());
	}
	
	public void lerp(double oldSize,double newSize, long time){
		border.a(oldSize, newSize, time);
	}
}
