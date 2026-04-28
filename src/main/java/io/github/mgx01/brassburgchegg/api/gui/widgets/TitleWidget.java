package io.github.mgx01.brassburgchegg.api.gui.widgets;

import io.github.mgx01.brassburgchegg.api.gui.util.GuiUtil;
import io.github.mgx01.brassburgchegg.api.gui.util.WidgetPos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class TitleWidget extends AbstractWidget {

    private final int color;
    private final boolean dropShadow;

    public TitleWidget(WidgetPos pos, int parentX, int parentY, Component message, int color, boolean dropShadow) {
        super(pos.left(parentX), pos.top(parentY), pos.width(), pos.height(), message);
        this.color = color;
        this.dropShadow = dropShadow;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (!this.visible) return;

        var font = Minecraft.getInstance().font;
        int centerX = this.getX() + (this.width / 2);
        int centerY = this.getY() + (this.height / 2) - 4;

        graphics.drawString(
                font,
                this.getMessage(),
                centerX - (font.width(this.getMessage()) / 2),
                centerY,
                this.color,
                this.dropShadow
        );
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narration) {
        this.defaultButtonNarrationText(narration);
    }
}