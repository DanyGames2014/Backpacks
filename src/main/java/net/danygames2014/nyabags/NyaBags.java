package net.danygames2014.nyabags;

import net.danygames2014.nyabags.inventory.BackpackInventory;
import net.danygames2014.nyabags.screen.BackpackScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.modificationstation.stationapi.api.client.gui.screen.GuiHandler;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.network.packet.MessagePacket;
import net.modificationstation.stationapi.api.util.Namespace;

public class NyaBags {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE;

    @EventListener
    public void registerScreenHandlers(GuiHandlerRegistryEvent event) {
        event.register(NAMESPACE.id("backpack"), new GuiHandler((GuiHandler.ScreenFactory) this::openBackpack, BackpackInventory::new));
    }

    @Environment(EnvType.CLIENT)
    private Screen openBackpack(PlayerEntity playerEntity, Inventory inventory, MessagePacket messagePacket) {
        BackpackInventory backpackInventory;
        if (playerEntity.world.isRemote) {
            backpackInventory = null;
        } else {
            backpackInventory = (BackpackInventory) inventory;
        }

        return new BackpackScreen(playerEntity, backpackInventory);
    }
}
