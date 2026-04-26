package io.github.mgx01.brassburgchegg.impl.registry; // Adjust if your package name is slightly different

import io.github.mgx01.brassburgchegg.api.gui.BaseCustomMenu;
import io.github.mgx01.brassburgchegg.impl.gui.deck.DeckMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModMenu {
    // REGISTER FOR MENUS
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, "brassburgchegg");

    // REGISTER FOR DECK MENU
    public static final DeferredHolder<MenuType<?>, MenuType<DeckMenu>> DECK_MENU =
            MENUS.register("deck_menu", () -> new MenuType<>(DeckMenu::new, FeatureFlags.DEFAULT_FLAGS));

}