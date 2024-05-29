package com.clapter.httpautomator.platform.services;

import com.clapter.httpautomator.platform.DeferredObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.function.Supplier;

public interface IBlockRegistry {

    void registerBlock(ResourceLocation identifier, Supplier<Block> block);
    void finishRegistry();
}
