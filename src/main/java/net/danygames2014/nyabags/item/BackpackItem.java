package net.danygames2014.nyabags.item;

import net.danygames2014.nyabags.NyaBags;
import net.danygames2014.nyabags.screen.BackpackScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
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
        BackpackScreenHandler screenHandler = new BackpackScreenHandler(player.inventory, stack);

        GuiHelper.openGUI(
                player,
                NyaBags.NAMESPACE.id("backpack"),
                screenHandler.backpackInventory,
                screenHandler,

                new Consumer<MessagePacket>() {
                    @Override
                    public void accept(MessagePacket messagePacket) {
                        for (int i = 0; i < player.inventory.size(); i++) {
                            if (player.inventory.getStack(i) != null && player.inventory.getStack(i) == stack) {
                                messagePacket.ints = new int[]{i};
                                return;
                            }
                        }

                        messagePacket.ints = new int[]{-1};
                    }
                }
        );
        
        return stack;
    }
}
