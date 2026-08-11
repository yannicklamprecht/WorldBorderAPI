package com.github.yannicklamprecht.worldborder.impl;

import com.github.yannicklamprecht.worldborder.api.AbstractWorldBorder;
import com.github.yannicklamprecht.worldborder.api.Position;
import com.github.yannicklamprecht.worldborder.api.WorldBorderAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Optional;

import static com.github.yannicklamprecht.worldborder.api.ConsumerSupplierTupel.of;

/**
 * The bukkit adapter impl for the world border
 */
public class WorldBorder extends AbstractWorldBorder {

    private final org.bukkit.WorldBorder handle;

    /**
     * Ctor
     *
     * @param player the bukkit player
     */
    public WorldBorder(Player player) {
        this(Optional.ofNullable(player.getWorldBorder()).orElseGet(Bukkit::createWorldBorder));
    }

    /**
     * Ctor
     *
     * @param world the bukkit world
     */
    public WorldBorder(World world) {
        this(world.getWorldBorder());
    }

    /**
     * Ctor
     *
     * @param worldBorder the bukkit world border
     */
    public WorldBorder(org.bukkit.WorldBorder worldBorder) {
        super(
            of(
                position -> worldBorder.setCenter(position.x(), position.z()),
                () -> new Position(worldBorder.getCenter().getX(), worldBorder.getCenter().getZ())
            ),
            () -> {
                double size = worldBorder.getSize();
                Location center = worldBorder.getCenter();
                return new Position(center.getX() - size / 2, center.getZ() - size / 2);
            },
            () -> {
                double size = worldBorder.getSize();
                Location center = worldBorder.getCenter();
                return new Position(center.getX() + size / 2, center.getZ() + size / 2);
            },
            of(worldBorder::setSize, worldBorder::getSize),
            of(worldBorder::setDamageBuffer, worldBorder::getDamageBuffer),
            of(worldBorder::setWarningTime, worldBorder::getWarningTime),
            of(worldBorder::setWarningDistance, worldBorder::getWarningDistance),
            (oldSize, newSize, time, startTime) -> worldBorder.setSize(newSize, time)
        );
        this.handle = worldBorder;
    }

    @Override
    public void send(Player player, WorldBorderAction worldBorderAction) {
        if (worldBorderAction == WorldBorderAction.INITIALIZE) {
            player.setWorldBorder(handle);
        }
    }
}
