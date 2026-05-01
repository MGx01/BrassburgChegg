package io.github.mgx01.brassburgchegg.api.gui.util;

import io.github.mgx01.brassburgchegg.api.gui.settings.functional.TextureSettings;
import io.github.mgx01.brassburgchegg.api.gui.settings.functional.TitleSettings;
import io.github.mgx01.brassburgchegg.api.gui.settings.positional.WidgetPos;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class GuiUtil {


    public static void drawCenteredText(GuiGraphics graphics, Font font, Component text, int centerX, int y, int color) {
        int textWidth = font.width(text);
        graphics.drawString(font, text, centerX - (textWidth / 2), y, color, false);
    }

    public static int getHorizontalCenter(WidgetPos pos, int parentX) {
        return pos.left(parentX) + (pos.width() / 2);
    }

    public static int getVerticalCenter(WidgetPos pos, int parentY) {
        return pos.top(parentY) + (pos.height() / 2) - 4;
    }

    public static void drawCenteredInArea(GuiGraphics graphics, Font font, Component text, WidgetPos pos, int parentX, int parentY, int color, boolean shadow) {
        int centerX = getHorizontalCenter(pos, parentX);
        int centerY = getVerticalCenter(pos, parentY);
        int textWidth = font.width(text);

        graphics.drawString(font, text, centerX - (textWidth / 2), centerY, color, shadow);
    }

    public static void renderCenteredTitle(GuiGraphics graphics, Font font, TitleSettings settings, TextureSettings tex) {
        if (!settings.render()) return;
        int x = (tex.width() / 2) - (font.width(settings.text()) / 2);
        graphics.drawString(font, settings.text(), x, settings.yOffset(), settings.color(), false);
    }

}