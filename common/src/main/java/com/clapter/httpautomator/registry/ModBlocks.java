package com.clapter.httpautomator.registry;

import com.clapter.httpautomator.Constants;
import com.clapter.httpautomator.block.HTTPReceiverBlock;
import com.clapter.httpautomator.block.TestBlock;
import com.clapter.httpautomator.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {

    private static Block testBlock;
    private static Block receiver;

    public static void registerBlocks(){
        Services.BLOCK_REGISTRY.registerBlock(id("test"), () -> testBlock = new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
        Services.BLOCK_REGISTRY.registerBlock(id("receiver"), () -> receiver = new HTTPReceiverBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.FIRE)
                .requiresCorrectToolForDrops()
                .strength(5.0F, 6.0F)
                .sound(SoundType.METAL)
        ));
        //Registering items for the coresponding blocks. If more blocks are planned,
        //then an iteration algorithm could be implemented, to avoid manual adding of items.
        Services.ITEM_REGISTRY.registerItem(id("test"), () -> new BlockItem(testBlock, new Item.Properties()));
        Services.ITEM_REGISTRY.registerItem(id("receiver"), () -> new BlockItem(receiver, new Item.Properties()));
    }

    private static ResourceLocation id(String name) {
        return new ResourceLocation(Constants.MOD_ID, name);
    }

}
