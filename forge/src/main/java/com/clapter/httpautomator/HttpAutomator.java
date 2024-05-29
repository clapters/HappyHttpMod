package com.clapter.httpautomator;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class HttpAutomator {

    public HttpAutomator() {
        CommonClass.init();
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarted);
    }

    private void onServerStarting(ServerStartingEvent e){
        CommonClass.onServerStarting();
    }

    private void onServerStarted(ServerStartedEvent e){
        CommonClass.onServerStarted();
    }

}