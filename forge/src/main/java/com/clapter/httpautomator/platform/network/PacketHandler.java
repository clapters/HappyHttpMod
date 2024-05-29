package com.clapter.httpautomator.platform.network;

import com.clapter.httpautomator.Constants;
import com.clapter.httpautomator.network.COpenScreenPacket;
import com.clapter.httpautomator.platform.network.IPacketHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
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
