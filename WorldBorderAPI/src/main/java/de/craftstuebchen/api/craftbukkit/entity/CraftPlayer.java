package de.craftstuebchen.api.craftbukkit.entity;

import org.bukkit.entity.Player;

import de.craftstuebchen.api.craftbukkit.packets.PacketPlayOutWorldBorder;

public class CraftPlayer {

	private org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer	player;

	public CraftPlayer(Player player) {
		this.player = (org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer) player;
	}

	public void sendPacket(PacketPlayOutWorldBorder packetPlayOutWorldBorder) {
		player.getHandle().playerConnection.sendPacket(packetPlayOutWorldBorder
				.getHandle());
	}

}
