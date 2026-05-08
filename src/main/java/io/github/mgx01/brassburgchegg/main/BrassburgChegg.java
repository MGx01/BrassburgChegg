package io.github.mgx01.brassburgchegg.main;

import io.github.mgx01.brassburgchegg.impl.data.pattern.json.ParamLoader;
import io.github.mgx01.brassburgchegg.impl.data.pattern.json.PatternJsonLoader;

import io.github.mgx01.brassburgchegg.impl.data.pattern.params.MobParams;
import io.github.mgx01.brassburgchegg.impl.data.rules.RulesetLoader;
import io.github.mgx01.brassburgchegg.impl.mob_logic.FrogLogic;
import io.github.mgx01.brassburgchegg.impl.registry.ModBlock;
import io.github.mgx01.brassburgchegg.impl.registry.ModCreativeModeTab;
import io.github.mgx01.brassburgchegg.impl.registry.ModItem;
import io.github.mgx01.brassburgchegg.impl.registry.ModMenu;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(BrassburgChegg.MOD_ID)
public class BrassburgChegg {
    public static final String MOD_ID = "brassburgchegg";
    public static final Logger LOGGER = LogUtils.getLogger();


    public BrassburgChegg(IEventBus modEventBus, ModContainer modContainer) {

        // LOAD THE JSON INTO MEMORY
        new PatternJsonLoader().load();

        // TEST LOADING
        testFrogLoad();
        testCreeperLoad();

        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        RulesetLoader.INSTANCE.load();
        ModItem.register(modEventBus);
        ModBlock.BLOCKS.register(modEventBus);
        ModCreativeModeTab.register(modEventBus);
        modEventBus.addListener(this::addCreative);
        ModMenu.MENUS.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItem.CHEGG_DECK);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItem.CHEGG_DECK_CASE);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlock.DECK_BOX);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    private void testFrogLoad() {
        LOGGER.info("=========================================");
        LOGGER.info("🧪 TESTING ULTIMATE FROG LOGIC...");

        try {
            FrogLogic dummyFrog = new FrogLogic();
            LOGGER.info("✅ SUCCESS! Frog object created cleanly.");
            dummyFrog.executeSpecial();
            dummyFrog.debugPrintPattern();

        } catch (Exception e) {
            LOGGER.error("❌ TEST FAILED! Something crashed.", e);
        }
        LOGGER.info("=========================================");
    }

    private void testCreeperLoad() {
        LOGGER.info("=========================================");
        LOGGER.info("🧪 TESTING CREEPER DATA LOAD");

        try {
            MobParams.Creeper creeperData = ParamLoader.getParams("Creeper", MobParams.Creeper.class);

            LOGGER.info("SUCCESS! Creeper data loaded seamlessly.");
            LOGGER.info("Fwendly Faia: {}", creeperData.friendly_fire);

        } catch (Exception e) {
            LOGGER.error("TEST FAILED! Could not load Frog params.", e);
        }
        LOGGER.info("=========================================");
    }
}