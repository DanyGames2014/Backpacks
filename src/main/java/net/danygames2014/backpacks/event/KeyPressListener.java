package net.danygames2014.backpacks.event;

import net.danygames2014.backpacks.init.KeyBindingListener;
import net.danygames2014.backpacks.item.BackpackItem;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import org.lwjgl.input.Keyboard;

@SuppressWarnings("deprecation")
public class KeyPressListener {
    @EventListener
    public void handleKeyPress(KeyStateChangedEvent event) {
        Minecraft mc = ((Minecraft) FabricLoader.getInstance().getGameInstance());
        PlayerEntity player = mc.player;

        if (Keyboard.getEventKeyState() && mc.currentScreen == null) {
            if (Keyboard.isKeyDown(KeyBindingListener.openBackpack.code)) {
                for (int i = 0; i < player.inventory.main.length; i++) {
                    ItemStack stack = player.inventory.main[i];
                    if (stack != null) {
                        if (stack.getItem() instanceof BackpackItem){
                            stack.getItem().use(stack, player.world, player);
                            return;
                        }
                    }
                }
            }
        }
    }
}
