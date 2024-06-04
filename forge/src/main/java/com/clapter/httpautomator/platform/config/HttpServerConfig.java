package com.clapter.httpautomator.platform.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class HttpServerConfig implements IHttpServerConfig {


    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec COMMON_CONFIG;

    private static ForgeConfigSpec.IntValue PORT;

    static {
        COMMON_BUILDER.push("Http Server Settings");

        PORT = COMMON_BUILDER
                .comment("Http Server Port")
                .defineInRange("port", 8080, 0, 999999);

        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    public HttpServerConfig() {
    }

    @Override
    public int getPort() {
        return PORT.get();
    }

}
