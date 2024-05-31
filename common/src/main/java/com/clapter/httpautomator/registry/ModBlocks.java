package com.clapter.httpautomator.registry;

import com.clapter.httpautomator.Constants;
import com.clapter.httpautomator.block.HttpReceiverBlock;
import com.clapter.httpautomator.block.HttpSenderBlock;
import com.clapter.httpautomator.platform.DeferredObject;
import com.clapter.httpautomator.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {

    public static Block httpReceiverBlock;
    public static Block httpSenderBlock;

    public static void registerBlocks(){
        Services.BLOCK_REGISTRY.registerBlock(id("receiver"), () -> httpReceiverBlock = new HttpReceiverBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.FIRE)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.METAL)
        ));
        Services.BLOCK_REGISTRY.registerBlock(id("sender"), () -> httpSenderBlock = new HttpSenderBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.FIRE)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.METAL)
        ));

        Services.BLOCK_REGISTRY.finishRegistry();

    }

    private static ResourceLocation id(String name) {
        return new ResourceLocation(Constants.MOD_ID, name);
    }

}
