package io.github.mgx01.brassburgchegg.api.gui.widgets;

import io.github.mgx01.brassburgchegg.api.gui.colors.CheggColors;
import io.github.mgx01.brassburgchegg.api.gui.managers.WidgetSelectionManager;
import io.github.mgx01.brassburgchegg.api.gui.settings.color.WidgetColors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;


public class GatedNavigationButtonWidget extends AbstractWidget {
    private final WidgetSelectionManager<String> selectionManager;
    private final Consumer<String> onPress;
    private final WidgetColors widgetColors;


    public GatedNavigationButtonWidget(
            int x, int y, int width, int height,
            WidgetColors widgetColors,
            Component message,
            WidgetSelectionManager<String> selectionManager,
            Consumer<String> onPress) {
        super(x, y, width, height, message);
        this.widgetColors = widgetColors;
        this.selectionManager = selectionManager;
        this.onPress = onPress;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        boolean active = hasSelection();

        drawBackground(graphics, active);
        drawText(graphics, active);
        drawHoverOverlay(graphics, active);
        handleTooltip(active);
    }


    private boolean hasSelection() {
        String selected = selectionManager.getSelected();
        return selected != null && !selected.isEmpty();
    }

    private void drawBackground(GuiGraphics graphics, boolean active) {
        int backgroundColor = active ? this.widgetColors.rectangleColor() : this.widgetColors.disabledRectangleColor();
        graphics.fill(getX(), getY(), getX() + width, getY() + height, backgroundColor);
        graphics.renderOutline(getX(), getY(), width, height, this.widgetColors.outlineColor());
    }

    private void drawText(GuiGraphics graphics, boolean active) {
        Font font = Minecraft.getInstance().font;
        int textColor = active ? this.widgetColors.textColor() : this.widgetColors.disabledTextColor();

        int textX = getX() + (width / 2) - (font.width(getMessage()) / 2);
        int textY = getY() + (height / 2) - 4;

        graphics.drawString(font, getMessage(), textX, textY, textColor, false);
    }

    private void drawHoverOverlay(GuiGraphics graphics, boolean active) {
        if (this.isHoveredOrFocused() && active) {
            graphics.fill(getX(), getY(), getX() + width, getY() + height, CheggColors.WHITE_20);
        }
    }

    private void handleTooltip(boolean active) {
        if (this.isHoveredOrFocused() && !active) {
            setTooltip(Tooltip.create(Component.literal("Select a mob first!")));
        } else {
            setTooltip(null);
        }
    }


    @Override
    public void onClick(double mouseX, double mouseY) {
        String selected = selectionManager.getSelected();
        if (selected != null && !selected.isEmpty()) {
            this.onPress.accept(selected);
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narration) {
        narration.add(NarratedElementType.TITLE, this.createNarrationMessage());
    }
}