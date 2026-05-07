package io.github.mgx01.brassburgchegg.api.pattern_parsing;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

public class PatternJsonFormat {
    public class GameConfig {
        public Map<String, EntityEntry> entities;
    }

    public class EntityEntry {
        public GuiText gui_text;
        public EntityLogic logic;
    }

    public class GuiText {
        public String special_description;
    }

    public class EntityLogic {
        public List<String> action_pattern;
        public SpecialAbility special;
    }

    public class SpecialAbility {
        public String type;
        public JsonObject params; // We keep this as JSON to parse it specifically later
    }
}

