package io.github.mgx01.brassburgchegg.api.gui;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public abstract class BaseCustomMenu extends AbstractContainerMenu {
    protected final Player player;

    protected BaseCustomMenu(@Nullable MenuType<?> type, int containerId, Player player) {
        super(type, containerId);
        this.player = player;
    }

    // DEFAULT FOR SLOTLESS GUI - DONT TOUCH
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    // FOR FLEXIBILITY REASONS - DEFINE YOURSELF IN MENU MODULES
    @Override
    public abstract boolean stillValid(Player player);
}