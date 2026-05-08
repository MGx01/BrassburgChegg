package io.github.mgx01.brassburgchegg.impl.data.pattern.json;

import com.google.gson.Gson;

import java.util.List;

public class ParamLoader {
    private static final Gson GSON = new Gson();

    public static <T> T getParams(String entityId, Class<T> paramClass) {
        PatternJsonFormat.EntityEntry entry = PatternJsonLoader.CONFIG_DATA.get(entityId);

        if (entry != null && entry.logic != null && entry.logic.special != null && entry.logic.special.params != null) {
            return GSON.fromJson(entry.logic.special.params, paramClass);
        }

        throw new IllegalArgumentException("Could not load params for entity: " + entityId);
    }

    public static List<String> getPattern(String entityId) {
        var entry = PatternJsonLoader.CONFIG_DATA.get(entityId);

        if (entry != null && entry.logic != null && entry.logic.action_pattern != null) {
            return entry.logic.action_pattern;
        }
        throw new IllegalArgumentException("Could not find action_pattern for: " + entityId);
    }
}
