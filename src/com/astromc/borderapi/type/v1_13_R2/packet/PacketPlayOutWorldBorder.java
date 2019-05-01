package com.astromc.borderapi.type.v1_13_R2.packet;

import com.astromc.borderapi.type.v1_13_R2.world.WorldBorder;
import com.astromc.borderapi.type.v1_13_R2.world.WorldBorderAction;
import com.astromc.borderapi.type.SupportedPacketPlayOutWorldBorder;

public class PacketPlayOutWorldBorder extends SupportedPacketPlayOutWorldBorder {

	public PacketPlayOutWorldBorder(WorldBorder border, WorldBorderAction action) {
		super(new net.minecraft.server.v1_13_R2.PacketPlayOutWorldBorder(border.getHandle(), action.getHandle()));
	}


}
