package com.astromc.borderapi.type.v1_11_R1.packet;

import com.astromc.borderapi.type.v1_11_R1.world.WorldBorder;
import com.astromc.borderapi.type.v1_11_R1.world.WorldBorderAction;
import com.astromc.borderapi.type.SupportedPacketPlayOutWorldBorder;
import net.minecraft.server.v1_11_R1.Packet;

public class PacketPlayOutWorldBorder extends SupportedPacketPlayOutWorldBorder {

	public PacketPlayOutWorldBorder(WorldBorder border, WorldBorderAction action) {
		super(new net.minecraft.server.v1_11_R1.PacketPlayOutWorldBorder(border.getHandle(), action.getHandle()));
	}

	public Packet<?> getBorderPacket() {
		return (Packet<?>) getAnonymousPacket();
	}

}
