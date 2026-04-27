package io.github.mgx01.brassburgchegg.api.gui.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;

public class PlusNumMinusBarWidget extends AbstractWidget {
    private static final int COLOR_BEIGE = 0xFFE9BD80;
    private static final int COLOR_BEIGE_DARK = 0xFFCF9B61;
    private static final int COLOR_WHITE = 0xFFFFFFFF;
    private static final int COLOR_LIME = 0xFFA8E61D;
    private static final int COLOR_RED_DARK = 0xFFA60A12;
    private static final int COLOR_BROWN = 0xFF773F2B;
    private static final int COLOR_BROWN_DARK = 0xFF5C2A22;

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
        int x = getX();
        int y = getY();

        // RECTANGLE + OUTLINE
        int xMaxRect = x + width;
        int yMaxRect = y + height;
        graphics.fill(x, y, xMaxRect, yMaxRect, COLOR_BROWN);
        graphics.renderOutline(x, y, width, height, COLOR_BROWN_DARK);

        // DRAW NUMBER
        String text = String.valueOf(value);
        int textWidth = font.width(text);
        int xNumber = x + (width / 2) - (textWidth / 2);
        int yNumber = y + (height / 2) - 4;
        graphics.drawString(font, text, xNumber, yNumber,  COLOR_WHITE);

        // DRAW '+' '-'
        int xMinus = x + 2;
        int yMinus = y + (height / 2) - 4;
        int xPlus = x + width - 8;
        int yPlus = y + (height / 2) - 4;
        graphics.drawString(font, "-", xMinus, yMinus, COLOR_RED_DARK);
        graphics.drawString(font, "+", xPlus, yPlus, COLOR_LIME);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        double relativePositionX = mouseX - getX();
        double relativePositionY = mouseY - getY();
        int hitZone = 16;
        boolean isInsideY = relativePositionY >= 0 && relativePositionY <= height;

        if (isInsideY) {
            if (relativePositionX <= hitZone) {
                changeValue(-1);
            }
            else if (relativePositionX >= (width - hitZone)) {
                changeValue(1);
            }
        }
    }
    public void playDownSound(SoundManager handler) {
        //STOPS MINECRAFT AUTO SOUND
    }
    private void changeValue(int delta) {
        int newValue = value + delta;
        if (newValue >= min && newValue <= max) {
            this.value = (byte) newValue;
            this.onValueChange.onChange(this.value);

            Minecraft.getInstance().getSoundManager().play(
                    SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F)
            );
        }
    }
    @Override
    protected void updateWidgetNarration(NarrationElementOutput narration) {
        //KEEP THIS OR MINECRAFT WONT BOOT
    }
    public interface OnValueChange {
        void onChange(byte newValue);
    }
}