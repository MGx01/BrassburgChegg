package io.github.mgx01.brassburgchegg.items;


import io.github.mgx01.brassburgchegg.BrassburgChegg;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items Items = DeferredRegister.createItems(BrassburgChegg.MOD_ID);
    public static final DeferredItem<Item> CHEGGDECK = Items.register("cheggdeck", () -> new Item(new Item.Properties()));
    public static void register(IEventBus eventBus) {
        Items.register(eventBus);
    }
}


