package io.github.mgx01.brassburgchegg.impl.data.rules;

import java.util.Map;

public class RulesetJsonFormat {
    public String rulesetId;
    public byte maxDeckSize;
    public byte minCardAmount;
    public byte maxCardAmount;
    public Map<String, Integer> manaCosts;
}