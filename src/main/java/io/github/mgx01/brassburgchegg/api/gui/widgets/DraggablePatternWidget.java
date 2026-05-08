package io.github.mgx01.brassburgchegg.api.gui.widgets;

import io.github.mgx01.brassburgchegg.api.gui.colors.BoardColor;
import io.github.mgx01.brassburgchegg.api.gui.colors.CheggColors;
import io.github.mgx01.brassburgchegg.impl.data.pattern.parsing.ParsedActionPattern;
import io.github.mgx01.brassburgchegg.impl.data.pattern.parsing.GridOffset;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class DraggablePatternWidget extends AbstractWidget {
    private static final ResourceLocation ATLAS = ResourceLocation.fromNamespaceAndPath("brassburgchegg", "textures/gui/board_textures/board_color_atlas.png");
    private final ParsedActionPattern pattern;

    // --- DISPLAY STATES ---
    public enum DisplayMode {
        MOVE, ATTACK, SPECIAL
    }
    private DisplayMode currentMode = DisplayMode.MOVE;

    // BOARD
    private final int boardX;
    private final int boardY;
    private final int tileSize = 16;
    private final int maxCols = 8;
    private final int maxRows = 10;
    private int originCol = 3;
    private int originRow = 6;

    public DraggablePatternWidget(ParsedActionPattern pattern, int boardX, int boardY) {
        super(boardX, boardY, 8 * 16, 10 * 16, Component.empty());
        this.pattern = pattern;
        this.boardX = boardX;
        this.boardY = boardY;
    }

    public void setDisplayMode(DisplayMode mode) {
        this.currentMode = mode;
    }

    // --- RENDERING ---
    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderInteractiveGrid(graphics, mouseX, mouseY);
    }

    private void renderInteractiveGrid(GuiGraphics graphics, int mouseX, int mouseY) {
        if (pattern == null || pattern.isEmpty()) return;

        drawTile(graphics, originCol, originRow, BoardColor.SMITHING_TABLE);
        this.drawActivePattern(graphics);

        if (this.isHoveredOrFocused()) {
            this.renderHoverHighlight(graphics, mouseX, mouseY);
        }
    }

    private void drawActivePattern(GuiGraphics graphics) {
        for (GridOffset offset : getActiveOffsets()) {
            int targetCol = originCol + offset.x();
            int targetRow = originRow + offset.z();
            drawTile(graphics, targetCol, targetRow, getTileColor(targetCol, targetRow));
        }
    }

    private void renderHoverHighlight(GuiGraphics graphics, int mouseX, int mouseY) {
        int col = (mouseX - this.boardX) / tileSize;
        int row = (mouseY - this.boardY) / tileSize;

        if (isInsideBoard(col, row) && isValidOffset(col, row, getActiveOffsets())) {
            int x = getScreenX(col);
            int y = getScreenY(row);

            graphics.fill(x, y, x + tileSize, y + tileSize, CheggColors.WHITE_20);
        }
    }

    private void drawTile(GuiGraphics graphics, int gridCol, int gridRow, BoardColor color) {
        if (!isInsideBoard(gridCol, gridRow)) return;

        graphics.blit(ATLAS,
                getScreenX(gridCol),
                getScreenY(gridRow),
                color.u, color.v, tileSize, tileSize, 64, 64);
    }

    // --- HELPER ---
    private boolean isValidOffset(int targetCol, int targetRow, List<GridOffset> validList) {
        int relX = targetCol - this.originCol;
        int relZ = targetRow - this.originRow;

        for (GridOffset offset : validList) {
            if (offset.x() == relX && offset.z() == relZ) {
                return true;
            }
        }
        return false;
    }

    private List<GridOffset> getActiveOffsets() {
        return switch (currentMode) {
            case MOVE -> pattern.getMoves();
            case ATTACK -> pattern.getAttacks();
            case SPECIAL -> pattern.getSpecials();
        };
    }

    private int getScreenX(int col) { return this.boardX + (col * tileSize); }
    private int getScreenY(int row) { return this.boardY + (row * tileSize); }

    private boolean isInsideBoard(int col, int row) {
        return col >= 0 && col < maxCols && row >= 0 && row < maxRows;
    }

    private BoardColor getTileColor(int col, int row) {
        return switch (currentMode) {
            case MOVE -> (col + row) % 2 == 0 ? BoardColor.LIME : BoardColor.GREEN;
            case ATTACK -> BoardColor.RED;
            case SPECIAL -> BoardColor.MAGENTA;
        };
    }

    // --- INTERACTIVITY (CLICK TO MOVE) ---

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!this.isHoveredOrFocused()) return false;

        int clickCol = ((int) mouseX - this.boardX) / tileSize;
        int clickRow = ((int) mouseY - this.boardY) / tileSize;

        if (isValidOffset(clickCol, clickRow, getActiveOffsets())) {
            this.originCol = clickCol;
            this.originRow = clickRow;
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    protected void updateWidgetNarration(net.minecraft.client.gui.narration.NarrationElementOutput output) {
    }
}