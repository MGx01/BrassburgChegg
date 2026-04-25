package io.github.mgx01.brassburgchegg.menus; // Adjust if your package name is slightly different

import io.github.mgx01.brassburgchegg.menus.deck.DeckMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModMenus {

    // 1. Create the Registry for Menus
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, "brassburgchegg");

    // 2. Register the Deck Menu
    // We tell it to use the "Dummy" Client constructor: (containerId, playerInv) -> new DeckMenu(...)
    public static final DeferredHolder<MenuType<?>, MenuType<DeckMenu>> DECK_MENU =
            MENUS.register("deck_menu", () -> new MenuType<>(DeckMenu::new, FeatureFlags.DEFAULT_FLAGS));

}