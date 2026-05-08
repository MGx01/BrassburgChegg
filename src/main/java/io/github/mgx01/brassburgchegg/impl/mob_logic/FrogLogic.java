package io.github.mgx01.brassburgchegg.impl.mob_logic;

import io.github.mgx01.brassburgchegg.impl.data.pattern.json.ParamLoader;
import io.github.mgx01.brassburgchegg.impl.data.pattern.params.MobParams;
import io.github.mgx01.brassburgchegg.impl.data.pattern.parsing.GridOffset;
import io.github.mgx01.brassburgchegg.impl.data.pattern.parsing.ParsedActionPattern;
import io.github.mgx01.brassburgchegg.impl.data.pattern.parsing.PatternParser;

import java.util.List;

public class FrogLogic {
    private final MobParams.Frog params;
    private final ParsedActionPattern pattern;

    public FrogLogic() {
        this.params = ParamLoader.getParams("Frog", MobParams.Frog.class);
        List<String> rawGrid = ParamLoader.getPattern("Frog");
        this.pattern = PatternParser.parse(rawGrid, "Frog");
    }

    public void executeSpecial() {
        int range = params.special_range;
        int distance = params.pull_distance;

        System.out.println("Frog pulling target from " + range + " away by " + distance + " blocks!");
    }

    public void debugPrintPattern() {
        System.out.println("Frog has " + pattern.getMoves().size() + " valid move tiles.");
        System.out.println("Frog has " + pattern.getAttacks().size() + " valid attack tiles.");
    }
}