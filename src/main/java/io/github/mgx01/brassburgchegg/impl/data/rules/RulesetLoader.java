package io.github.mgx01.brassburgchegg.impl.data.rules;

import io.github.mgx01.brassburgchegg.api.json_loader.AbstractJsonLoader;
import io.github.mgx01.brassburgchegg.impl.data.CardMana;
import io.github.mgx01.brassburgchegg.impl.data.CheggCardData;

import java.util.Map;

public class RulesetLoader extends AbstractJsonLoader<RulesetJsonFormat> {

    public static final DeckRuleset DEFAULT_RULES = new DeckRuleset("default");

    public static final RulesetLoader INSTANCE = new RulesetLoader();

    private RulesetLoader() {
        super(
                RulesetJsonFormat.class,
                "/assets/brassburgchegg/data/default_rules.json",
                "brassburgchegg_entities.json"
        );
    }

    @Override
    protected boolean synchronizeWithInternalDefaults(RulesetJsonFormat masterData, RulesetJsonFormat userData) {
        boolean modified = false;
        if (userData.manaCosts == null) {
            userData.manaCosts = masterData.manaCosts;
            return true;
        }

        for (Map.Entry<String, CardMana> entry : masterData.manaCosts.entrySet()) {
            if (!userData.manaCosts.containsKey(entry.getKey())) {
                userData.manaCosts.put(entry.getKey(), entry.getValue());
                modified = true;
                logger.info("BrassburgChegg: Patching missing card '{}'.", entry.getKey());
            }
        }
        return modified;
    }

    @Override
    protected void applyToGameMemory(RulesetJsonFormat data) {
        DEFAULT_RULES.clear();
        CheggCardData.getCardData().clear();

        DEFAULT_RULES.maxDeckSize = data.maxDeckSize;
        DEFAULT_RULES.minCardAmount = data.minCardAmount;
        DEFAULT_RULES.maxCardAmount = data.maxCardAmount;

        if (data.manaCosts != null) {
            data.manaCosts.forEach((name, cost) -> {
                DEFAULT_RULES.setMana(name, cost);
                CheggCardData.registerCard(name);
            });
        }

        logger.info("BrassburgChegg: Active ruleset loaded with {} cards.",
                data.manaCosts != null ? data.manaCosts.size() : 0);
    }
}