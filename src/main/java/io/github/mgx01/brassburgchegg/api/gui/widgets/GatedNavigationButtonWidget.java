package io.github.mgx01.brassburgchegg.api.gui.widgets;

import io.github.mgx01.brassburgchegg.api.gui.colors.CheggColors;
import io.github.mgx01.brassburgchegg.api.gui.managers.WidgetSelectionManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;


public class GatedNavigationButtonWidget extends AbstractWidget {
    private final WidgetSelectionManager<String> selectionManager;
    private final Consumer<String> onPress;

    private final int clickableBoxColor;
    private final int unclickableBoxColor;
    private final int clickableTextColor;
    private final int unclickableTextColor;

    public GatedNavigationButtonWidget(
            int x, int y, int width, int height,
            int clickableBoxColor, int unclickableBoxColor, int clickableTextColor, int unclickableTextColor,
            Component message,
            WidgetSelectionManager<String> selectionManager,
            Consumer<String> onPress) {
        super(x, y, width, height, message);
        this.clickableBoxColor = clickableBoxColor;
        this.unclickableBoxColor = unclickableBoxColor;
        this.clickableTextColor = clickableTextColor;
        this.unclickableTextColor = unclickableTextColor;
        this.selectionManager = selectionManager;
        this.onPress = onPress;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        var font = Minecraft.getInstance().font;
        
        String selected = selectionManager.getSelected();
        boolean hasSelection = selected != null && !selected.isEmpty();
        
        int backgroundColor = hasSelection ? clickableBoxColor : unclickableBoxColor;
        int textColor = hasSelection ? clickableTextColor : unclickableTextColor;

        graphics.fill(getX(), getY(), getX() + width, getY() + height, backgroundColor);
        graphics.renderOutline(getX(), getY(), width, height, CheggColors.BROWN_DARK);

        int textX = getX() + (width / 2) - (font.width(getMessage()) / 2);
        int textY = getY() + (height / 2) - 4;
        graphics.drawString(font, getMessage(), textX, textY, textColor, false);

        if (this.isHoveredOrFocused() && !hasSelection) {
            setTooltip(net.minecraft.client.gui.components.Tooltip.create(Component.literal("Select a mob first!")));
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