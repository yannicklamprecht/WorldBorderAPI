package com.github.yannicklamprecht.worldborder.api;


public final class BorderAPI {

    private BorderAPI(){}

    private static WorldBorderApi api;

    @Deprecated
    public static void setWorldBorderApi(WorldBorderApi worldBorderApi) {
        if (api != null) {
            throw new UnsupportedOperationException("Cannot redefine singleton Server");
        } else {
            api = worldBorderApi;
        }
    }

    @Deprecated
    public static WorldBorderApi getApi() {
        return api;
    }
}
