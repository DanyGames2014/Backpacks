package net.danygames2014.backpacks.inventory;

import net.danygames2014.backpacks.item.BackpackItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.modificationstation.stationapi.api.item.StationItemNbt;

public class BackpackInventory implements Inventory {
    public ItemStack[] inventory;
    public ItemStack stack;
    public int rows;
    public int columns;

    public BackpackInventory(ItemStack stack) {
        this.rows = ((BackpackItem) stack.getItem()).rows;
        this.columns = ((BackpackItem) stack.getItem()).columns;
        this.inventory = new ItemStack[this.rows * this.columns];
        this.stack = stack;
        readNbt(stack);
    }

    @Override
    public int size() {
        return this.rows * this.columns;
    }

    @Override
    public ItemStack getStack(int slot) {
        if(slot < inventory.length){
            return inventory[slot];
        }
        return null;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if(slot < inventory.length){
            if(inventory[slot] != null){
                ItemStack itemStack = null;

                if(amount >= inventory[slot].count){
                    itemStack = inventory[slot];
                    inventory[slot] = null;
                }else{
                    itemStack = inventory[slot].split(Math.min(64, amount));
                }

                return itemStack;
            }
        }

        return null;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if(stack != null && stack.count <= this.getMaxCountPerStack() && slot < inventory.length){
            inventory[slot] = stack;
        }
    }

    @Override
    public String getName() {
        return "Backpack";
    }

    @Override
    public void markDirty() {
        writeNbt(this.stack);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    public void writeNbt(ItemStack stack) {
        NbtCompound nbt = ((StationItemNbt) stack).getStationNbt();
        NbtList nbtList = new NbtList();

        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                continue;
            }
            NbtCompound slotNbt = new NbtCompound();
            slotNbt.putInt("slot", i);
            slotNbt.put("item", inventory[i].writeNbt(new NbtCompound()));
            nbtList.add(slotNbt);
        }
        nbt.put("Items", nbtList);
    }

    public void readNbt(ItemStack stack){
        this.inventory = new ItemStack[this.rows*this.columns];

        NbtCompound nbt = ((StationItemNbt) stack).getStationNbt();
        NbtList nbtList = nbt.getList("Items");

        for (int i = 0; i < nbtList.size(); i++) {
            NbtCompound slotNbt = (NbtCompound) nbtList.get(i);
            this.inventory[slotNbt.getInt("slot")] = new ItemStack(slotNbt.getCompound("item"));
        }
    }
}
