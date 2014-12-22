package de.craftstuebchen.api.craftbukkit.packets;

import de.craftstuebchen.api.craftbukkit.world.WorldBorder;
import de.craftstuebchen.api.craftbukkit.world.WorldBorderAction;

public class PacketPlayOutWorldBorder {
	private net.minecraft.server.v1_8_R1.PacketPlayOutWorldBorder	borderPacket;

	public PacketPlayOutWorldBorder(WorldBorder border, WorldBorderAction action) {

		this.borderPacket = new net.minecraft.server.v1_8_R1.PacketPlayOutWorldBorder(
				border.getHandle(), action.getHandle());

	}

	public net.minecraft.server.v1_8_R1.PacketPlayOutWorldBorder getHandle() {
		return borderPacket;
	}

}
