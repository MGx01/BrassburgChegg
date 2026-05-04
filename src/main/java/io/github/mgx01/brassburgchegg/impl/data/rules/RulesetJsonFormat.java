package io.github.mgx01.brassburgchegg.impl.data.rules;

import io.github.mgx01.brassburgchegg.impl.data.card_data.CardMana;

import java.util.Map;

public class RulesetJsonFormat {
    public String rulesetId;
    public int maxDeckSize;
    public int minCardAmount;
    public int maxCardAmount;
    public Map<String, CardMana> manaCosts;
}