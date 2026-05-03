package io.github.mgx01.brassburgchegg.impl.gui.deck;

import io.github.mgx01.brassburgchegg.api.gui.BaseCustomScreen;
import io.github.mgx01.brassburgchegg.api.gui.colors.CheggColors;
import io.github.mgx01.brassburgchegg.api.gui.managers.DeckCardManager;
import io.github.mgx01.brassburgchegg.api.gui.settings.color.WidgetColors;
import io.github.mgx01.brassburgchegg.api.gui.util.GuiUtil;
import io.github.mgx01.brassburgchegg.api.gui.settings.functional.TextureSettings;
import io.github.mgx01.brassburgchegg.api.gui.settings.functional.TitleSettings;
import io.github.mgx01.brassburgchegg.api.gui.settings.positional.WidgetPos;
import io.github.mgx01.brassburgchegg.api.gui.util.TextAlignment;
import io.github.mgx01.brassburgchegg.api.gui.widgets.BackButtonWidget;
import io.github.mgx01.brassburgchegg.api.gui.widgets.DynamicLabelWidget;
import io.github.mgx01.brassburgchegg.impl.data.CheggCardData;
import io.github.mgx01.brassburgchegg.impl.data.CheggCardMana;
import io.github.mgx01.brassburgchegg.impl.data.rules.DeckRuleManager;
import io.github.mgx01.brassburgchegg.main.BrassburgChegg;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MobPatternScreen extends BaseCustomScreen<DeckMenu> {

    // --- TEXTURE SETTINGS ---
    private static final TextureSettings PATTERN_BG = TextureSettings.of(
            ResourceLocation.fromNamespaceAndPath(BrassburgChegg.MOD_ID, "textures/gui/deck_background_pattern.png"),
            336, 256
    );

    // --- TEXT ---
    private static final String buttonText = "< Back";
    
    // --- COLORS ---
    private static final WidgetColors BACK_BTN_COLORS = WidgetColors.builder()
            .rectangleColor(CheggColors.LIGHT_BROWN)
            .outlineColor(CheggColors.BROWN)
            .textColor(CheggColors.WHITE)
            .build();
    
    // --- LOCATION --- 
    private static final WidgetPos BACK_BTN_LOC  = new WidgetPos(15, 10, 55, 30);
    private static final WidgetPos TEXTURE_LOC   = new WidgetPos(198, 67, 226, 95);
    private static final WidgetPos LABEL_LOC     = new WidgetPos(199, 47, 288, 57);
    private static final WidgetPos SPAWNCOST_LOC = new WidgetPos(237, 68, 288, 78);
    private static final WidgetPos SPECIALCOST_LOC = new WidgetPos(237, 85, 288, 96);
    
    // --- INSTANCE DATA ---
    private final String entityName;
    private final DeckScreen parent;
    private final TitleSettings patternTitle;
    private final DeckRuleManager ruleCardManager;
    private final DeckRuleManager deckRuleManager;

    public MobPatternScreen(DeckMenu menu, Inventory inv, Component title, String entityName, DeckScreen parent) {
        super(menu, inv, title, PATTERN_BG);
        this.entityName = entityName;
        this.parent = parent;
        this.ruleCardManager = menu.getRuleManager();
        this.deckRuleManager = menu.getRuleManager();
        // REFACTOR MAY BE NECESSARY BECAUSE THIS STINKS
        this.patternTitle = new TitleSettings(true, "Patterns: " + this.entityName, CheggColors.WHITE, 10);

    }

    @Override
    protected void init() {
        super.init();
        this.addBackButton();
        this.addSpawnCostLabel();
        this.addSpecialCostLabel();
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        super.renderBg(graphics, partialTick, mouseX, mouseY);
        this.drawEntityTexture(graphics);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        addEntityLabel(graphics);
    }


    private void addBackButton() {
        this.addRenderableWidget(new BackButtonWidget(
                BACK_BTN_LOC.left(this.leftPos), BACK_BTN_LOC.top(this.topPos), BACK_BTN_LOC.w, BACK_BTN_LOC.h,
                BACK_BTN_COLORS,
                Component.literal(buttonText),
                (ignored) -> this.goBack()
        ));
    }

    private void drawEntityTexture(GuiGraphics graphics) {
        ResourceLocation mobTexture = CheggCardData.getCardData().get(this.entityName);

        if (mobTexture != null) {
            int x = this.leftPos + TEXTURE_LOC.x;
            int y = this.topPos + TEXTURE_LOC.y;
            int width = TEXTURE_LOC.w;
            int height = TEXTURE_LOC.h;

            graphics.blit(mobTexture, x, y, 0, 0, width, height, width, height);
        }
    }

    private void addEntityLabel(GuiGraphics graphics){
        int x = TextAlignment.CENTER.calculateX(font, entityName, LABEL_LOC.left(this.leftPos), LABEL_LOC.w);
        graphics.drawString(this.font, entityName, x, LABEL_LOC.top(this.topPos), CheggColors.BROWN_DARK, false);
    }

    private void addSpawnCostLabel(){
        this.addRenderableWidget(new DynamicLabelWidget(
                SPAWNCOST_LOC.left(this.leftPos), SPAWNCOST_LOC.top(this.topPos), SPAWNCOST_LOC.w, SPAWNCOST_LOC.h,
                this::getSpawnCostText, this::getManaColor, TextAlignment.CENTER
        ));
    }

    private void addSpecialCostLabel(){
        this.addRenderableWidget(new DynamicLabelWidget(
                SPECIALCOST_LOC.left(this.leftPos), SPECIALCOST_LOC.top(this.topPos), SPECIALCOST_LOC.w, SPECIALCOST_LOC.h,
                this::getSpecialCostText, this::getManaColor, TextAlignment.CENTER
        ));
    }

    // --- HELPER ---

    private String getSpawnCostText() {
        return "Spawn: " + deckRuleManager.getSpawnManaCost(entityName);
    }

    private String getSpecialCostText() {
        return "Special: " + deckRuleManager.getSpecialManaCost(entityName);
    }

    private int getManaColor(){
        return CheggColors.MANA_BLUE;
    }

    // --- DEPENDENCIES ---
    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        GuiUtil.renderCenteredTitle(graphics, this.font, patternTitle, textureSettings);
    }

    private void goBack() {
        if (this.minecraft != null) {
            this.minecraft.setScreen(this.parent);
        }
    }
}