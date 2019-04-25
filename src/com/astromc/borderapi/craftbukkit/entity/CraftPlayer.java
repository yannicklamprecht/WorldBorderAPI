package com.astromc.borderapi.craftbukkit.entity;

import org.bukkit.entity.Player;

import com.astromc.borderapi.craftbukkit.packets.PacketPlayOutWorldBorder;

public class CraftPlayer {

	private org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer	player;

	public CraftPlayer(Player player) {
		this.player = (org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer) player;
	}

	public void sendPacket(PacketPlayOutWorldBorder packetPlayOutWorldBorder) {
		player.getHandle().playerConnection.sendPacket(packetPlayOutWorldBorder
				.getHandle());
	}

}
