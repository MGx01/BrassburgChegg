package io.github.mgx01.brassburgchegg.items.custom;

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

public class CheggDeckCase extends Item {
    public CheggDeckCase(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            if (player instanceof ServerPlayer serverPlayer) {
                // 1. Create the container (RAM)
                SimpleContainer container = new SimpleContainer(9);

                // 2. LOAD data from the Item (Hard Drive) into the container
                ItemContainerContents contents = itemInHand.get(DataComponents.CONTAINER);
                if (contents != null) {
                    for (int i = 0; i < Math.min(container.getContainerSize(),contents.getSlots()); i++) {
                        container.setItem(i, contents.getStackInSlot(i));
                    }
                }

                // 3. Open the Menu
                serverPlayer.openMenu(new SimpleMenuProvider(
                        (containerId, playerInventory, p) -> new ChestMenu(MenuType.GENERIC_9x1, containerId, playerInventory, container, 1),
                        itemInHand.getHoverName()
                ));

                // 4. THE SAVE LOGIC: Listen for when the container changes
                container.addListener(c -> {
                    NonNullList<ItemStack> currentStacks = NonNullList.withSize(c.getContainerSize(), ItemStack.EMPTY);
                    for (int i = 0; i < c.getContainerSize(); i++) {
                        currentStacks.set(i, c.getItem(i));
                    }
                    itemInHand.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(currentStacks));
                });
            }
        }
        return InteractionResultHolder.sidedSuccess(itemInHand, level.isClientSide());
    }
}
