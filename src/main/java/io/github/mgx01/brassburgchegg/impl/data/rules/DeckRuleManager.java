package io.github.mgx01.brassburgchegg.impl.data.rules;

public class DeckRuleManager {

    private DeckRuleset getActiveRules() {
        return RulesetLoader.DEFAULT_RULES;
    }

    public int getMaxDeckSize() {
        return getActiveRules().maxDeckSize;
    }

    public int getMinCardAmount() {
        return getActiveRules().minCardAmount;
    }

    public int getMaxCardAmount() {
        return getActiveRules().maxCardAmount;
    }

    public int getSpawnManaCost(String entityName) {
        return getActiveRules().getSpawnCost(entityName);
    }

    public int getSpecialManaCost(String entityName) {
        return getActiveRules().getSpecialCost(entityName);
    }

    public boolean canAddMore(int currentTotal) {
        return currentTotal < getMaxDeckSize();
    }
}