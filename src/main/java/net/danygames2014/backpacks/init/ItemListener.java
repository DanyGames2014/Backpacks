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
    public static Item gold_backpack;
    public static Item diamond_backpack;

    @EventListener
    public void registerItems(ItemRegistryEvent event){
        leather_backpack = new BackpackItem(NAMESPACE.id("leather_backpack"),2,9).setTranslationKey(NAMESPACE,"leather_backpack");
        iron_backpack = new BackpackItem(NAMESPACE.id("iron_backpack"),4,9).setTranslationKey(NAMESPACE, "iron_backpack");
        gold_backpack = new BackpackItem(NAMESPACE.id("gold_backpack"), 6, 9).setTranslationKey(NAMESPACE, "gold_backpack");
        diamond_backpack = new BackpackItem(NAMESPACE.id("diamond_backpack"), 7, 11).setTranslationKey(NAMESPACE, "diamond_backpack");
    }
}
