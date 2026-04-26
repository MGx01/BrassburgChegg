package io.github.mgx01.brassburgchegg.api.gui.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class PlusNumMinusBarWidget extends AbstractWidget {
    private byte value;
    private final byte min;
    private final byte max;
    private final OnValueChange onValueChange;

    public PlusNumMinusBarWidget(int x, int y, int width, int height, byte initialValue, byte min, byte max, OnValueChange onValueChange) {
        super(x, y, width, height, Component.empty());
        this.value = initialValue;
        this.min = min;
        this.max = max;
        this.onValueChange = onValueChange;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        var font = Minecraft.getInstance().font;

        // 1. DRAW RECTANGLE
        graphics.fill(getX(), getY(), getX() + width, getY() + height, 0xFF000000);

        // 2. DRAW OUTLINE (Fixed parameters)
        graphics.renderOutline(getX(), getY(), width, height, 0xFFFFFFFF);

        // 3. DRAW NUMBER
        String text = String.valueOf(value);
        int textWidth = font.width(text);
        graphics.drawString(font, text, getX() + (width / 2) - (textWidth / 2), getY() + (height / 2) - 4, 0xFFFFFF);

        // 4. DRAW '+' '-'
        graphics.drawString(font, "-", getX() + 2, getY() + (height / 2) - 4, 0xFF0000);
        graphics.drawString(font, "+", getX() + width - 8, getY() + (height / 2) - 4, 0x00FF00);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        // Simple logic: click left side to decrease, right side to increase
        if (mouseX < getX() + (width / 2)) {
            changeValue(-1);
        } else {
            changeValue(1);
        }
    }

    private void changeValue(int delta) {
        int newValue = value + delta;
        if (newValue >= min && newValue <= max) {
            this.value = (byte) newValue;
            this.onValueChange.onChange(this.value);
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narration) {}

    public interface OnValueChange {
        void onChange(byte newValue);
    }
}