package io.github.mgx01.brassburgchegg.api.gui.util;

import net.minecraft.client.gui.Font;

public enum TextAlignment {
    LEFT,
    CENTER,
    RIGHT;

    public int calculateX(Font font, String text, int startX, int areaWidth) {
        int textWidth = font.width(text);

        return switch (this) {
            case CENTER -> startX + (areaWidth / 2) - (textWidth / 2);
            case RIGHT  -> startX + areaWidth - textWidth;
            case LEFT   -> startX;
        };
    }
}