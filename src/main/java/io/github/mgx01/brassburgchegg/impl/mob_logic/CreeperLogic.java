package io.github.mgx01.brassburgchegg.impl.mob_logic;

import io.github.mgx01.brassburgchegg.impl.data.pattern.json.ParamLoader;
import io.github.mgx01.brassburgchegg.impl.data.pattern.params.MobParams;

public class CreeperLogic {
    private final MobParams.Creeper params;

    public CreeperLogic() {
        this.params = ParamLoader.getParams("Creeper", MobParams.Creeper.class);
    }

    public void executeSpecial() {

    }
}