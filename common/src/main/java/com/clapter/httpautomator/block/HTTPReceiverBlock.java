package com.clapter.httpautomator.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Redstone;

public class HTTPReceiverBlock extends PoweredBlock {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public HTTPReceiverBlock(Properties $$0) {
        super($$0);
    }

    @Override
    public boolean isSignalSource(BlockState $$0) {
        System.out.println("HERE");
        return false;
    }

    @Override
    public int getSignal(BlockState $$0, BlockGetter $$1, BlockPos $$2, Direction $$3) {
        System.out.println("HERE");
        return 1;
    }
}
