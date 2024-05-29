package com.clapter.httpautomator;

import com.clapter.httpautomator.platform.BlockRegistry;
import com.clapter.httpautomator.platform.ItemRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod(Constants.MOD_ID)
public class HttpAutomator {

    public HttpAutomator() {
        //MAIN MODULE INIT
        CommonClass.init();
        //ADDING EVENT HANDLERS FOR EVENT BUSES.
        //Probably Forge specific, so is separated from the main module
        BlockRegistry.finishRegistry();
        ItemRegistry.finishRegistry();
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
    }

    private void onServerStarting(ServerStartingEvent e){
        CommonClass.onServerStarting();
    }

}