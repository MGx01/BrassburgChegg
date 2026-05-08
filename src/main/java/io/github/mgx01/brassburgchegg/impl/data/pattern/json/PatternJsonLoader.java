package io.github.mgx01.brassburgchegg.impl.data.pattern.json;
import io.github.mgx01.brassburgchegg.api.json_loader.AbstractJsonLoader;

import java.util.HashMap;
import java.util.Map;

public class PatternJsonLoader extends AbstractJsonLoader<PatternJsonFormat.GameConfig> {
    public static Map<String, PatternJsonFormat.EntityEntry> CONFIG_DATA = new HashMap<>();

    public PatternJsonLoader() {
        super(
                PatternJsonFormat.GameConfig.class,
                "/assets/brassburgchegg/data/default_entity_patterns.json",
                "entity_patterns.json"
        );
    }

    @Override
    protected boolean synchronizeWithInternalDefaults(PatternJsonFormat.GameConfig masterData, PatternJsonFormat.GameConfig userData) {
        boolean changed = false;
        for (String key : masterData.entities.keySet()) {
            if (!userData.entities.containsKey(key)) {
                userData.entities.put(key, masterData.entities.get(key));
                changed = true;
                logger.info("Brassburg: Added missing entity '{}' to your config.", key);
            }
        }
        return changed;
    }

    @Override
    protected void applyToGameMemory(PatternJsonFormat.GameConfig data) {
        CONFIG_DATA = data.entities;

        logger.info("Brassburg: Synced {} entities to Game Memory!", CONFIG_DATA.size());
    }
}