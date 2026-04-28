package io.github.mgx01.brassburgchegg.api.gui.widgets;

import io.github.mgx01.brassburgchegg.api.gui.colors.CheggColors;
import io.github.mgx01.brassburgchegg.api.gui.managers.WidgetSelectionManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ClickableTextureWidget extends AbstractWidget {
    private final String entityId;
    private final ResourceLocation texture;
    private final WidgetSelectionManager<String> manager;

    public ClickableTextureWidget(int x, int y, int width, int height, String entityId,
                                  ResourceLocation texture, WidgetSelectionManager<String> manager) {
        super(x, y, width, height, Component.empty());
        this.entityId = entityId;
        this.texture = texture;
        this.manager = manager;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.blit(texture, getX(), getY(), 0, 0, width, height, width, height);

        if (manager.isSelected(entityId)) {
            graphics.fill(getX(), getY(), getX() + width, getY() + height, CheggColors.WHITE_80);
        }
        else if (this.isMouseOver(mouseX, mouseY)) {
            graphics.fill(getX(), getY(), getX() + width, getY() + height, CheggColors.WHITE_20); // ~20% white
        }
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return this.active && this.visible
                && mouseX >= (double)this.getX()
                && mouseY >= (double)this.getY()
                && mouseX < (double)(this.getX() + this.width)
                && mouseY < (double)(this.getY() + this.height);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        manager.select(entityId);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narration) {
        narration.add(NarratedElementType.TITLE, Component.literal("Select " + this.entityId + " Card"));
    }
}