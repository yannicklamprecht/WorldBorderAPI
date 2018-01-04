package de.craftstuebchen.api.worldborder;

import de.craftstuebchen.api.craftbukkit.entity.CraftPlayer;
import de.craftstuebchen.api.craftbukkit.packets.PacketPlayOutWorldBorder;
import de.craftstuebchen.api.craftbukkit.world.IWorldBorder;
import de.craftstuebchen.api.craftbukkit.world.WorldBorder;
import de.craftstuebchen.api.craftbukkit.world.WorldBorderAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class WorldBorderAPI {

    private static WorldBorderAPI inst = new WorldBorderAPI();

    private HashSet<Player> customWorldBorder = new HashSet<Player>();

    public static WorldBorderAPI inst() {
        return inst;
    }

    private WorldBorderAPI(){}

    public void sendRedScreen(Player player) {
        this.sendRedScreen(player, 10);
    }

    /**
     * Will send a red edge to the given player a certain Time
     *
     * @param player The player that will get the red edge on screen
     * @param time   The time the red edge disappears
     */
    public void sendRedScreen(final Player player, int time) {

        WorldBorder border = new WorldBorder(player);

        border.setWarningDistanceInBlocks((int) border.getLength() / 2);

        PacketPlayOutWorldBorder borderpacket = new PacketPlayOutWorldBorder(
                border, WorldBorderAction.SET_WARNING_BLOCKS);

        border.setWarningDistanceInBlocks(0);

        final PacketPlayOutWorldBorder WT = new PacketPlayOutWorldBorder(border,
                WorldBorderAction.SET_WARNING_BLOCKS);

        sendPacket(player, borderpacket);
        customWorldBorder.add(player);

        Bukkit.getScheduler().runTaskLater(WorldBorderPlugin.inst(),
                () -> {
                    sendPacket(player, WT);
                    customWorldBorder.remove(player);
                }, time * 20L);

    }

    private void sendPacket(Player player, PacketPlayOutWorldBorder packet) {
        CraftPlayer cplayer = new CraftPlayer(player);
        cplayer.sendPacket(packet);
    }

    /**
     * Will set a custom border for player
     *
     * @param player The player who will get the border
     * @param radius The borderradius
     */
    public void setBorder(Player player, double radius) {
        setBorder(player, radius, player.getWorld().getSpawnLocation());
    }

    /**
     * Will set a custom border for player
     *
     * @param player   The player who will get the border
     * @param radius   The borderradius
     * @param location The center location of the worldborder
     */
    public void setBorder(Player player, double radius, Location location) {

        IWorldBorder border = new WorldBorder(player);

        border.setRadius(radius);
        border.setCenter(location.getX(), location.getZ());

        sendPacket(player, new PacketPlayOutWorldBorder(border,
                WorldBorderAction.SET_SIZE));
        sendPacket(player, new PacketPlayOutWorldBorder(border, WorldBorderAction.SET_CENTER));
        customWorldBorder.add(player);
    }

    /**
     * Will set the border for everyone after 10 seconds
     *
     * @param radius The radius of the border
     */
    public void setBorder(double radius) {
        setBorder(radius, 10);
    }

    /**
     * Will set the border for everyone
     *
     * @param radius  The radius of the border
     * @param seconds The seconds until border reappear
     */
    @SuppressWarnings("deprecation")
    public void setBorder(double radius, int seconds) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            setBorder(p, radius, seconds);
        }
    }

    /**
     * Will set the border for the given player centered at worldspawnlocation
     *
     * @param player  The player who will get the border
     * @param radius  the radius of the border
     * @param seconds The seconds until the border will reappear
     */
    public void setBorder(Player player, double radius, int seconds) {
        WorldBorder border = new WorldBorder(player);
        border.lerp(border.getLength(), radius, seconds);
        sendPacket(player, new PacketPlayOutWorldBorder(border,
                WorldBorderAction.LERP_SIZE));
    }

    /**
     * Will set a border for given player
     *
     * @param player The player that gets the border
     * @param border The border that will be set
     * @param action The action of the border
     */
    public void setBorder(Player player, IWorldBorder border,
                          WorldBorderAction action) {
        sendPacket(player, new PacketPlayOutWorldBorder(border, action));
    }

    public IWorldBorder getWorldBorder(Player p) {
        return new WorldBorder(p);
    }

}
