package io.github.mgx01.brassburgchegg.api.gui;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public abstract class BaseCustomBlockMenu extends BaseCustomMenu {
    protected final ContainerLevelAccess access;
    protected final Block blockType;

    protected BaseCustomBlockMenu(@Nullable MenuType<?> type, int containerId, Player player, ContainerLevelAccess access, Block blockType) {
        super(type, containerId, player);
        this.access = access;
        this.blockType = blockType; // Needed to verify the block hasn't been broken
    }

    @Override
    public boolean stillValid(Player player) {
        // Standard Minecraft distance and block-existence check
        return stillValid(this.access, player, this.blockType);
    }
}