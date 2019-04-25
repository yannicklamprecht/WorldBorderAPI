package com.astromc.borderapi.craftbukkit.packets;

import com.astromc.borderapi.craftbukkit.world.WorldBorder;
import com.astromc.borderapi.craftbukkit.world.IWorldBorder;
import com.astromc.borderapi.craftbukkit.world.WorldBorderAction;

public class PacketPlayOutWorldBorder {
	private net.minecraft.server.v1_13_R2.PacketPlayOutWorldBorder borderPacket;

	public PacketPlayOutWorldBorder(IWorldBorder border,
			WorldBorderAction action) {

		this.borderPacket = new net.minecraft.server.v1_13_R2.PacketPlayOutWorldBorder(
				((WorldBorder) border).getHandle(), action.getHandle());

	}

	public net.minecraft.server.v1_13_R2.PacketPlayOutWorldBorder getHandle() {
		return borderPacket;
	}

}
