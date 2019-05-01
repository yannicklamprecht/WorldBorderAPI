package com.astromc.borderapi;

import com.astromc.borderapi.type.SupportedPacketPlayOutWorldBorder;
import com.astromc.borderapi.type.SupportedVersion;
import com.astromc.borderapi.type.SupportedWorldBorder;
import com.astromc.borderapi.type.SupportedWorldBorderAction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VersionSupport {

    public static SupportedWorldBorder getWorldBorder(Player player) {
        // @TODO maybe use reflection so the casting isn't so ugly...
        switch (BorderAPI.getInstance().getVersion()) {
            case v1_8_R3:
                return new com.astromc.borderapi.type.v1_8_R3.world.WorldBorder(player);
            case v1_11_R1:
                return new com.astromc.borderapi.type.v1_11_R1.world.WorldBorder(player);
            case v1_12_R1:
                return new com.astromc.borderapi.type.v1_12_R1.world.WorldBorder(player);
            default:
                return new com.astromc.borderapi.type.v1_13_R2.world.WorldBorder(player);
        }
    }

    public static SupportedPacketPlayOutWorldBorder getPacketPlayOutWorldBorder(SupportedWorldBorder border, SupportedWorldBorderAction action) {
        // @TODO maybe use reflection so the casting isn't so ugly...
        switch (BorderAPI.getInstance().getVersion()) {
            case v1_8_R3:
                return new com.astromc.borderapi.type.v1_8_R3.packet.PacketPlayOutWorldBorder(
                        (com.astromc.borderapi.type.v1_8_R3.world.WorldBorder) border,
                        com.astromc.borderapi.type.v1_8_R3.world.WorldBorderAction.valueOf(action.name()));
            case v1_11_R1:
                return new com.astromc.borderapi.type.v1_11_R1.packet.PacketPlayOutWorldBorder(
                        (com.astromc.borderapi.type.v1_11_R1.world.WorldBorder) border,
                        com.astromc.borderapi.type.v1_11_R1.world.WorldBorderAction.valueOf(action.name()));
            case v1_12_R1:
                return new com.astromc.borderapi.type.v1_12_R1.packet.PacketPlayOutWorldBorder(
                        (com.astromc.borderapi.type.v1_12_R1.world.WorldBorder) border,
                        com.astromc.borderapi.type.v1_12_R1.world.WorldBorderAction.valueOf(action.name()));
            default:
                return new com.astromc.borderapi.type.v1_13_R2.packet.PacketPlayOutWorldBorder(
                        (com.astromc.borderapi.type.v1_13_R2.world.WorldBorder) border,
                        com.astromc.borderapi.type.v1_13_R2.world.WorldBorderAction.valueOf(action.name()));
        }
    }

    public static void sendPacketPlayOutWorldBorder(Player player, SupportedPacketPlayOutWorldBorder packet) {
        // @TODO maybe use reflection so the casting isn't so ugly...
        switch (BorderAPI.getInstance().getVersion()) {
            case v1_8_R3:
                ((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer) player)
                        .getHandle().playerConnection
                        .sendPacket((net.minecraft.server.v1_8_R3.Packet<?>) packet.getAnonymousPacket());
                break;
            case v1_11_R1:
                ((org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer) player)
                        .getHandle().playerConnection
                        .sendPacket((net.minecraft.server.v1_11_R1.Packet<?>) packet.getAnonymousPacket());
                break;
            case v1_12_R1:
                ((org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer) player)
                        .getHandle().playerConnection
                        .sendPacket((net.minecraft.server.v1_12_R1.Packet<?>) packet.getAnonymousPacket());
                break;
            default:
                ((org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer) player)
                        .getHandle().playerConnection
                        .sendPacket((net.minecraft.server.v1_13_R2.Packet<?>) packet.getAnonymousPacket());
                break;
        }
    }

    public static SupportedVersion getVersion() throws Exception {
        String version = "unknown";
        try {
            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        } catch (ArrayIndexOutOfBoundsException ignored) {
            // do nothing
        }
        switch (version) {
            case "v1_8_R3":
                return SupportedVersion.v1_8_R3;
            case "v1_11_R1":
                return SupportedVersion.v1_11_R1;
            case "v1_12_R1":
                return SupportedVersion.v1_12_R1;
            default:
                return SupportedVersion.v1_13_R2;
        }
    }

}
