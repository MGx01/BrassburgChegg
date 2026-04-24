package io.github.mgx01.brassburgchegg.items;

import io.github.mgx01.brassburgchegg.BrassburgChegg;
import io.github.mgx01.brassburgchegg.items.custom.CheggDeckCase;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;



public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BrassburgChegg.MOD_ID);
    public static final DeferredItem<Item> CHEGG_DECK = ITEMS.register(
            "cheggdeck",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHEGG_DECK_CASE = ITEMS.register(
            "cheggdeckcase",
            () -> new CheggDeckCase(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

    }

}


