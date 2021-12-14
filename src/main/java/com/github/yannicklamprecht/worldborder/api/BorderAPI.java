package com.github.yannicklamprecht.worldborder.api;


import org.bukkit.Bukkit;

@Deprecated
public final class BorderAPI {

    private BorderAPI() {
    }

    @Deprecated
    public static WorldBorderApi getApi() {
        var registration = Bukkit.getServicesManager().getRegistration(WorldBorderApi.class);
        if (registration == null) {
            return null;
        }
        return registration.getProvider();
    }
}
