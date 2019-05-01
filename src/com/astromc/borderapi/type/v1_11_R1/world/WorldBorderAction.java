package com.astromc.borderapi.type.v1_11_R1.world;

import net.minecraft.server.v1_11_R1.PacketPlayOutWorldBorder;

public enum WorldBorderAction {

	INITIALIZE(PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE), LERP_SIZE(
			PacketPlayOutWorldBorder.EnumWorldBorderAction.LERP_SIZE), SET_CENTER(
			PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_CENTER), SET_SIZE(
			PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_SIZE), SET_WARNING_BLOCKS(
			PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_WARNING_BLOCKS), SET_WARNING_TIME(
			PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_WARNING_TIME);

	private PacketPlayOutWorldBorder.EnumWorldBorderAction action;

	WorldBorderAction(PacketPlayOutWorldBorder.EnumWorldBorderAction action) {
		this.action = action;
	}

	public PacketPlayOutWorldBorder.EnumWorldBorderAction getHandle() {
		return action;
	}

}
