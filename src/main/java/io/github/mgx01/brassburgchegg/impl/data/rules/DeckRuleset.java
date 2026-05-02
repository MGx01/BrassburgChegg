package io.github.mgx01.brassburgchegg.impl.data.rules;

import java.util.HashMap;
import java.util.Map;


public class DeckRuleset {
    private final String id;

    // Hardcoded fallback limits (overwritten by JSON)
    public byte maxDeckSize = 15;
    public byte minCardAmount = 0;
    public byte maxCardAmount = 3;

    private final Map<String, Integer> manaCosts = new HashMap<>();

    public DeckRuleset(String id) {
        this.id = id;
    }

    public void setMana(String entityName, int cost) {
        this.manaCosts.put(entityName, cost);
    }

    public int getMana(String entityName) {
        return this.manaCosts.getOrDefault(entityName, 0);
    }

    public void clear() {
        this.manaCosts.clear();
    }

    public String getId() {
        return this.id;
    }
}