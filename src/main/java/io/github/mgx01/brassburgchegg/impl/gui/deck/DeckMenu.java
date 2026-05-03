package io.github.mgx01.brassburgchegg.impl.gui.deck;

import io.github.mgx01.brassburgchegg.api.gui.BaseCustomItemMenu;
import io.github.mgx01.brassburgchegg.api.gui.managers.DeckCardManager;
import io.github.mgx01.brassburgchegg.impl.data.rules.DeckRuleManager;
import io.github.mgx01.brassburgchegg.impl.registry.ModMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.Map;
import java.util.HashMap;

public class DeckMenu extends BaseCustomItemMenu {

    private final DeckRuleManager ruleManager;
    private final DeckCardManager deckManager;
    private final DeckRuleManager deckRuleManager;

    public DeckMenu(int containerId, Inventory playerInv) {
        this(containerId, playerInv, new HashMap<>(), ItemStack.EMPTY);
    }

    // LOGICAL CONSTRUCTOR (Server Side / Real Initialization)
    public DeckMenu(int containerId, Inventory playerInv, Map<String, Byte> initialCounts, ItemStack deckItem) {
        super(ModMenu.DECK_MENU.get(), containerId, playerInv.player, deckItem);
        this.ruleManager = new DeckRuleManager();
        this.deckManager = new DeckCardManager(this.ruleManager, initialCounts);
        this.deckRuleManager = new DeckRuleManager();
    }

    // --- GETTERS FOR THE SCREENS ---
    public DeckRuleManager getRuleManager() {
        return ruleManager;
    }

    public DeckCardManager getDeckManager() {
        return deckManager;
    }

    public DeckRuleManager gedeckRuleManager() {
        return deckRuleManager;
    }

}