package io.github.mgx01.brassburgchegg.impl.registry; // Matches your directory structure

import io.github.mgx01.brassburgchegg.impl.gui.deck.DeckScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent; // The New 1.21 Event!

@EventBusSubscriber(modid = "brassburgchegg", value = Dist.CLIENT)
public class ModScreen {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenu.DECK_MENU.get(), DeckScreen::new);
    }
}