package io.github.mgx01.brassburgchegg.impl.data.patterns;

import java.util.List;
import java.util.Map;

public class PatternJsonFormat {
    public Map<String, EntityData> entities;

    public static class EntityData {
        public GuiText gui_text;
        public EntityLogic logic;
    }

    public static class GuiText {
        public String special_description;
    }

    public static class EntityLogic {
        public List<String> action_pattern;

        // This will be null for mobs like the Skeleton that don't have a special defined!
        public SpecialData special;
    }

    public static class SpecialData {
        public String type;

        // This will be null if a special doesn't use a specific grid pattern (like the Frog)
        public List<String> special_pattern;

        // Maps the arbitrary key-value pairs (like pull_distance, friendly_fire)
        public Map<String, Object> params;

        @SuppressWarnings("unchecked")
        public <T> T getParam(String key, T defaultValue) {
            if (params != null && params.containsKey(key)) {
                try {
                    return (T) params.get(key);
                } catch (ClassCastException e) {
                    return defaultValue;
                }
            }
            return defaultValue;
        }
    }
}