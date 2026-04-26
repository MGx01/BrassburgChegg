package io.github.mgx01.brassburgchegg.impl.registry;

import io.github.mgx01.brassburgchegg.main.BrassburgChegg;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModBlock {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(BrassburgChegg.MOD_ID);

    public static final DeferredBlock<Block> DECK_BOX = registerBlock(
            "deckbox",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2.0f)
                    .explosionResistance(2.0f)
                    .sound(SoundType.BAMBOO_WOOD))
    );

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

}
