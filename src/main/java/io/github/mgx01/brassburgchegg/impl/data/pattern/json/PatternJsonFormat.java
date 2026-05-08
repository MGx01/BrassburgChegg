package io.github.mgx01.brassburgchegg.impl.data.pattern.json;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

public class PatternJsonFormat {
    public static class GameConfig {
        public Map<String, EntityEntry> entities;
    }

    public static class EntityEntry {
        public GuiText gui_text;
        public EntityLogic logic;
    }

    public static class GuiText {
        public String special_description;
    }

    public static class EntityLogic {
        public List<String> action_pattern;
        public SpecialAbility special;
    }

    public static class SpecialAbility {
        public String type;
        public JsonObject params;
    }
}

