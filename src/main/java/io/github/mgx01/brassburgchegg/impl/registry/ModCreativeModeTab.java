package io.github.mgx01.brassburgchegg.impl.registry;
import io.github.mgx01.brassburgchegg.main.BrassburgChegg;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BrassburgChegg.MOD_ID);

    public static final Supplier<CreativeModeTab> CHEGG = CREATIVE_MODE_TAB.register("chegg_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItem.CHEGG_DECK.get()))
                    .title(Component.translatable("creativetab.brassburgchegg.chegg_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItem.CHEGG_DECK);
                        output.accept(ModItem.CHEGG_DECK_CASE);
                        output.accept(ModBlock.DECK_BOX);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}