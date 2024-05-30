package com.clapter.httpautomator.platform.network;

import com.clapter.httpautomator.Constants;
import com.clapter.httpautomator.network.packet.BasePacket;
import com.clapter.httpautomator.network.packet.CSyncHttpReceiverValuesPacket;
import com.clapter.httpautomator.network.packet.SUpdateHttpReceiverValuesPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public class PacketHandler implements IPacketHandler {

    private static final SimpleChannel INSTANCE = ChannelBuilder.named(
            new ResourceLocation(Constants.MOD_ID, "packet_handler"))
            .serverAcceptedVersions((status, version) -> true)
            .clientAcceptedVersions((status, version) -> true)
            .networkProtocolVersion(1)
            .simpleChannel();

    public static void register(){
        //INSTANCE.messageBuilder(COpenScreenPacket.class, NetworkDirection.PLAY_TO_CLIENT)
        //        .encoder(COpenScreenPacket::encode)
        //        .decoder(COpenScreenPacket::new)
        //        .consumerMainThread(COpenScreenPacket::handle)
        //        .add();
        INSTANCE.messageBuilder(SUpdateHttpReceiverValuesPacket.class, NetworkDirection.PLAY_TO_SERVER)
                .encoder(SUpdateHttpReceiverValuesPacket::encode)
                .decoder(SUpdateHttpReceiverValuesPacket::new)
                .consumerMainThread(PacketHandler::handle)
                .add();

        INSTANCE.messageBuilder(CSyncHttpReceiverValuesPacket.class, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(CSyncHttpReceiverValuesPacket::encode)
                .decoder(CSyncHttpReceiverValuesPacket::new)
                .consumerMainThread(PacketHandler::handle)
                .add();


    }

    //THE HANDLER METHOD FOR HANDLING PACKETS AFTER THEIR DECODING PROCESS
    //HAS TO START HERE, SINCE THE CONTEXT OBJECT IS A FORGE CLASS
    private static void handle(BasePacket packet, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> packet.handle(new PacketContext(context)));
        context.setPacketHandled(true);
    }

    @Override
    public void sendPacketToPlayer(Object packet, ServerPlayer player) {
        INSTANCE.send(packet, PacketDistributor.PLAYER.with(player));
    }

    @Override
    public void sendPacketToServer(Object packet) {
        INSTANCE.send(packet, PacketDistributor.SERVER.noArg());
    }
}
