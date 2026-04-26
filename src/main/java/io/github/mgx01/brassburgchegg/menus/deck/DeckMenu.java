package io.github.mgx01.brassburgchegg.menus.deck;

import io.github.mgx01.brassburgchegg.menus.ModMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.HashMap;

public class DeckMenu extends AbstractContainerMenu {

    private final Map<String, Byte> deckCounts;
    private final ItemStack deckItem;

    // CLIENT CONSTRUCTOR
    public DeckMenu(int containerId, Inventory playerInv) {
        this(containerId, playerInv, new HashMap<>(), ItemStack.EMPTY);
    }

    // SERVER CONSTRUCTOR
    public DeckMenu(int containerId, Inventory playerInv, Map<String, Byte> deckCounts, ItemStack deckItem) {
        super(ModMenu.DECK_MENU.get(), containerId);
        this.deckCounts = deckCounts;
        this.deckItem = deckItem;
    }

    public Map<String, Byte> getDeckCounts() {
        return this.deckCounts;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.getMainHandItem() == this.deckItem || player.getOffhandItem() == this.deckItem;
    }
}