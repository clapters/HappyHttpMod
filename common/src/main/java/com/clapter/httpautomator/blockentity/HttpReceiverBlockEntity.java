package com.clapter.httpautomator.blockentity;

import com.clapter.httpautomator.Constants;
import com.clapter.httpautomator.block.HttpReceiverBlock;
import com.clapter.httpautomator.enums.EnumPoweredType;
import com.clapter.httpautomator.enums.EnumTimerUnit;
import com.clapter.httpautomator.http.handlers.HttpReceiverBlockHandler;
import com.clapter.httpautomator.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HttpReceiverBlockEntity extends BlockEntity {


    private final Values values;

    public HttpReceiverBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.httpReceiverBlockEntity.get().get(), pos, state);
        this.values = new Values();
        //this.values.poweredType = EnumPoweredType.SWITCH;
        //this.values.timerUnit = EnumTimerUnit.TICKS;
    }

    public void onSignal(){
        BlockState state = this.level.getBlockState(this.getBlockPos());
        Block block = state.getBlock();
        if(block instanceof HttpReceiverBlock receiver){
            receiver.onSignal(state, this.level, this.getBlockPos());
        }
    }

    public void updateValues(Values values){
        this.values.updateValues(values);
        setChanged();
        if(!this.getLevel().isClientSide) {
            HttpReceiverBlockHandler.create(this, this.values.url);
        }
    }

    private void postLoad(){
        HttpReceiverBlockHandler.create(this, this.values.url);
    }

    public Values getValues(){
        return this.values;
    }


    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        CompoundTag compound = nbt.getCompound(Constants.MOD_ID);
        this.values.url = compound.getString("url");
        this.values.poweredType = EnumPoweredType.getById(compound.getInt("poweredType"));
        this.values.timerUnit = EnumTimerUnit.getById(compound.getInt("timerUnit"));
        this.values.timer = compound.getFloat("timer");
        this.values.redirectClientUrl = compound.getString("redirectClientUrl");
        this.postLoad();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        CompoundTag compound = new CompoundTag();
        compound.putString("url", this.values.url);
        compound.putInt("poweredType", this.values.poweredType.ordinal());
        compound.putInt("timerUnit", this.values.timerUnit.ordinal());
        compound.putFloat("timer", this.values.timer);
        compound.putString("redirectClientUrl", this.values.redirectClientUrl);
        nbt.put(Constants.MOD_ID, compound);
    }

    public static class Values {

        public String url = "";
        public EnumPoweredType poweredType = EnumPoweredType.SWITCH;
        public float timer;
        public EnumTimerUnit timerUnit = EnumTimerUnit.TICKS;
        public String redirectClientUrl = "";

        public void writeValues(FriendlyByteBuf buf){
            buf.writeUtf(this.url);
            buf.writeEnum(this.poweredType);
            buf.writeFloat(this.timer);
            buf.writeEnum(this.timerUnit);
            buf.writeUtf(this.redirectClientUrl);
        }

        public static Values readBuffer(FriendlyByteBuf buf){
            Values values = new Values();
            values.url = buf.readUtf();
            values.poweredType = buf.readEnum(EnumPoweredType.class);
            values.timer = buf.readFloat();
            values.timerUnit = buf.readEnum(EnumTimerUnit.class);
            values.redirectClientUrl = buf.readUtf();
            return values;
        }

        private void updateValues(Values values){
            this.url = values.url;
            this.redirectClientUrl = values.redirectClientUrl;
            this.timer = values.timer;
            this.timerUnit = values.timerUnit;
            this.poweredType = values.poweredType;
        }

    }

}
