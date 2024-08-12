package net.danygames2014.backpacks.item;

import net.danygames2014.backpacks.screen.BackpackScreen;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.item.StationItemNbt;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class BackpackItem extends TemplateItem {
    public int rows;
    public int columns;

    public BackpackItem(Identifier identifier, int rows, int columns) {
        super(identifier);
        this.rows = rows;
        this.columns = columns;
        this.setMaxCount(1);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity player) {
        if (player.isSneaking()) {
            player.method_490(((StationItemNbt) stack).getStationNbt().toString());
        } else {
            ((Minecraft) FabricLoader.getInstance().getGameInstance()).setScreen(new BackpackScreen(player, stack));
        }
        return stack;
    }
}
