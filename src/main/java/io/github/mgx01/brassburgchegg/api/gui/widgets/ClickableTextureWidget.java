package io.github.mgx01.brassburgchegg.api.gui.widgets;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ClickableTextureWidget extends AbstractWidget {
    private final ResourceLocation texture;
    private final String entityId;
    private final Supplier<String> activeIdSupplier;
    private final Consumer<String> onClickAction;

    public ClickableTextureWidget(int x, int y, int width, int height, String entityId,
                          ResourceLocation texture,
                          Supplier<String> activeIdSupplier,
                          Consumer<String> onClickAction) {
        super(x, y, width, height, Component.empty());
        this.entityId = entityId;
        this.texture = texture;
        this.activeIdSupplier = activeIdSupplier;
        this.onClickAction = onClickAction;
    }
    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.blit(this.texture, getX(), getY(), 0, 0, width, height, width, height);
        boolean isActive = this.entityId.equals(activeIdSupplier.get());
        if (isActive) {
            graphics.fill(getX(), getY(), getX() + width, getY() + height, 0x80FFFFFF);
            graphics.renderOutline(getX(), getY(), width, height, 0xFFFFFFFF);
        } else if (this.isHoveredOrFocused()) {
            // Softer highlight just for hovering
            graphics.fill(getX(), getY(), getX() + width, getY() + height, 0x40FFFFFF);
        }
    }
    @Override
    public void onClick(double mouseX, double mouseY) {
        this.onClickAction.accept(this.entityId);
    }

    protected void updateWidgetNarration(NarrationElementOutput narration) {
        narration.add(NarratedElementType.TITLE, Component.literal("Select " + this.entityId + " Card"));
    }
}