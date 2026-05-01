package io.github.mgx01.brassburgchegg.impl.gui.deck;

import io.github.mgx01.brassburgchegg.api.gui.BaseCustomScreen;
import io.github.mgx01.brassburgchegg.api.gui.colors.CheggColors;
import io.github.mgx01.brassburgchegg.api.gui.managers.DeckCardManager;
import io.github.mgx01.brassburgchegg.api.gui.managers.DeckRuleManager;
import io.github.mgx01.brassburgchegg.api.gui.managers.WidgetSelectionManager;
import io.github.mgx01.brassburgchegg.api.gui.settings.color.WidgetColors;
import io.github.mgx01.brassburgchegg.api.gui.settings.functional.TextureSettings;
import io.github.mgx01.brassburgchegg.api.gui.settings.functional.TitleSettings;
import io.github.mgx01.brassburgchegg.api.gui.settings.positional.WidgetPos;
import io.github.mgx01.brassburgchegg.api.gui.util.*;
import io.github.mgx01.brassburgchegg.api.gui.widgets.*;
import io.github.mgx01.brassburgchegg.impl.data.CheggCardData;
import io.github.mgx01.brassburgchegg.main.BrassburgChegg;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;
import java.util.Map;

public class DeckScreen extends BaseCustomScreen<DeckMenu> {

    // --- TEXTURE & STYLE ---
    private static final TextureSettings DECK_BG = TextureSettings.of(
            ResourceLocation.fromNamespaceAndPath(BrassburgChegg.MOD_ID, "textures/gui/deck_background.png"),
            336, 256
    );
    private static final TitleSettings DECK_TITLE = new TitleSettings(true, "Chegg Deck Menu", CheggColors.WHITE, 10);

    // --- WIDGET LOCATIONS ---
    private static final WidgetPos DECKCOUNT_LOC = new WidgetPos(174, 186, 276, 196);
    private static final WidgetPos PATTERN_LOC   = new WidgetPos(173, 203, 224, 215);
    private static final WidgetPos INFO_LOC      = new WidgetPos(227, 203, 278, 215);
    private static final WidgetPos TEXTURE_LOC   = new WidgetPos(59, 44, 88, 73);
    private static final WidgetPos PLUSMINUS_LOC = new WidgetPos(56, 75, 91, 85);

    // --- WIDGET COLORS ---
    private static final WidgetColors PLUSMINUS_COLORS = WidgetColors.builder()
            .rectangleColor(CheggColors.BROWN)
            .outlineColor(CheggColors.BROWN_DARK)
            .textColor(CheggColors.WHITE)
            .minusColor(CheggColors.RED_DARK)
            .plusColor(CheggColors.LIME)
            .build();

    private static final WidgetColors NAV_BTN_COLORS = WidgetColors.builder()
            .rectangleColor(CheggColors.BROWN)
            .disabledRectangleColor(CheggColors.GREY)
            .textColor(CheggColors.WHITE)
            .disabledTextColor(CheggColors.PINK)
            .build();


    // --- GRID SETTINGS ---
    private static final int ROW_SIZE = 6;
    private static final int X_STEP = 38;
    private static final int Y_STEP = 46;

    // --- MANAGERS ---
    private final DeckRuleManager ruleManager;
    private final DeckCardManager deckManager;
    private final WidgetSelectionManager<String> selectionManager = new WidgetSelectionManager<>("");

    public DeckScreen(DeckMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, DECK_BG);
        this.ruleManager = menu.getRuleManager();
        this.deckManager = menu.getDeckManager();
    }

    @Override
    protected void init() {
        super.init();
        this.addNavigationButtons();
        this.addStatusLabels();
        this.buildCardGrid();
    }

    private void addNavigationButtons() {


        this.addRenderableWidget(new GatedNavigationButtonWidget(
                PATTERN_LOC.left(this.leftPos), PATTERN_LOC.top(this.topPos), PATTERN_LOC.w, PATTERN_LOC.h,
                NAV_BTN_COLORS,
                Component.literal("Patterns"),
                this.selectionManager,
                this::openPatternScreen
        ));

        // CONFIG BUTTON
        this.addRenderableWidget(new GatedNavigationButtonWidget(
                INFO_LOC.left(this.leftPos), INFO_LOC.top(this.topPos), INFO_LOC.w, INFO_LOC.h,
                NAV_BTN_COLORS,
                Component.literal("Info"),
                this.selectionManager,
                (mob) -> System.out.println("Opening config for: " + mob)
        ));
    }
        //DECK LIMIT
    private void addStatusLabels() {
        this.addRenderableWidget(new DynamicLabelWidget(
                DECKCOUNT_LOC.left(leftPos), DECKCOUNT_LOC.top(topPos), DECKCOUNT_LOC.width(), DECKCOUNT_LOC.height(),
                this::getDeckStatusText, this::getDeckStatusColor, TextAlignment.CENTER
        ));
    }

    private void buildCardGrid() {
        List<String> entityNames = CheggCardData.getEntityNameList();
        Map<String, ResourceLocation> cardResourceMap = CheggCardData.getCardData();

        for (int i = 0; i < entityNames.size(); i++) {
            String name = entityNames.get(i);

            int xOffset = (i % ROW_SIZE) * X_STEP;
            int yOffset = (i / ROW_SIZE) * Y_STEP;

            int textureX = this.leftPos + TEXTURE_LOC.x + xOffset;
            int textureY = this.topPos + TEXTURE_LOC.y + yOffset;
            this.addRenderableWidget(new ClickableTextureWidget(
                    textureX, textureY, TEXTURE_LOC.w, TEXTURE_LOC.h,
                    name, cardResourceMap.get(name), selectionManager
            ));

            int barX = this.leftPos + PLUSMINUS_LOC.x + xOffset;
            int barY = this.topPos + PLUSMINUS_LOC.y + yOffset;
            this.addRenderableWidget(new PlusNumMinusBarWidget(
                    barX, barY, PLUSMINUS_LOC.w, PLUSMINUS_LOC.h,
                    PLUSMINUS_COLORS, menu.getDeckManager(), name
            ));
        }
    }

    // --- UTILITY LOGIC ---

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        GuiUtil.renderCenteredTitle(graphics, font, DECK_TITLE, textureSettings);
    }

    private String getDeckStatusText() {
        return "Cur. Deck Size " + deckManager.getTotalCount() + "/" + ruleManager.getMaxDeckSize();
    }

    private int getDeckStatusColor() {
        return (deckManager.getTotalCount() >= ruleManager.getMaxDeckSize()) ? CheggColors.RED_DARK : CheggColors.BROWN_DARK;
    }

    private void openPatternScreen(String selectedMob) {
        if (selectedMob == null || selectedMob.isEmpty()) {
            return;
        }
        if (this.minecraft == null || this.minecraft.player == null) {
            return;
        }
        this.minecraft.setScreen(new MobPatternScreen(
                this.menu,
                this.minecraft.player.getInventory(),
                Component.empty(),
                selectedMob,
                this
        ));
    }

}