package io.github.mgx01.brassburgchegg.gui;
import io.github.mgx01.brassburgchegg.menus.ModMenus;
import io.github.mgx01.brassburgchegg.gui.deck.DeckScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = "brassburgchegg", value = Dist.CLIENT)
public class ModScreens {
    @SubscribeEvent
    public static void registerScreens(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenus.DECK_MENU.get(), DeckScreen::constructScreen);
            // MenuScreens.register(ModMenus.SCEPTER_MENU.get(), ScepterScreen::constructScreen);
        });
    }
}