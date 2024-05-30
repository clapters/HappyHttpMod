package com.clapter.httpautomator.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class HttpServerConfig implements IHttpServerConfig {

    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        COMMON_SPEC = builder.build();
    }

    public HttpServerConfig() {
        
    }

    @Override
    public int getPort() {
        return COMMON_SPEC.getInt("port");
    }

    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Integer> port;

        Common(ForgeConfigSpec.Builder builder) {
            builder.push("Http Server Settings");

            port = builder
                    .comment("Http Server Port")
                    .define("port", 8080);

            builder.pop();
        }
    }

}
