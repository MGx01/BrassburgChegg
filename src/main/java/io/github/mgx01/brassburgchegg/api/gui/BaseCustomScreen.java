package io.github.mgx01.brassburgchegg.api.gui;

import io.github.mgx01.brassburgchegg.api.gui.util.TextureSettings;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public abstract class BaseCustomScreen<T extends BaseCustomMenu> extends AbstractContainerScreen<T> {
    protected final TextureSettings textureSettings;

    public BaseCustomScreen(T menu, Inventory inv, Component title, TextureSettings settings) {
        super(menu, inv, title);
        this.textureSettings = settings;
        this.imageWidth = settings.width();
        this.imageHeight = settings.height();
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(
                textureSettings.location(), this.leftPos, this.topPos,
                0, 0, this.imageWidth, this.imageHeight,
                textureSettings.atlasWidth(), textureSettings.atlasHeight()
        );
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        // Leave this empty to prevent "gui.brassburgchegg.deck_title" from appearing
    }
}