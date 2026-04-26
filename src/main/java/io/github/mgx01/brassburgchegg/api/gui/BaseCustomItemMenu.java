package io.github.mgx01.brassburgchegg.api.gui;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public abstract class BaseCustomItemMenu extends BaseCustomMenu {
    protected final ItemStack triggerItem;

    protected BaseCustomItemMenu(@Nullable MenuType<?> type, int containerId, Player player, ItemStack triggerItem) {
        super(type, containerId, player);
        this.triggerItem = triggerItem;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.getMainHandItem() == this.triggerItem || player.getOffhandItem() == this.triggerItem;
    }
}