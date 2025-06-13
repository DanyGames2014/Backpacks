package net.danygames2014.nyabags.init;

import net.danygames2014.nyabags.item.BackpackItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;

public class ItemListener {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE;

    public static Item leatherBackpack;
    public static Item ironBackpack;
    public static Item goldBackpack;
    public static Item diamondBackpack;

    @EventListener
    public void registerItems(ItemRegistryEvent event){
        leatherBackpack = new BackpackItem(NAMESPACE.id("leather_backpack"),2,9).setTranslationKey(NAMESPACE,"leather_backpack");
        ironBackpack = new BackpackItem(NAMESPACE.id("iron_backpack"),4,9).setTranslationKey(NAMESPACE, "iron_backpack");
        goldBackpack = new BackpackItem(NAMESPACE.id("gold_backpack"), 6, 9).setTranslationKey(NAMESPACE, "gold_backpack");
        diamondBackpack = new BackpackItem(NAMESPACE.id("diamond_backpack"), 7, 11).setTranslationKey(NAMESPACE, "diamond_backpack");
    }
}
