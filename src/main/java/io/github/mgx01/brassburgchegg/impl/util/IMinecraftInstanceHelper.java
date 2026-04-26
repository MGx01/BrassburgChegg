package io.github.mgx01.brassburgchegg.impl.util;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

public interface IMinecraftInstanceHelper {
    @Nullable
    Player player();
}