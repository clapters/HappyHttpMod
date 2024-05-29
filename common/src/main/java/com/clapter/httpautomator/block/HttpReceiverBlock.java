package com.clapter.httpautomator.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

//MOST OVERRIDEN METHODS ARE DEPRECATED.
//PEOPLE AT FORGE FORUM SAY IT IS NOT A PROBLEM AND THESE METHODS CAN STILL BE USED
//IN OTHER INSTANCES OF BLOCKS SAFELY
public class HttpReceiverBlock extends PoweredBlock implements EntityBlock {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public HttpReceiverBlock(Properties $$0) {

        super($$0);
        this.registerDefaultState(
                this.stateDefinition.any().setValue(POWERED, Boolean.valueOf(false))
        );
    }

    @Override
    public void onPlace(BlockState $$0, Level $$1, BlockPos $$2, BlockState $$3, boolean $$4) {
        super.onPlace($$0, $$1, $$2, $$3, $$4);
        $$0.setValue(POWERED, false);
    }

    @Override
    public InteractionResult use(BlockState $$0, Level $$1, BlockPos $$2, Player $$3, InteractionHand $$4, BlockHitResult $$5) {
        if (!$$1.isClientSide) {
            BlockState $$7 = this.switchPowered($$0, $$1, $$2);
            float $$8 = $$7.getValue(POWERED) ? 0.6F : 0.5F;
            $$1.playSound(null, $$2, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, $$8);
            $$1.gameEvent($$3, $$7.getValue(POWERED) ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, $$2);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    public BlockState switchPowered(BlockState $$0, Level $$1, BlockPos $$2) {
        $$0 = $$0.cycle(POWERED);
        $$1.setBlock($$2, $$0, 3);
        this.updateNeighbours($$0, $$1, $$2);
        return $$0;
    }

    @Override
    public void onRemove(BlockState $$0, Level $$1, BlockPos $$2, BlockState $$3, boolean $$4) {
        if (!$$4 && !$$0.is($$3.getBlock())) {
            if ($$0.getValue(POWERED)) {
                this.updateNeighbours($$0, $$1, $$2);
            }

            super.onRemove($$0, $$1, $$2, $$3, $$4);
        }
    }

    private void updateNeighbours(BlockState $$0, Level $$1, BlockPos $$2) {
        //$$1.updateNeighborsAt($$2, this);
        //$$1.updateNeighborsAt($$2.relative(getConnectedDirection($$0).getOpposite()), this);
    }

    @Override
    public int getSignal(BlockState $$0, BlockGetter $$1, BlockPos $$2, Direction $$3) {
        return $$0.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> $$0) {
        $$0.add(POWERED);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos var1, BlockState var2) {
        return null;
    }
}
