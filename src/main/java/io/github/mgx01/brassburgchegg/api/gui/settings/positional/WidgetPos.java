package io.github.mgx01.brassburgchegg.api.gui.settings.positional;

public class WidgetPos {
    public int x, y, w, h;

    public WidgetPos(int x1, int y1, int x2, int y2) {
        this.x = Math.min(x1, x2);
        this.y = Math.min(y1, y2);
        this.w = Math.abs(x2 - x1);
        this.h = Math.abs(y2 - y1);
    }
    public WidgetPos(WidgetPos toCopy) {
        this.x = toCopy.x;
        this.y = toCopy.y;
        this.w = toCopy.w;
        this.h = toCopy.h;
    }

    public int left(int parentX) { return parentX + this.x; }
    public int top(int parentY) { return parentY + this.y; }
    public int width() { return this.w; }
    public int height() { return this.h; }

    public int centerX(int parentX) {
        return left(parentX) + (w / 2);
    }

    public int centerY(int parentY) {
        return top(parentY) + (h / 2);
    }


}