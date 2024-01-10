package net.danygames2014.backpacks.init;

import net.danygames2014.backpacks.item.BackpackItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class ItemListener {
    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    public static Item leather_backpack;
    public static Item iron_backpack;
    public static Item crappy_backpack;

    @EventListener
    public void registerItems(ItemRegistryEvent event){
        crappy_backpack = new BackpackItem(NAMESPACE.id("crappy_backpack"),1,1).setTranslationKey(NAMESPACE, "crappy_backpack");
        leather_backpack = new BackpackItem(NAMESPACE.id("leather_backpack"),2,9).setTranslationKey(NAMESPACE,"leather_backpack");
        iron_backpack = new BackpackItem(NAMESPACE.id("iron_backpack"),12,13).setTranslationKey(NAMESPACE, "iron_backpack");
    }
}
