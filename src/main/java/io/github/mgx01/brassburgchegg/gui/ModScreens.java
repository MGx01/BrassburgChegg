package io.github.mgx01.brassburgchegg.gui; // Matches your directory structure

import io.github.mgx01.brassburgchegg.menus.ModMenus;
import io.github.mgx01.brassburgchegg.gui.deck.DeckScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent; // The New 1.21 Event!

//@EventBusSubscriber(modid = "brassburgchegg", value = Dist.CLIENT)
public class ModScreens {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.DECK_MENU.get(), DeckScreen::new);
    }
}