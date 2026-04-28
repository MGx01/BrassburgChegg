package io.github.mgx01.brassburgchegg.api.gui.util;

public record TitleSettings(boolean render, String text, int color, int yOffset) {
    public static final TitleSettings HIDE = new TitleSettings(false, "", 0, 0);
}