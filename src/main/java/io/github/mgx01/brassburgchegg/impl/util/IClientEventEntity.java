package io.github.mgx01.brassburgchegg.impl.util;

import io.github.mgx01.brassburgchegg.impl.network.packets.EntityEventPacket;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.PacketDistributor;

public interface IClientEventEntity {
    void handleClientEvent(byte eventId);

    default <T extends Entity & IClientEventEntity> void serverTriggerEvent(byte eventId) {
        PacketDistributor.sendToPlayersTrackingEntity((T) this, new EntityEventPacket<T>((T) this, eventId));
    }
}