package io.github.mgx01.brassburgchegg.api.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public abstract class BaseCustomScreen<T extends BaseCustomMenu> extends AbstractContainerScreen<T> {
    private final ResourceLocation backgroundTexture;
    private final int textureFileWidth;
    private final int textureFileHeight;
    protected static final int COLOR_DARK_GRAY = 0x404040;
    protected static final int COLOR_WHITE = 0xFFFFFF;
    protected static final int COLOR_RED = 0xFF5555;
    protected static final int COLOR_BLUE = 0x5555FF;
    protected boolean renderTitle;
    protected Component titleText;
    protected int titleYOffset;
    protected int titleColor;


    public BaseCustomScreen(T menu, Inventory inv, Component title, ResourceLocation tex, int visibleImgWidth, int visibleImgHeight, int texFileWidth, int texFileHeight) {
        super(menu, inv, title);
        this.backgroundTexture = tex;
        this.imageWidth = visibleImgWidth;
        this.imageHeight = visibleImgHeight;
        this.textureFileWidth = texFileWidth;
        this.textureFileHeight = texFileHeight;
    }

    // ADD FONT CUSTOMIZATION LATER
    protected void setTitleConfiguration(boolean renderTitle, String title, int titleColor, int titleYOffset){
        this.renderTitle = renderTitle;
        this.titleText = Component.literal(title);
        this.titleColor = titleColor;
        this.titleYOffset = titleYOffset;
    };

    protected void drawCenteredText(GuiGraphics graphics, net.minecraft.client.gui.Font fontToUse, Component text, int yOffset, int color) {
        int textWidth = fontToUse.width(text);
        int xPos = this.leftPos + (this.imageWidth / 2) - (textWidth / 2);
        int yPos = this.topPos + yOffset;

        graphics.drawString(fontToUse, text, xPos, yPos, color, false);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        if (this.renderTitle && this.titleText != null) {
            this.drawCenteredText(graphics, this.font, this.titleText, this.titleYOffset, this.titleColor);
        }
    }

    @Override
    public void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(this.backgroundTexture, this.leftPos, this.topPos, 0, 0,
                this.imageWidth, this.imageHeight,
                this.textureFileWidth, this.textureFileHeight);
    }
}

