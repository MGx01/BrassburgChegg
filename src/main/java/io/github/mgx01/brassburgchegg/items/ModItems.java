package io.github.mgx01.brassburgchegg.items;


import io.github.mgx01.brassburgchegg.Blocks.ModBlocks;
import io.github.mgx01.brassburgchegg.BrassburgChegg;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BrassburgChegg.MOD_ID);
    public static final DeferredItem<Item> CHEGG_DECK = ITEMS.register(
            "cheggdeck",
            () -> new Item(new Item.Properties()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    public static final DeferredItem<BlockItem> DECK_BOX_ITEM = ITEMS.registerSimpleBlockItem(
            "deckbox",
            ModBlocks.DECK_BOX
    );
}


