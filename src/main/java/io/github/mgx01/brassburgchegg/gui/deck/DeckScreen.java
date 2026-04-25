package io.github.mgx01.brassburgchegg.gui.deck;

import io.github.mgx01.brassburgchegg.menus.deck.DeckMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class DeckScreen extends AbstractContainerScreen<DeckMenu> {

    // THE CONSTRUCTOR
    public DeckScreen(DeckMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    // REQUIRED RENDERING METHODS
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        // Custom UI background
    }
}