package io.github.mgx01.brassburgchegg.api.gui.widgets;

import io.github.mgx01.brassburgchegg.api.gui.util.TextAlignment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
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
        var font = Minecraft.getInstance().font;
        String text = textSupplier.get();
        int color = colorSupplier.get();

        int textWidth = font.width(text);
        int renderX = getX();

        switch (alignment) {
            case CENTER -> renderX = getX() + (width / 2) - (textWidth / 2);
            case RIGHT  -> renderX = getX() + width - textWidth;
            case LEFT   -> renderX = getX();
        }

        graphics.drawString(font, text, renderX, getY(), color, false);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narration) {
        narration.add(net.minecraft.client.gui.narration.NarratedElementType.TITLE, textSupplier.get());
    }
}