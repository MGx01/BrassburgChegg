package io.github.mgx01.brassburgchegg.impl.items.custom;

import io.github.mgx01.brassburgchegg.impl.gui.deck.DeckMenu;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;

import java.util.HashMap;

public class CheggDeckCase extends Item {

    public CheggDeckCase(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {

            var deckCounts = new HashMap<String, Byte>();

            // SERVER REQUIREMENT
            player.openMenu(new SimpleMenuProvider(
                    (containerId, playerInventory, playerEntity) ->
                            new DeckMenu(containerId, playerInventory, deckCounts, stack),
                    Component.translatable("gui.brassburgchegg.deck_title")
            ));
        }

        // Tell the game the right-click was successful and to trigger the arm-swing animation
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}