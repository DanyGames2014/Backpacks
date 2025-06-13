package net.danygames2014.nyabags.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.option.KeyBinding;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import org.lwjgl.input.Keyboard;

@SuppressWarnings("unused")
public class KeyBindingListener {
    public static KeyBinding openBackpack;

    @EventListener
    public void registerKeys(KeyBindingRegisterEvent event) {
        event.keyBindings.add(openBackpack = new KeyBinding("key.backpacks:open_backpack", Keyboard.KEY_B));
    }
}
