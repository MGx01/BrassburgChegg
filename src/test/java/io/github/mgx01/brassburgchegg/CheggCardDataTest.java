package io.github.mgx01.brassburgchegg;
import io.github.mgx01.brassburgchegg.impl.data.CheggCardData;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheggCardDataTest {

    @Test
    @DisplayName("VERIFY CARD_DATA IS NOT EMPTY")
    void testCardDataSize() {
        Map<String, ResourceLocation> cardData = CheggCardData.getCardData();
        Assertions.assertNotNull(cardData, "ERROR - CheggCardData.getCardData() returned NULL!");
        Assertions.assertFalse(cardData.isEmpty(), "ERROR - CheggCardData doesn't have cards in it!");
        System.out.println("Test passed! Found " + cardData.size() + "cards.");
    }

    @Test
    @DisplayName("PRINT ALL RESOURCE PATHS")
    void testPrintKeys() {
        List<String> paths = CheggCardData.getEntityNameList();

        System.out.println("--- START RESOURCE PATH LIST ---");
        paths.forEach(System.out::println);
        System.out.println("--- END RESOURCE PATH LIST ---");
    }

    @Test
    @DisplayName("PRINT ALL RESOURCE PATHS")
    void testPrintPaths() {
        List<String> paths = CheggCardData.getResourcePathList();

        System.out.println("--- START RESOURCE PATH LIST ---");
        paths.forEach(System.out::println);
        System.out.println("--- END RESOURCE PATH LIST ---");
    }

    @Test
    @DisplayName("VERIFY ENTITY-TO-PATH MAPPINGS")
    void testSpecificMappings() {
        Map<String, ResourceLocation> cardData = CheggCardData.getCardData();

    }

    @Test
    @DisplayName("DYNAMICALLY VERIFY ALL KEY-PATH PAIRS")
    void testAllMapCombinations() {
        Map<String, ResourceLocation> cardData = CheggCardData.getCardData();

        cardData.forEach((entityName, location) -> {
            String expectedFileName = CheggCardData.getEntityResourceName(entityName);
            String expectedPath = CheggCardData.getCardPath() + expectedFileName + CheggCardData.getImageFormat();
            assertEquals(expectedPath, location.getPath(),
                    "Mismatch for entity: " + entityName);

        });
    }

    @Test
    @DisplayName("DEBUG: DUMP HASHMAP CONTENTS")
    void debugDumpMap() {
        Map<String, ResourceLocation> cardData = CheggCardData.getCardData();

        System.out.println("\n" + "=".repeat(80));
        System.out.printf("%-20s | %-60s%n", "ENTITY NAME", "RESOURCE LOCATION");
        System.out.println("-".repeat(80));

        cardData.forEach((name, loc) -> {
            System.out.printf("%-20s | %s%n", name, loc.toString());
        });

        System.out.println("=".repeat(80));
        System.out.println("TOTAL ENTRIES: " + cardData.size());
        System.out.println("=".repeat(80) + "\n");
    }
}
