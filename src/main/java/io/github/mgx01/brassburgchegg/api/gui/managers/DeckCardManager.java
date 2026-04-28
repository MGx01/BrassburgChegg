package io.github.mgx01.brassburgchegg.api.gui.managers;

import java.util.HashMap;
import java.util.Map;

public class DeckCardManager {
    private final Map<String, Byte> currentCounts = new HashMap<>();
    private final DeckRuleManager ruleManager;

    public DeckCardManager(DeckRuleManager ruleManager) {
        this.ruleManager = ruleManager;
    }

    public boolean updateCount(String entityName, byte newValue) {
        if (isUpdateAllowed(entityName, newValue)) {
            currentCounts.put(entityName, newValue);
            return true;
        }
        return false;
    }

    public boolean isUpdateAllowed(String entityName, byte requestedValue) {
        int currentTotal = getTotalCount();
        byte oldCount = getCount(entityName);

        int projectedTotal = (currentTotal - oldCount) + requestedValue;

        return projectedTotal <= ruleManager.getMaxDeckSize();
    }

    public byte getCount(String entityName) {
        return currentCounts.getOrDefault(entityName, (byte) 0);
    }

    public int getTotalCount() {
        return currentCounts.values().stream().mapToInt(Byte::intValue).sum();
    }
}