package io.github.mgx01.brassburgchegg.api.gui.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class NavigationButtonWidget extends AbstractWidget {
    private final Consumer<Void> onPress;
    private final int backgroundColor;
    private final int textColor;
    private final int outlineColor;
    private final boolean dropShadow;

    public NavigationButtonWidget(
            int x, int y, int width, int height,
            int backgroundColor, int textColor, int outlineColor,
            boolean dropShadow,
            Component message, Consumer<Void> onPress) {
        super(x, y, width, height, message);
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.outlineColor = outlineColor;
        this.dropShadow = dropShadow;
        this.onPress = onPress;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        var font = Minecraft.getInstance().font;

        int currX = getX();
        int currY = getY();

        // Use 'this.width' and 'this.height' specifically
        graphics.fill(currX, currY, currX + this.width, currY + this.height, backgroundColor);
        graphics.renderOutline(currX, currY, this.width, this.height, outlineColor);

        // Center text based on actual width/height
        int textX = currX + (this.width / 2) - (font.width(getMessage()) / 2);
        int textY = currY + (this.height / 2) - 4;
        graphics.drawString(font, getMessage(), textX, textY, textColor, dropShadow);
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