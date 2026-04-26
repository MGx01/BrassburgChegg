package io.github.mgx01.brassburgchegg.impl.gui.deck;

import io.github.mgx01.brassburgchegg.api.gui.BaseCustomScreen;
import io.github.mgx01.brassburgchegg.main.BrassburgChegg;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DeckScreen extends BaseCustomScreen<DeckMenu> {

    private static final ResourceLocation DECK_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(BrassburgChegg.MOD_ID, "textures/gui/deck_background336x256.png");

    private static final int BACKGROUND_TEXTURE_WIDTH = 336;
    private static final int BACKGROUND_TEXTURE_HEIGHT = 256;
    private static final int BACKGROUND_ATLAS_WIDTH = 336;
    private static final int BACKGROUND_ATLAS_HEIGHT = 256;

    public DeckScreen(DeckMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, DECK_TEXTURE, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT, BACKGROUND_ATLAS_WIDTH, BACKGROUND_ATLAS_HEIGHT);
        this.setTitleConfiguration(true, "Chegg Deck Menu", COLOR_WHITE, 10);
    }

    @Override
    protected void init() {
        super.init();
        // WIDGETS
    }
}