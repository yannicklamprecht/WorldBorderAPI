package com.github.yannicklamprecht.worldborder.plugin;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class WorldBorderDataTagType implements PersistentDataType<PersistentDataContainer, WorldBorderData> {
    public static final WorldBorderDataTagType TYPE = new WorldBorderDataTagType();

    private WorldBorderDataTagType(){}

    @Override
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @Override
    public Class<WorldBorderData> getComplexType() {
        return WorldBorderData.class;
    }

    @Override
    public PersistentDataContainer toPrimitive(WorldBorderData complex, PersistentDataAdapterContext context) {
        return null;
    }

    @Override
    public WorldBorderData fromPrimitive(PersistentDataContainer primitive, PersistentDataAdapterContext context) {
        return null;
    }
}
