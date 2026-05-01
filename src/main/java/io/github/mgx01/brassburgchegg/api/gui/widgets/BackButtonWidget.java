package io.github.mgx01.brassburgchegg.api.gui.widgets;

import io.github.mgx01.brassburgchegg.api.gui.settings.color.WidgetColors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class BackButtonWidget extends AbstractWidget {
    private final Consumer<Void> onPress;
    private final WidgetColors widgetColors;
    private static final boolean DROP_SHADOW = true;


    public BackButtonWidget(
            int x, int y, int width, int height,
            WidgetColors widgetColors,
            Component message,
            Consumer<Void> onPress) {
        super(x, y, width, height, message);
        this.widgetColors = widgetColors;
        this.onPress = onPress;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        drawBackground(graphics);
        drawButtonText(graphics);
    }

    private void drawBackground(GuiGraphics graphics) {
        graphics.fill(getX(), getY(), getX() + width, getY() + height, widgetColors.rectangleColor());
        graphics.renderOutline(getX(), getY(), width, height, widgetColors.outlineColor());
    }

    private void drawButtonText(GuiGraphics graphics) {
        Font font = Minecraft.getInstance().font;
        int textX = getX() + (width / 2) - (font.width(getMessage()) / 2);
        int textY = getY() + (height / 2) - 4;

        graphics.drawString(font, getMessage(), textX, textY, widgetColors.textColor(), DROP_SHADOW);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        this.onPress.accept(null);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narration) {
        this.defaultButtonNarrationText(narration);
    }
}