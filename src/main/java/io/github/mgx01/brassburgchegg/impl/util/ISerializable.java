package io.github.mgx01.brassburgchegg.impl.util;

import net.minecraft.network.FriendlyByteBuf;

public interface ISerializable {
    void writeToBuffer(FriendlyByteBuf buffer);
    void readFromBuffer(FriendlyByteBuf buffer);
}