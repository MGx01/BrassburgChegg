package io.github.mgx01.brassburgchegg.impl.data;

import io.github.mgx01.brassburgchegg.main.BrassburgChegg;
import net.minecraft.resources.ResourceLocation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CheggCardData {
    private static final String cardPath = "textures/gui/cards/";
    private static final String imageFormat = ".png";
    private static final Map<String, ResourceLocation> CARD_DATA = new LinkedHashMap<>();

    public static void registerCard(String entityName) {
        String resourceName = getEntityResourceName(entityName);
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(
                BrassburgChegg.MOD_ID,
                cardPath + resourceName + imageFormat
        );
        CARD_DATA.put(entityName, texture);
    }

    public static String getEntityResourceName(String entity) {
        return entity.toLowerCase().replace(" ", "").trim();
    }

    public static ResourceLocation getTexture(String entityName) {
        return CARD_DATA.get(entityName);
    }

    public static List<String> getEntityNameList() {
        return List.copyOf(CARD_DATA.keySet());
    }

    public static Map<String, ResourceLocation> getCardData() {
        return CARD_DATA;
    }
}