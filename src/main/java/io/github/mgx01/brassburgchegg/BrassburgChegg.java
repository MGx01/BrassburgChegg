package io.github.mgx01.brassburgchegg;

import io.github.mgx01.brassburgchegg.Blocks.ModBlocks;
import io.github.mgx01.brassburgchegg.items.ModCreativeModeTabs;
import io.github.mgx01.brassburgchegg.items.ModItems;
import io.github.mgx01.brassburgchegg.menus.ModMenu;
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
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        //ModBlockEntities.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        modEventBus.addListener(this::addCreative);
        ModMenu.MENUS.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building Blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.CHEGG_DECK);
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.CHEGG_DECK_CASE);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.DECK_BOX);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}