package io.github.mgx01.brassburgchegg.api.gui.util;

import net.minecraft.resources.ResourceLocation;

public record TextureSettings(
        ResourceLocation location,
        int width,
        int height,
        int atlasWidth,
        int atlasHeight
) {
    public static TextureSettings of(ResourceLocation loc, int w, int h) {
        return new TextureSettings(loc, w, h, w, h);
    }
}