package net.danygames2014.nyabags.item;

import net.danygames2014.nyabags.NyaBags;
import net.danygames2014.nyabags.inventory.BackpackInventory;
import net.danygames2014.nyabags.screen.BackpackScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.item.StationItemNbt;
import net.modificationstation.stationapi.api.network.packet.MessagePacket;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.function.Consumer;

public class BackpackItem extends TemplateItem {
    public int rows;
    public int columns;

    public BackpackItem(Identifier identifier, int rows, int columns) {
        super(identifier);
        this.rows = rows;
        this.columns = columns;
        this.setMaxCount(1);
    }

    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity player) {
        if (player.isSneaking()) {
            player.sendMessage(((StationItemNbt) stack).getStationNbt().toString());
        } else {
            GuiHelper.openGUI(
                    player,
                    NyaBags.NAMESPACE.id("backpack"),
                    new BackpackInventory(stack),
                    new BackpackScreenHandler(player.inventory, stack),

                    new Consumer<MessagePacket>() {
                        @Override
                        public void accept(MessagePacket messagePacket) {
                        }
                    }
            );
        }
        return stack;
    }
}
