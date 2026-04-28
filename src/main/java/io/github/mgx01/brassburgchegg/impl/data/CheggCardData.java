package io.github.mgx01.brassburgchegg.impl.data;

import io.github.mgx01.brassburgchegg.main.BrassburgChegg;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CheggCardData {
    private static final String cardPath = "textures/gui/cards/";
    private static final String imageFormat = ".png";
    private static final Map<String, ResourceLocation> CARD_DATA = Util.make(new LinkedHashMap<>(), map -> {
        add(map, "Zombie");
        add(map, "Creeper");
        add(map, "Pig");
        add(map, "Rabbit");
        add(map, "Puffer Fish");
        add(map, "Iron Golem");
        add(map, "Frog");
        add(map, "Skeleton");
        add(map, "Blaze");
        add(map, "Phantom");
        add(map, "Enderman");
        add(map, "Slime");
        add(map, "Parrot");
        add(map, "Cat");
        add(map, "Sniffer");
        add(map, "Turtle");
        add(map, "Snow Golem");
        add(map, "Wither");
        add(map, "Shulker Box");
    });

    private static void add(Map<String, ResourceLocation> map, String entity) {
        add(map, entity, getEntityResourceName(entity));
    }

    private static void add(Map<String, ResourceLocation> map, String entity, String entityResourceName) {
        map.put(entity, ResourceLocation.fromNamespaceAndPath(BrassburgChegg.MOD_ID, getEntityResourceLocation(entityResourceName)));
    }

    public static String getEntityResourceName(String entity){
        return entity.toLowerCase().replace(" ", "").trim();
    }

    public static String getEntityResourceLocation(String entityResourceName){return cardPath + entityResourceName + imageFormat;}
    public static String getCardPath(){return cardPath;}
    public static String getImageFormat(){return imageFormat;}
    public static int getCardMapSize() {return CARD_DATA.size();}

    public static Map<String, ResourceLocation> getCardData() {
        return CARD_DATA;
    }

    public static List<String> getEntityNameList() {
        return List.copyOf(CARD_DATA.keySet());
    }

    public static List<String> getResourcePathList() {
        return CARD_DATA.values().stream()
                .map(ResourceLocation::toString)
                .toList();
    }
}
