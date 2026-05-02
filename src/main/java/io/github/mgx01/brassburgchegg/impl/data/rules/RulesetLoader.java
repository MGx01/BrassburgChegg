package io.github.mgx01.brassburgchegg.impl.data.rules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mojang.logging.LogUtils;
import io.github.mgx01.brassburgchegg.impl.data.CheggCardData;
import net.neoforged.fml.loading.FMLPaths;
import org.slf4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class RulesetLoader {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("brassburgchegg_entities.json");

    public static final DeckRuleset DEFAULT_RULES = new DeckRuleset("default");

    public static void load() {
        ensureConfigFileExists();

        try {
            RulesetJsonFormat data = loadConfigFromFile();
            if (data != null) {
                synchronizeWithInternalDefaults(data);
                applyToGameMemory(data);
            }
        } catch (JsonSyntaxException e) {
            LOGGER.error("BrassburgChegg: Invalid JSON syntax in config!", e);
        } catch (IOException e) {
            LOGGER.error("BrassburgChegg: I/O error during ruleset loading!", e);
        }
    }

    private static void ensureConfigFileExists() {
        if (!Files.exists(CONFIG_PATH)) {
            RulesetJsonFormat master = getInternalDefaults();
            saveConfigToFile(master);
            LOGGER.info("BrassburgChegg: Initialized default config from master template.");
        }
    }

    private static RulesetJsonFormat loadConfigFromFile() throws IOException {
        try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
            return GSON.fromJson(reader, RulesetJsonFormat.class);
        }
    }

    private static void synchronizeWithInternalDefaults(RulesetJsonFormat userData) {
        RulesetJsonFormat master = getInternalDefaults();
        boolean modified = false;

        if (userData.manaCosts == null) {
            userData.manaCosts = new LinkedHashMap<>();
            modified = true;
        }

        for (Map.Entry<String, Integer> entry : master.manaCosts.entrySet()) {
            if (!userData.manaCosts.containsKey(entry.getKey())) {
                userData.manaCosts.put(entry.getKey(), entry.getValue());
                modified = true;
                LOGGER.info("BrassburgChegg: Patching missing card '{}' into user config.", entry.getKey());
            }
        }

        if (modified) {
            saveConfigToFile(userData);
        }
    }

    private static void applyToGameMemory(RulesetJsonFormat data) {
        DEFAULT_RULES.clear();
        CheggCardData.getCardData().clear();

        DEFAULT_RULES.maxDeckSize = data.maxDeckSize;
        DEFAULT_RULES.minCardAmount = data.minCardAmount;
        DEFAULT_RULES.maxCardAmount = data.maxCardAmount;

        data.manaCosts.forEach((name, cost) -> {
            DEFAULT_RULES.setMana(name, cost);
            CheggCardData.registerCard(name);
        });

        LOGGER.info("BrassburgChegg: Active ruleset updated with {} cards.", data.manaCosts.size());
    }

    private static void saveConfigToFile(RulesetJsonFormat data) {
        try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            LOGGER.error("BrassburgChegg: Failed to write to config file!", e);
        }
    }

    private static RulesetJsonFormat getInternalDefaults() {
        String path = "/assets/brassburgchegg/data/default_rules.json";
        try (InputStream is = RulesetLoader.class.getResourceAsStream(path);
             Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return GSON.fromJson(reader, RulesetJsonFormat.class);
        } catch (Exception e) {
            LOGGER.error("CRITICAL: Internal master template is missing!", e);
            return new RulesetJsonFormat();
        }
    }
}