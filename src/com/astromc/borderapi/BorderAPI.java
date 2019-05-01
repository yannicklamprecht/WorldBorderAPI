package com.astromc.borderapi;

import com.astromc.borderapi.type.SupportedPacketPlayOutWorldBorder;
import com.astromc.borderapi.type.SupportedVersion;
import com.astromc.borderapi.type.SupportedWorldBorder;
import com.astromc.borderapi.type.SupportedWorldBorderAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class BorderAPI extends JavaPlugin implements Listener {

    private static BorderAPI instance;

    private SupportedVersion version;

    @Override
    public void onEnable() {
        instance = this;
        try {
            this.version = VersionSupport.getVersion();
        } catch (Exception exception) {
            exception.printStackTrace();
            // don't bother, version isn't supported
            setEnabled(false);
        }
    }

    /**
     * Send a red screen to the player for 10 seconds.
     * @param player - the player to send the red border to
     */

    public static void sendRedBorder(Player player) {
        sendRedBorder(player, 10);
    }

    /**
     * Send a red screen to the player for n seconds.
     * @param player - the player to send the border to
     * @param time - the duration of the red border
     */

    public static void sendRedBorder(final Player player, int time) {
        final SupportedWorldBorder border = VersionSupport.getWorldBorder(player);

        assert border != null;

        border.setWarningDistanceInBlocks((int) border.getLength() / 2);

        VersionSupport.sendPacketPlayOutWorldBorder(player, VersionSupport.getPacketPlayOutWorldBorder(
                border, SupportedWorldBorderAction.SET_WARNING_BLOCKS
        ));

        border.setWarningDistanceInBlocks(0);

        getInstance().getServer().getScheduler().runTaskLater(instance, () -> {
            final SupportedPacketPlayOutWorldBorder removeBorderPacket = VersionSupport.getPacketPlayOutWorldBorder(
                    border, SupportedWorldBorderAction.SET_WARNING_BLOCKS
            );
            VersionSupport.sendPacketPlayOutWorldBorder(player, removeBorderPacket);
        }, time * 20L);
    }

    /**
     * Set the border for this player at n radius from the world spawn location.
     * @param player - the player to send the border to
     * @param radius - the radius of the border
     */

    public static void setBorder(Player player, double radius) {
        setBorder(player, radius, player.getWorld().getSpawnLocation());
    }

    public static void setBorder(Player player, double radius, Location location) {
        SupportedWorldBorder border = VersionSupport.getWorldBorder(player);;

        assert border != null;

        border.setRadius(radius);
        border.setCenter(location.getX(), location.getZ());

        VersionSupport.sendPacketPlayOutWorldBorder(player, VersionSupport.getPacketPlayOutWorldBorder(
                border, SupportedWorldBorderAction.SET_SIZE
        ));

        VersionSupport.sendPacketPlayOutWorldBorder(player, VersionSupport.getPacketPlayOutWorldBorder(
                border, SupportedWorldBorderAction.SET_CENTER
        ));
    }

    /**
     * Set the border for all players at n radius from the world spawn location.
     * @param radius - the radius of the border
     */

    public static void setBorder(double radius) {
        setBorder(radius, 10);
    }

    @SuppressWarnings("deprecation")
    public static void setBorder(double radius, int seconds) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            setBorder(p, radius, seconds);
        }
    }

    /**
     * Grow the border for this player to n radius over n seconds.
     * @param player - the player to send the border to
     * @param radius - the radius of the border
     * @param seconds - duration of growth
     */

    public static void setBorder(Player player, double radius, int seconds) {
        SupportedWorldBorder border = VersionSupport.getWorldBorder(player);;
        assert border != null;
        border.lerp(border.getLength(), radius, seconds);
        VersionSupport.sendPacketPlayOutWorldBorder(player, VersionSupport.getPacketPlayOutWorldBorder(
                border, SupportedWorldBorderAction.LERP_SIZE
        ));
    }

    /**
     * Send a border packet to the player.
     * @param player - the player to send the border to
     * @param border - a SupportedWorldBorder
     * @param action - a SupportedWorldBorderAction
     */

    public static void setBorder(Player player, SupportedWorldBorder border, SupportedWorldBorderAction action) {
        VersionSupport.sendPacketPlayOutWorldBorder(player, VersionSupport.getPacketPlayOutWorldBorder(
                border, action
        ));
    }

    public static BorderAPI getInstance() {
        return instance;
    }

    public SupportedVersion getVersion() {
        return version;
    }

}
