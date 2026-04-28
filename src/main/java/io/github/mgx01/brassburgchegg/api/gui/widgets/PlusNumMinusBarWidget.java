package io.github.mgx01.brassburgchegg.api.gui.widgets;

import io.github.mgx01.brassburgchegg.api.gui.colors.CheggColors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;

public class PlusNumMinusBarWidget extends AbstractWidget {
    private byte value;
    private final byte min;
    private final byte max;
    private final ValueValidator validator;
    private final OnValueChange onValueChange;

    //BASE CONSTRUCTOR
    public PlusNumMinusBarWidget(int x, int y, int width, int height, byte initialValue, byte min, byte max, OnValueChange onValueChange) {
        this(x, y, width, height, initialValue, min, max, onValueChange, (val) -> true);
    }

    //INJECT DEPENDENCY OF DeckRuleManager
    public PlusNumMinusBarWidget(int x, int y, int width, int height, byte initialValue, byte min, byte max, OnValueChange onValueChange, ValueValidator validator) {
        super(x, y, width, height, Component.empty());
        this.value = initialValue;
        this.min = min;
        this.max = max;
        this.onValueChange = onValueChange;
        this.validator = validator;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        var font = Minecraft.getInstance().font;
        int x = getX();
        int y = getY();

        // RECTANGLE + OUTLINE
        int xMaxRect = x + width;
        int yMaxRect = y + height;
        graphics.fill(x, y, xMaxRect, yMaxRect, CheggColors.BROWN);
        graphics.renderOutline(x, y, width, height, CheggColors.BROWN_DARK);

        // DRAW NUMBER
        String text = String.valueOf(value);
        int textWidth = font.width(text);
        int xNumber = x + (width / 2) - (textWidth / 2);
        int yNumber = y + (height / 2) - 4;
        graphics.drawString(font, text, xNumber, yNumber,  CheggColors.WHITE);

        // DRAW '+' '-'
        int xMinus = x + 2;
        int yMinus = y + (height / 2) - 4;
        int xPlus = x + width - 8;
        int yPlus = y + (height / 2) - 4;
        graphics.drawString(font, "-", xMinus, yMinus, CheggColors.RED_DARK);
        graphics.drawString(font, "+", xPlus, yPlus, CheggColors.LIME);
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
        int nextValue = value + delta;
        if (nextValue < min || nextValue > max) return;

        // CHANGE ALLOWED CHECK
        if (validator.canChange((byte) nextValue)) {
            this.value = (byte) nextValue;
            this.onValueChange.onChange(this.value);
            // INSERT SOUND ON SUCCESS
        } else {
            // INSERT SOUND ON FAILURE
        }
    }

    @Override
    protected void updateWidgetNarration(net.minecraft.client.gui.narration.NarrationElementOutput narration) {
        // BOOT REQUIREMENT
    }

    public interface OnValueChange {
        void onChange(byte newValue);
    }

    public interface ValueValidator {
        boolean canChange(byte requestedValue);
    }
}