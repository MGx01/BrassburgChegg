package io.github.mgx01.brassburgchegg.impl.data.rules;
import io.github.mgx01.brassburgchegg.impl.data.rules.RulesetLoader;
import io.github.mgx01.brassburgchegg.impl.data.rules.DeckRuleset;


public class DeckRuleManager {

    private DeckRuleset getActiveRules() {
        return RulesetLoader.DEFAULT_RULES;
    }

    public byte getMaxDeckSize() {
        return getActiveRules().maxDeckSize;
    }

    public byte getMinCardAmount() {
        return getActiveRules().minCardAmount;
    }

    public byte getMaxCardAmount() {
        return getActiveRules().maxCardAmount;
    }

    public int getManaCost(String entityName) {
        return getActiveRules().getMana(entityName);
    }

    public boolean canAddMore(int currentTotal) {
        return currentTotal < getMaxDeckSize();
    }
}