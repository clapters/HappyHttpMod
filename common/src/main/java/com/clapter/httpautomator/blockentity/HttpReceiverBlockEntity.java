package com.clapter.httpautomator.blockentity;

import com.clapter.httpautomator.Constants;
import com.clapter.httpautomator.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HttpReceiverBlockEntity extends BlockEntity {

    private final Values values;

    public HttpReceiverBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.httpReceiverBlockEntity.get().get(), pos, state);
        this.values = new Values();
    }

    public void updateValues(Values values){
        //System.out.println("OLD VALUE: "+this.values.url);
        this.values.updateValues(values);
        setChanged();
    }

    public Values getValues(){
        return this.values;
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        CompoundTag compound = nbt.getCompound(Constants.MOD_ID);
        this.values.url = compound.getString("url");
        System.out.println("LOAD: " +this.values.url);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        CompoundTag compound = new CompoundTag();
        compound.putString("url", this.values.url);
        nbt.put(Constants.MOD_ID, compound);
    }

    public static class Values {

        public String url = "";

        public void writeValues(FriendlyByteBuf buf){
            buf.writeUtf(this.url);
        }

        public static Values readBuffer(FriendlyByteBuf buf){
            Values values = new Values();
            values.url = buf.readUtf();
            return values;
        }

        private void updateValues(Values values){
            this.url = values.url;
        }

    }

}
