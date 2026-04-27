package io.github.mgx01.brassburgchegg.impl.gui.deck;

import io.github.mgx01.brassburgchegg.api.gui.BaseCustomScreen;
import io.github.mgx01.brassburgchegg.api.gui.widgets.ClickableTextureWidget;
import io.github.mgx01.brassburgchegg.api.gui.widgets.PlusNumMinusBarWidget;
import io.github.mgx01.brassburgchegg.main.BrassburgChegg;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DeckScreen extends BaseCustomScreen<DeckMenu> {
    private static final ResourceLocation DECK_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(BrassburgChegg.MOD_ID, "textures/gui/deck_background_19_slots_with_widgets.png");

    private static final int BACKGROUND_TEXTURE_WIDTH = 336;
    private static final int BACKGROUND_TEXTURE_HEIGHT = 256;
    private static final int BACKGROUND_ATLAS_WIDTH = 336;
    private static final int BACKGROUND_ATLAS_HEIGHT = 256;
    private String selectedEntity = "";

    //LIST OF 29x29 CARD ICONS
    private static final List<ResourceLocation> CARD_ICONS = List.of(
            ResourceLocation.fromNamespaceAndPath(BrassburgChegg.MOD_ID, "textures/gui/cards/zombie.png")
    );

    //TEMPORARY PLACE FOR ENTITY NAME DEFINITION
    List<String> entities = List.of(
            "Zombie", "Creeper", "Rabbit", "Puffer Fish", "Iron Golem", "Frog",
            "Skeleton", "Blaze", "Phantom", "Enderman", "Slime", "Shulker Box",
            "Slime", "Shulker box", "Parrot", "Cat", "Sniffer", "Turtle",
            "Snow Golem", "Wither"
    );

    public DeckScreen(DeckMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, DECK_TEXTURE, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT, BACKGROUND_ATLAS_WIDTH, BACKGROUND_ATLAS_HEIGHT);
        this.setTitleConfiguration(true, "Chegg Deck Menu", COLOR_WHITE, 10);
    }

    @Override
    protected void init() {
        super.init();

        //PLUS NUMBER MINUS - WIDGET
        int startX = 56;
        int endX = 91;
        int startY = 75;
        int endY = 85;

        int widgetX = this.leftPos + startX;
        int widgetY = this.topPos + startY;
        int widgetWidth = endX - startX;
        int widgetHeight = endY - startY;

        byte initialValue = 0;
        byte minCounter = 0;
        byte maxCounter = 3;

        byte guiSlotRowMax = 6;
        byte guiSlotColumnMax = 4;
        byte guiWidgetXStepSize = 38;
        byte guiWidgetYStepSize = 46;
        byte guiLastRowSlotAmount = 1;
        int originalWidgetX = widgetX;

        for (int i = 0; i < guiSlotColumnMax; i++){
            if (i == guiSlotColumnMax -1){
                guiSlotRowMax = guiLastRowSlotAmount;
            }
            for(int j = 0; j < guiSlotRowMax; j++){
                this.addRenderableWidget(new PlusNumMinusBarWidget(
                        widgetX, widgetY, widgetWidth, widgetHeight,
                        initialValue, minCounter, maxCounter,
                        (newValue) -> {
                            System.out.println("Card count changed to: " + newValue);
                        }
                ));
                widgetX += guiWidgetXStepSize;
            }
            widgetY += guiWidgetYStepSize;
            widgetX = originalWidgetX;
        }

        //ENTITIY TEXTURE - WIDGET
        startX = 59;
        endX = 88;
        startY = 44;
        endY = 73;
        //59 44 - 86 72
        widgetX = this.leftPos + startX;
        widgetY = this.topPos + startY;
        widgetWidth = endX - startX;
        widgetHeight = endY - startY;

        guiSlotRowMax = 6;
        guiSlotColumnMax = 4;
        guiWidgetXStepSize = 38;
        guiWidgetYStepSize = 46;
        guiLastRowSlotAmount = 3;
        originalWidgetX = widgetX;

        this.addRenderableWidget(new ClickableTextureWidget(
                widgetX, widgetY, widgetWidth, widgetHeight,
                "Zombie",
                ResourceLocation.fromNamespaceAndPath(BrassburgChegg.MOD_ID, "textures/gui/cards/zombie.png"
                ), () -> this.selectedEntity, (newId) -> {
                        this.selectedEntity = newId;
                        System.out.println("Selected: " + newId);
                        }));

        startX = 97; //+38
        endX = 126; //+38
        startY = 44;
        endY = 73;

        widgetX = this.leftPos + startX;
        widgetY = this.topPos + startY;
        widgetWidth = endX - startX;
        widgetHeight = endY - startY;

        this.addRenderableWidget(new ClickableTextureWidget(
                widgetX, widgetY, widgetWidth, widgetHeight,
                "Creeper",
                ResourceLocation.fromNamespaceAndPath(BrassburgChegg.MOD_ID, "textures/gui/cards/creeper.png"
                ), () -> this.selectedEntity, (newId) -> {
            this.selectedEntity = newId;
            System.out.println("Selected: " + newId);
        }));
    }
}