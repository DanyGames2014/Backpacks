package net.danygames2014.nyabags.screen;

import net.danygames2014.nyabags.inventory.BackpackInventory;
import net.danygames2014.nyabags.inventory.slot.BackpackSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class BackpackScreenHandler extends ScreenHandler {
    Inventory playerInventory;
    BackpackInventory backpackInventory;
    ItemStack backpackStack;

    public BackpackScreenHandler(Inventory playerInventory, ItemStack stack) {
        this.playerInventory = playerInventory;
        this.backpackStack = stack;
        this.backpackInventory = new BackpackInventory(stack);

        int playerInventoryVerticalOffset = 34 + (backpackInventory.rows * 18); // 34 = 17(Backpack Top Bar) + 3(Visible Backpack Bottom Part) + 14 (Player Inventory Top Bar)
        int playerInventoryHorizontalOffset = (Math.max(176, 14 + (backpackInventory.columns * 18)) - 176) / 2;

        int row;
        int column;

        // Backpack Slots
        for(row = 0; row < backpackInventory.rows; row++) {
            for(column = 0; column < backpackInventory.columns; column++) {
                this.addSlot(new BackpackSlot(
                        backpackInventory,
                        column + (row * backpackInventory.columns),
                        8 + column * 18,
                        18 + row * 18
                    )
                );
            }
        }

        // Player Inventory
        for(row = 0; row < 3; row++) {
            for(column = 0; column < 9; column++) {
                this.addSlot(new Slot(playerInventory,
                        column + (row * 9) + 9,
                        playerInventoryHorizontalOffset + 8 + (column * 18),
                        playerInventoryVerticalOffset + (row * 18)
                    )
                );
            }
        }

        // Player Hotbar
        for(row = 0; row < 9; row++) {
            this.addSlot(new Slot(playerInventory,
                    row,
                    playerInventoryHorizontalOffset + 8 + (row * 18),
                    playerInventoryVerticalOffset + 58 // 58 = ( 3(Inventory Rows) * 18(Slot Size) ) + 4(Bar between Inventory and Hotbar Slots)
                )
            );
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return backpackInventory.canPlayerUse(player);
    }
}
