package io.github.mgx01.brassburgchegg.impl.gui.deck;

import io.github.mgx01.brassburgchegg.api.gui.BaseCustomScreen;
import io.github.mgx01.brassburgchegg.api.gui.colors.CheggColors;
import io.github.mgx01.brassburgchegg.api.gui.util.GuiUtil;
import io.github.mgx01.brassburgchegg.api.gui.util.TextureSettings;
import io.github.mgx01.brassburgchegg.api.gui.util.TitleSettings;
import io.github.mgx01.brassburgchegg.api.gui.util.WidgetPos;
import io.github.mgx01.brassburgchegg.api.gui.widgets.NavigationButtonWidget;
import io.github.mgx01.brassburgchegg.api.gui.widgets.TitleWidget;
import io.github.mgx01.brassburgchegg.main.BrassburgChegg;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MobPatternScreen extends BaseCustomScreen<DeckMenu> {

    //TEXTURE SETTINGS
    private static final TextureSettings PATTERN_BG = TextureSettings.of(
            ResourceLocation.fromNamespaceAndPath(BrassburgChegg.MOD_ID, "textures/gui/deck_background_pattern.png"),
            336, 256
    );
    private static final WidgetPos BACK_BTN_POS = new WidgetPos(15, 10, 55, 30);
    private static final WidgetPos TITLE_POS    = new WidgetPos(0, 10, 336, 25);
    private final TitleSettings patternTitle;
    private final String entityName;
    private final DeckScreen parent;

    public MobPatternScreen(DeckMenu menu, Inventory inv, Component title, String entityName, DeckScreen parent) {
        super(menu, inv, title, PATTERN_BG);
        this.entityName = entityName;
        this.parent = parent;
        this.patternTitle = new TitleSettings(true, "Patterns: " + this.entityName, CheggColors.WHITE, 10);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        GuiUtil.renderCenteredTitle(graphics, this.font, patternTitle, textureSettings);
    }

    @Override
    protected void init() {
        super.init();


        this.addRenderableWidget(new NavigationButtonWidget(
                BACK_BTN_POS.left(this.leftPos),
                BACK_BTN_POS.top(this.topPos),
                BACK_BTN_POS.width(),
                BACK_BTN_POS.height(),
                CheggColors.LIGHT_BROWN, CheggColors.WHITE, CheggColors.BROWN,
                true,
                Component.literal("< Back"),
                (ignored) -> this.goBack()
        ));

    }

    @Override
    public void onClose() {
        super.onClose();
    }

    private void goBack() {
        if (this.minecraft != null) {
            this.minecraft.setScreen(this.parent);
        }
    }
}