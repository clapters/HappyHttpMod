package com.clapter.httpautomator.platform.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class HttpServerConfig implements IHttpServerConfig {


    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec COMMON_CONFIG;

    private static ForgeConfigSpec.IntValue PORT;
    private static ForgeConfigSpec.ConfigValue LOCAL_ADRESS;

    static {
        COMMON_BUILDER.push("Http Server Settings");

        PORT = COMMON_BUILDER
                .comment("Http Server Port")
                .defineInRange("port", 8080, 0, 999999);

        LOCAL_ADRESS = COMMON_BUILDER
                .comment("Local adress of the machine. Leave empty to determine automatically (May be wrong if more than one Network Interface)")
                .define("local_adress", "192.168.0.1");

        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    public HttpServerConfig() {
    }

    @Override
    public int getPort() {
        return PORT.get();
    }

    @Override
    public String getLocalAdress() {
        return LOCAL_ADRESS.get().toString();
    }

}
