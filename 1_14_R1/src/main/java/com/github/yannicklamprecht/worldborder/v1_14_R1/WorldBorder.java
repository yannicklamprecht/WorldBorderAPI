package com.github.yannicklamprecht.worldborder.v1_14_R1;

import com.github.yannicklamprecht.worldborder.api.AbstractWorldBorder;
import com.github.yannicklamprecht.worldborder.api.Position;
import net.minecraft.server.v1_14_R1.ChunkCoordIntPair;
import org.bukkit.Location;

/**
 * Created by ysl3000
 */
public class WorldBorder extends AbstractWorldBorder {

    public WorldBorder() {
        this(new net.minecraft.server.v1_14_R1.WorldBorder());
    }

    public WorldBorder(net.minecraft.server.v1_14_R1.WorldBorder worldBorder) {
        super(
        () -> new Position(worldBorder.getCenterX(), worldBorder.getCenterZ()),
        (position -> worldBorder.setCenter(position.getX(), position.getZ())),
        () -> new Position(worldBorder.c(), worldBorder.d()),
        () -> new Position(worldBorder.e(), worldBorder.f()),
        worldBorder::getSize,
        worldBorder::setSize,
        worldBorder::m,
        worldBorder::a,
        worldBorder::getDamageAmount,
        worldBorder::setDamageAmount,
        worldBorder::getWarningTime,
        worldBorder::setWarningTime,
        worldBorder::getWarningDistance,
        worldBorder::setWarningDistance,
        (Location location) -> worldBorder.isInBounds(new ChunkCoordIntPair(location.getBlockX(), location.getBlockZ())),
        worldBorder::transitionSizeBetween
        );
    }
}
