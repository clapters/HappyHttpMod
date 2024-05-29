package com.clapter.httpautomator.platform.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public interface IPacketHandler {

    void sendPacketToPlayer(Object packet, ServerPlayer player);
    void sendPacketToServer(Object packet);
}
