package com.clapter.httpautomator.platform.config;

import com.clapter.httpautomator.platform.config.IHttpServerConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class HttpServerConfig implements IHttpServerConfig {

    //public static final ForgeConfigSpec COMMON_SPEC;
    private final ForgeConfigSpec.ConfigValue<Integer> port;

    static {
        Pair<HttpServerConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(HttpServerConfig::new);

    }

    public HttpServerConfig(ForgeConfigSpec.Builder builder) {
        builder.push("Http Server Settings");

        port = builder
                .comment("Http Server Port")
                .define("port", 8080);

        builder.pop();
    }

    @Override
    public int getPort() {
        return COMMON_SPEC.getInt("port");
    }

}
