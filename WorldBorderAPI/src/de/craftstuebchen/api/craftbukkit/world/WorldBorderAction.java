package de.craftstuebchen.api.craftbukkit.world;

import net.minecraft.server.v1_8_R1.EnumWorldBorderAction;

public enum WorldBorderAction {

	INITIALIZE(EnumWorldBorderAction.INITIALIZE), LERP_SIZE(
			EnumWorldBorderAction.LERP_SIZE), SET_CENTER(
			EnumWorldBorderAction.SET_CENTER), SET_SIZE(
			EnumWorldBorderAction.SET_SIZE), SET_WARNING_BLOCKS(
			EnumWorldBorderAction.SET_WARNING_BLOCKS), SET_WARNING_TIME(
			EnumWorldBorderAction.SET_WARNING_TIME);

	private EnumWorldBorderAction	action;

	WorldBorderAction(EnumWorldBorderAction action) {
		this.action = action;
	}

	public EnumWorldBorderAction getHandle() {
		return action;
	}

}
