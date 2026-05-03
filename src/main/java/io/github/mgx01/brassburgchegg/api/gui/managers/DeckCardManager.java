package io.github.mgx01.brassburgchegg.api.gui.managers;

import io.github.mgx01.brassburgchegg.impl.data.rules.DeckRuleManager;

import java.util.HashMap;
import java.util.Map;

public class DeckCardManager {
    private final Map<String, Byte> currentCounts;
    private final DeckRuleManager ruleManager;

    // LOAD EMPTY
    public DeckCardManager(DeckRuleManager ruleManager) {
        this(ruleManager, new HashMap<>());
    }
    // LOAD SAVED
    public DeckCardManager(DeckRuleManager ruleManager, Map<String, Byte> initialData) {
        this.ruleManager = ruleManager;
        this.currentCounts = new HashMap<>(initialData);
    }

    public boolean updateCount(String entityName, byte newValue) {
        if (isUpdateAllowed(entityName, newValue)) {
            currentCounts.put(entityName, newValue);
            return true;
        }
        return false;
    }
    public boolean isAtMaxCount(){
        return getTotalCount() >= ruleManager.getMaxCardAmount();
    }

    public boolean isUpdateAllowed(String entityName, int targetValue) {
        int currentCount = getCount(entityName);
        int delta = targetValue - currentCount;
        int projectedTotal = getTotalCount() + delta;

        if (projectedTotal > Byte.MAX_VALUE) {
            return false;
        }

        return  projectedTotal <= ruleManager.getMaxDeckSize() &&
                projectedTotal >= 0 &&
                targetValue <= ruleManager.getMaxCardAmount() &&
                targetValue >= ruleManager.getMinCardAmount();
    }

    public byte getCount(String entityName) {
        return currentCounts.getOrDefault(entityName, (byte) 0);
    }

    public int getTotalCount() {
        return currentCounts.values().stream().mapToInt(Byte::intValue).sum();
    }

    // EXPOSE DATA
    public Map<String, Byte> getCurrentCounts() {
        return currentCounts;
    }

}