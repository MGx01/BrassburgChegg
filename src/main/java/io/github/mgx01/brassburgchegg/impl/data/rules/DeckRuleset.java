package io.github.mgx01.brassburgchegg.impl.data.rules;

import io.github.mgx01.brassburgchegg.impl.data.CardMana;
import java.util.HashMap;
import java.util.Map;

public class DeckRuleset {
    private final String id;

    public int maxDeckSize;
    public int minCardAmount;
    public int maxCardAmount;

    private final Map<String, CardMana> manaCosts = new HashMap<>();

    public DeckRuleset(String id) {
        this.id = id;
    }

    public void setMana(String entityName, CardMana costs) {
        this.manaCosts.put(entityName, costs);
    }

    public CardMana getCardMana(String entityName) {
        return this.manaCosts.get(entityName);
    }

    public int getSpawnCost(String entityName) {
        CardMana m = this.manaCosts.get(entityName);
        return m != null ? m.spawn : 0;
    }

    public int getSpecialCost(String entityName) {
        CardMana m = this.manaCosts.get(entityName);
        return m != null ? m.special : 0;
    }

    public void clear() {
        this.manaCosts.clear();
        this.maxDeckSize = 0;
        this.minCardAmount = 0;
        this.maxCardAmount = 0;
    }

    public String getId() {
        return this.id;
    }

    public Map<String, CardMana> getManaCosts() {
        return this.manaCosts;
    }
}