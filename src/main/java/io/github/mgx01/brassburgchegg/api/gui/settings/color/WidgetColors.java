package io.github.mgx01.brassburgchegg.api.gui.settings.color;

import io.github.mgx01.brassburgchegg.api.gui.colors.CheggColors;

public record WidgetColors(
        int rectangleColor,
        int outlineColor,
        int textColor,
        int plusColor,
        int minusColor,
        int disabledRectangleColor, // NEW: For your unclickable boxes
        int disabledTextColor       // NEW: For your unclickable text
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int rectangleColor = CheggColors.BEIGE_DARK;
        private int outlineColor = CheggColors.LIGHT_BROWN;
        private int textColor = CheggColors.WHITE;
        private int plusColor = CheggColors.LIME;
        private int minusColor = CheggColors.RED_DARK;
        private int disabledRectangleColor = CheggColors.GREY; // NEW Default
        private int disabledTextColor = CheggColors.PINK;      // NEW Default

        public Builder rectangleColor(int color) { this.rectangleColor = color; return this; }
        public Builder outlineColor(int color) { this.outlineColor = color; return this; }
        public Builder textColor(int color) { this.textColor = color; return this; }
        public Builder plusColor(int color) { this.plusColor = color; return this; }
        public Builder minusColor(int color) { this.minusColor = color; return this; }
        public Builder disabledRectangleColor(int color) { this.disabledRectangleColor = color; return this; }
        public Builder disabledTextColor(int color) { this.disabledTextColor = color; return this; }

        public WidgetColors build() {
            return new WidgetColors(
                    rectangleColor, outlineColor, textColor, plusColor, minusColor,
                    disabledRectangleColor, disabledTextColor
            );
        }
    }
}