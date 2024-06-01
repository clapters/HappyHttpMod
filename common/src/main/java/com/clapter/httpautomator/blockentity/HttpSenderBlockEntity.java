package com.clapter.httpautomator.blockentity;

import com.clapter.httpautomator.CommonClass;
import com.clapter.httpautomator.Constants;
import com.clapter.httpautomator.block.HttpReceiverBlock;
import com.clapter.httpautomator.http.handlers.HttpReceiverBlockHandler;
import com.clapter.httpautomator.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HttpSenderBlockEntity extends BlockEntity {


    private final HttpSenderBlockEntity.Values values;

    public HttpSenderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.httpSenderBlockEntity.get().get(), pos, state);
        this.values = new HttpSenderBlockEntity.Values();
    }

    public void onPowered(){
        if(!this.values.url.isEmpty()){
            CommonClass.HTTP_CLIENT.sendPost(this.values.url, "test=test2");
        }
    }

    public void onUnpowered(){

    }

    public void updateValues(HttpSenderBlockEntity.Values values){
        this.values.updateValues(values);
        setChanged();
    }

    public HttpSenderBlockEntity.Values getValues(){
        return this.values;
    }


    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        CompoundTag compound = nbt.getCompound(Constants.MOD_ID);
        this.values.url = compound.getString("url");
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

        public static HttpSenderBlockEntity.Values readBuffer(FriendlyByteBuf buf){
            HttpSenderBlockEntity.Values values = new HttpSenderBlockEntity.Values();
            values.url = buf.readUtf();
            return values;
        }

        private void updateValues(HttpSenderBlockEntity.Values values){
            this.url = values.url;
        }

    }

}