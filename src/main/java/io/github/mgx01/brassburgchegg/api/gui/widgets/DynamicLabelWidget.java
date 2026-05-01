package io.github.mgx01.brassburgchegg.api.gui.widgets;

import io.github.mgx01.brassburgchegg.api.gui.util.TextAlignment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

public class DynamicLabelWidget extends AbstractWidget {
    private final Supplier<String> textSupplier;
    private final Supplier<Integer> colorSupplier;
    private final TextAlignment alignment;

    public DynamicLabelWidget(int x, int y, int width, int height,
                              Supplier<String> textSupplier,
                              Supplier<Integer> colorSupplier,
                              TextAlignment alignment) {
        super(x, y, width, height, Component.empty());
        this.textSupplier = textSupplier;
        this.colorSupplier = colorSupplier;
        this.alignment = alignment;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        String text = textSupplier.get();
        if (text == null || text.isEmpty()) return;

        drawLabel(graphics, text);
    }

    private void drawLabel(GuiGraphics graphics, String text) {
        Font font = Minecraft.getInstance().font;
        int renderX = alignment.calculateX(font, text, getX(), width);
        int color = colorSupplier.get();

        graphics.drawString(font, text, renderX, getY(), color, false);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narration) {
        narration.add(NarratedElementType.TITLE, textSupplier.get());
    }
}