package io.github.mgx01.brassburgchegg.impl.gui.deck;

import io.github.mgx01.brassburgchegg.api.gui.BaseCustomItemMenu;
import io.github.mgx01.brassburgchegg.impl.registry.ModMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import java.util.Map;
import java.util.HashMap;

public class DeckMenu extends BaseCustomItemMenu {
    private final Map<String, Byte> deckCounts;

    // REGISTRY CONSTRUCTOR
    public DeckMenu(int containerId, Inventory playerInv) {
        this(containerId, playerInv, new HashMap<>(), ItemStack.EMPTY);
    }

    // LOGICAL CONSTRUCTOR
    public DeckMenu(int containerId, Inventory playerInv, Map<String, Byte> deckCounts, ItemStack deckItem) {
        super(ModMenu.DECK_MENU.get(), containerId, playerInv.player, deckItem);
        this.deckCounts = deckCounts;
    }

}