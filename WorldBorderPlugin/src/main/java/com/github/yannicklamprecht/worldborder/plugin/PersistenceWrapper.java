package com.github.yannicklamprecht.worldborder.plugin;

import com.github.yannicklamprecht.worldborder.api.IWorldBorder;
import com.github.yannicklamprecht.worldborder.api.Position;
import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class PersistenceWrapper implements WorldBorderApi {

    private final WorldBorderApi worldBorderApi;
    private final NamespacedKey worldBorderDataKey;

    public PersistenceWrapper(JavaPlugin javaPlugin, WorldBorderApi worldBorderApi) {
        this.worldBorderApi = worldBorderApi;
        this.worldBorderDataKey = new NamespacedKey(javaPlugin, "world_border_data");
    }

    @Override
    public IWorldBorder getWorldBorder(Player p) {
        IWorldBorder worldBorder = worldBorderApi.getWorldBorder(p);
        PersistentDataContainer persistentDataContainer = p.getPersistentDataContainer();
        if (persistentDataContainer.has(worldBorderDataKey, WorldBorderDataTagType.TYPE)) {
            applyWorldDataToWorldBorder(worldBorder, persistentDataContainer.get(worldBorderDataKey, WorldBorderDataTagType.TYPE));
        }
        return worldBorder;
    }

    @Override
    public IWorldBorder getWorldBorder(World world) {
        return worldBorderApi.getWorldBorder(world);
    }

    @Override
    public void resetWorldBorderToGlobal(Player player) {
        worldBorderApi.resetWorldBorderToGlobal(player);
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        if (persistentDataContainer.has(worldBorderDataKey, WorldBorderDataTagType.TYPE)) {
            persistentDataContainer.remove(worldBorderDataKey);
        }
    }

    @Override
    public void setBorder(Player player, double size) {
        worldBorderApi.setBorder(player, size);
        modifyAndUpdateWorldData(player, worldBorderData -> worldBorderData.setSize(size));
    }

    @Override
    public void setBorder(Player player, double size, Location location) {
        worldBorderApi.setBorder(player, size, location);
        modifyAndUpdateWorldData(player, worldBorderData -> {
            worldBorderData.setSize(size);
            worldBorderData.setCenter(location.getBlockX(), location.getBlockZ());
        });
    }

    @Override
    public void sendRedScreenForSeconds(Player player, long timeSeconds, JavaPlugin javaPlugin) {
        worldBorderApi.sendRedScreenForSeconds(player, timeSeconds, javaPlugin);
    }

    @Override
    public void setBorder(Player player, double size, long milliSeconds) {
        worldBorderApi.setBorder(player, size, milliSeconds);
        modifyAndUpdateWorldData(player, worldBorderData -> worldBorderData.setSize(size));
    }

    @Override
    public void setBorder(Player player, double size, long time, TimeUnit timeUnit) {
        worldBorderApi.setBorder(player, size, time, timeUnit);
        modifyAndUpdateWorldData(player, worldBorderData -> worldBorderData.setSize(size));
    }

    private void applyWorldDataToWorldBorder(IWorldBorder iWorldBorder, WorldBorderData worldBorderData) {
        worldBorderData.applyCenter((x, z) -> iWorldBorder.setCenter(new Position(x, z)));
        iWorldBorder.setSize(worldBorderData.getSize());
        iWorldBorder.setDamageBufferInBlocks(worldBorderData.getDamageBuffer());
        iWorldBorder.setDamagerPerSecondPerBlock(worldBorderData.getDamageAmount());
        iWorldBorder.setWarningDistanceInBlocks(worldBorderData.getWarningDistance());
        iWorldBorder.setWarningTimeInSeconds(worldBorderData.getWarningTimeSeconds());
    }

    private void modifyAndUpdateWorldData(Player player, Consumer<WorldBorderData> worldBorderDataConsumer) {
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        WorldBorderData worldBorderData = new WorldBorderData();
        if (persistentDataContainer.has(worldBorderDataKey, WorldBorderDataTagType.TYPE)) {
            worldBorderData = persistentDataContainer.get(worldBorderDataKey, WorldBorderDataTagType.TYPE);
        }
        worldBorderDataConsumer.accept(worldBorderData);
        persistentDataContainer.set(worldBorderDataKey, WorldBorderDataTagType.TYPE, worldBorderData);
    }
}
