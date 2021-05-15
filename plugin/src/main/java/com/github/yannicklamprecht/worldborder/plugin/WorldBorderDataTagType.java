package com.github.yannicklamprecht.worldborder.plugin;

import com.github.yannicklamprecht.worldborder.api.WorldBorderData;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class WorldBorderDataTagType implements PersistentDataType<PersistentDataContainer, WorldBorderData> {

    private final NamespacedKey sizeKey;
    private final NamespacedKey xKey;
    private final NamespacedKey zKey;
    private final NamespacedKey damageBufferInBlocksKey;
    private final NamespacedKey damageAmountKey;
    private final NamespacedKey warningTimeSecondsKey;
    private final NamespacedKey warningDistanceKey;

    public WorldBorderDataTagType(JavaPlugin javaPlugin) {
        this.sizeKey = new NamespacedKey(javaPlugin, "size");
        this.xKey = new NamespacedKey(javaPlugin, "center_x");
        this.zKey = new NamespacedKey(javaPlugin, "center_z");
        this.damageBufferInBlocksKey = new NamespacedKey(javaPlugin, "damage_buffer_in_blocks");
        this.damageAmountKey = new NamespacedKey(javaPlugin, "damage_amount");
        this.warningTimeSecondsKey = new NamespacedKey(javaPlugin, "warning_time_seconds");
        this.warningDistanceKey = new NamespacedKey(javaPlugin, "warning_distance");
    }


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
        PersistentDataContainer persistentDataContainer = context.newPersistentDataContainer();

        persistentDataContainer.set(sizeKey, PersistentDataType.DOUBLE, complex.getSize());
        complex.applyCenter((x, z) -> {
            persistentDataContainer.set(xKey, PersistentDataType.DOUBLE, x);
            persistentDataContainer.set(zKey, PersistentDataType.DOUBLE, z);
        });
        persistentDataContainer.set(damageBufferInBlocksKey, PersistentDataType.DOUBLE, complex.getDamageBuffer());
        persistentDataContainer.set(damageAmountKey, PersistentDataType.DOUBLE, complex.getDamageAmount());
        persistentDataContainer.set(warningTimeSecondsKey, PersistentDataType.INTEGER, complex.getWarningTimeSeconds());
        persistentDataContainer.set(warningDistanceKey, PersistentDataType.INTEGER, complex.getWarningDistance());

        return persistentDataContainer;
    }

    @Override
    public WorldBorderData fromPrimitive(PersistentDataContainer primitive, PersistentDataAdapterContext context) {

        WorldBorderData worldBorderData = new WorldBorderData();

        get(primitive, sizeKey, PersistentDataType.DOUBLE).ifPresent(worldBorderData::setSize);
        Optional<Double> centerX = get(primitive, xKey, PersistentDataType.DOUBLE);
        Optional<Double> centerZ = get(primitive, zKey, PersistentDataType.DOUBLE);
        if (centerX.isPresent() && centerZ.isPresent()) {
            worldBorderData.setCenter(centerX.get(), centerZ.get());
        }
        get(primitive, damageBufferInBlocksKey, PersistentDataType.DOUBLE).ifPresent(worldBorderData::setDamageBuffer);
        get(primitive, damageAmountKey, PersistentDataType.DOUBLE).ifPresent(worldBorderData::setDamageAmount);
        get(primitive, warningTimeSecondsKey, PersistentDataType.INTEGER).ifPresent(worldBorderData::setWarningTimeSeconds);
        get(primitive, warningDistanceKey, PersistentDataType.INTEGER).ifPresent(worldBorderData::setWarningDistance);

        return worldBorderData;
    }

    private <T, Z> Optional<Z> get(PersistentDataContainer persistentDataContainer, NamespacedKey namespacedKey, PersistentDataType<T, Z> type) {
        if (persistentDataContainer.has(namespacedKey, type)) {
            return Optional.ofNullable(persistentDataContainer.get(namespacedKey, type));
        }
        return Optional.empty();
    }

}
