package io.github.mgx01.brassburgchegg.api.gui.widgets;

import io.github.mgx01.brassburgchegg.api.gui.colors.BoardColor;
import io.github.mgx01.brassburgchegg.impl.data.pattern.parsing.ParsedActionPattern;
import io.github.mgx01.brassburgchegg.impl.data.pattern.parsing.GridOffset;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

// Extending AbstractWidget gives us hovering, dragging, and clicking for free!
public class DraggablePatternWidget extends AbstractWidget {
    private static final ResourceLocation ATLAS = ResourceLocation.fromNamespaceAndPath("brassburgchegg", "textures/gui/board_textures/board_color_atlas.png");
    private final ParsedActionPattern pattern;

    // Board Data
    private final int boardX;
    private final int boardY;
    private final int tileSize = 16;
    private final int maxCols = 8;
    private final int maxRows = 10;

    // The dynamic position of our 'O' (Origin) on the grid (0-7, 0-9)
    private int originCol = 3;
    private int originRow = 4;

    private boolean isDragging = false;

    public DraggablePatternWidget(ParsedActionPattern pattern, int boardX, int boardY) {
        // AbstractWidget needs an x, y, width, and height to know where the mouse is allowed to click
        super(boardX, boardY, 8 * 16, 10 * 16, Component.empty());
        this.pattern = pattern;
        this.boardX = boardX;
        this.boardY = boardY;
    }

    // --- RENDERING ---

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (pattern == null || pattern.isEmpty()) return;

        // ORIGIN
        drawTile(graphics, originCol, originRow, BoardColor.SMITHING_TABLE);
        // MOVES
        for (GridOffset move : pattern.getMoves()) {
            int targetCol = originCol + move.x();
            int targetRow = originRow + move.z();
            BoardColor moveColor = isLightTile(targetCol, targetRow) ? BoardColor.LIME : BoardColor.GREEN;
            drawTile(graphics, targetCol, targetRow, moveColor);
        }

    }

    private boolean isLightTile(int col, int row) {
        return (col + row) % 2 == 0;
    }

    private void drawTile(GuiGraphics graphics, int gridCol, int gridRow, BoardColor color) {
        if (gridCol < 0 || gridCol >= maxCols || gridRow < 0 || gridRow >= maxRows) {
            return;
        }

        int screenX = this.boardX + (gridCol * tileSize);
        int screenY = this.boardY + (gridRow * tileSize);

        graphics.blit(ATLAS, screenX, screenY, color.u, color.v, 16, 16, 64, 64);
    }

    private boolean isValidMove(int targetCol, int targetRow) {
        // Calculate the distance from the current origin
        int relX = targetCol - this.originCol;
        int relZ = targetRow - this.originRow;

        // Loop through the Frog's pattern to see if this offset exists
        for (GridOffset move : pattern.getMoves()) {
            if (move.x() == relX && move.z() == relZ) {
                return true;
            }
        }
        return false;
    }

// --- INTERACTIVITY (CLICK TO MOVE) ---

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!this.isHoveredOrFocused()) return false;

        // Convert raw mouse pixels to grid coordinates
        int clickCol = ((int) mouseX - this.boardX) / tileSize;
        int clickRow = ((int) mouseY - this.boardY) / tileSize;

        // 1. Check if they clicked out of bounds
        if (clickCol < 0 || clickCol >= maxCols || clickRow < 0 || clickRow >= maxRows) {
            return false;
        }

        // 2. Check if the tile they clicked is a valid Green/Lime move
        if (isValidMove(clickCol, clickRow)) {
            // Teleport the Frog!
            this.originCol = clickCol;
            this.originRow = clickRow;
            return true;
        }

        return false;
    }

    // We override these and return false to disable the old free-dragging behavior
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return super.mouseReleased(mouseX, mouseY, button);
    }
    private void updateOriginFromMouse(double mouseX, double mouseY) {
        int hoverCol = ((int) mouseX - this.boardX) / tileSize;
        int hoverRow = ((int) mouseY - this.boardY) / tileSize;

        // Clamp the values so you can't drag the origin off the board!
        this.originCol = Math.max(0, Math.min(hoverCol, maxCols - 1));
        this.originRow = Math.max(0, Math.min(hoverRow, maxRows - 1));
    }

    @Override
    protected void updateWidgetNarration(net.minecraft.client.gui.narration.NarrationElementOutput output) {
    }
}