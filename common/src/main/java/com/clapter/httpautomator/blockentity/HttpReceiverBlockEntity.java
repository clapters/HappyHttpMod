package com.clapter.httpautomator.blockentity;

import com.clapter.httpautomator.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HttpReceiverBlockEntity extends BlockEntity {

    private String url;


    public HttpReceiverBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.httpReceiverBlockEntity.get().get(), pos, state);
    }



}
