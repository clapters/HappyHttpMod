package com.clapter.httpautomator.platform;

import com.clapter.httpautomator.Constants;
import com.clapter.httpautomator.platform.services.IBlockRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BlockRegistry implements IBlockRegistry {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

    @Override
    public void registerBlock(ResourceLocation identifier, Supplier<Block> block) {
        BLOCKS.register(identifier.getPath(), block);
    }

    public static void finishRegistry(){
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
