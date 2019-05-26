package com.github.yannicklamprecht.worldborder.v1_8_R3;

import com.github.yannicklamprecht.worldborder.api.AbstractWorldBorder;
import com.github.yannicklamprecht.worldborder.api.Position;
import net.minecraft.server.v1_8_R3.ChunkCoordIntPair;
import org.bukkit.Location;

public class WorldBorder extends AbstractWorldBorder {

    public WorldBorder() {
        this(new net.minecraft.server.v1_8_R3.WorldBorder());
    }

    public WorldBorder(net.minecraft.server.v1_8_R3.WorldBorder worldBorder) {
        super(
        () -> new Position(worldBorder.getCenterX(), worldBorder.getCenterZ()),
        (position -> worldBorder.setCenter(position.getX(), position.getZ())),
        () -> new Position(worldBorder.b(), worldBorder.c()),
        () -> new Position(worldBorder.d(), worldBorder.e()),
        worldBorder::getSize,
        worldBorder::setSize,
        worldBorder::l,
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
