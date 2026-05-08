package io.github.mgx01.brassburgchegg.api.gui.colors;

public enum BoardColor {
    BROWN(0, 0),
    DEEP_BLACK(16, 0),
    GREEN(32, 0),
    LIGHT_GRAY(48, 0),
    MAGENTA(0, 16),
    PINK(16, 16),
    PURPLE(32, 16),
    BLACK(48, 16),
    CYAN(0, 32),
    LIGHT_BLUE(16, 32),
    LIME(32, 32),
    ORANGE(48, 32),
    RED(0, 48),
    SMITHING_TABLE(16, 48), // 'SmithingTableBottom'
    WHITE(32, 48),
    YELLOW(48, 48);

    public final int u;
    public final int v;

    BoardColor(int u, int v) {
        this.u = u;
        this.v = v;
    }
}