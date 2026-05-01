package io.github.mgx01.brassburgchegg.api.gui.widgets;

import io.github.mgx01.brassburgchegg.api.gui.managers.DeckCardManager;
import io.github.mgx01.brassburgchegg.api.gui.settings.color.WidgetColors;
import io.github.mgx01.brassburgchegg.api.gui.settings.positional.WidgetPos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;

import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;

//BEST WIDGET DIMENSIONS ARE 3(x) TO 1(y)

public class PlusNumMinusBarWidget extends AbstractWidget {
    private final String entityName;
    private final DeckCardManager deckCardManager;
    private final WidgetColors colors;
    //INJECT DEPENDENCY OF DeckRuleManager
    public PlusNumMinusBarWidget(int x, int y, int width, int height,
                                 WidgetColors WidgetColors,
                                 DeckCardManager deckCardManager, String entityName) {
        super(x, y, width, height, Component.empty());
        this.entityName = entityName;
        this.deckCardManager = deckCardManager;
        this.colors = WidgetColors;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        drawBackground(graphics);
        drawCounterValue(graphics);
        drawControls(graphics);
    }

    private void drawBackground(GuiGraphics graphics) {
        graphics.fill(getX(), getY(), getX() + width, getY() + height, colors.rectangleColor());
        graphics.renderOutline(getX(), getY(), width, height, colors.outlineColor());
    }

    private void drawCounterValue(GuiGraphics graphics) {
        Font font = Minecraft.getInstance().font;
        String text = String.valueOf(deckCardManager.getCount(entityName));

        int xNumber = getX() + (width / 2) - (font.width(text) / 2);
        int yNumber = getY() + (height / 2) - 4;

        graphics.drawString(font, text, xNumber, yNumber, colors.textColor(), false);
    }

    private void drawControls(GuiGraphics graphics) {
        Font font = Minecraft.getInstance().font;
        int yAxis = getY() + (height / 2) - 4;

        graphics.drawString(font, "-", getX() + 4, yAxis, colors.minusColor(), false);
        int plusWidth = font.width("+");
        graphics.drawString(font, "+", getX() + width - plusWidth - 4, yAxis, colors.plusColor(), false);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        double relativeX = mouseX - getX();
        int hitZone = this.width / 3;

        if (relativeX <= hitZone) {
            changeValue(-1);
        } else if (relativeX >= (this.width - hitZone)) {
            changeValue(1);
        }
    }

    public void playDownSound(SoundManager handler) {
        //STOPS MINECRAFT AUTO SOUND
    }

    private void changeValue(int delta) {
        int nextValue = (deckCardManager.getCount(entityName) + delta);
        if (deckCardManager.isUpdateAllowed(entityName, nextValue)) {
            deckCardManager.updateCount(entityName, (byte)nextValue);
            // INSERT SOUND ON SUCCESS
        }
    }

    @Override
    protected void updateWidgetNarration(net.minecraft.client.gui.narration.NarrationElementOutput narration) {
        // BOOT REQUIREMENT
    }


}