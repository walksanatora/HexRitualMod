package com.walksanator.ritualhex.items;

import com.walksanator.ritualhex.RitualHex;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraftforge.registries.ForgeRegistries.ITEMS;

public class ItemRegister {
    public static DeferredRegister<Item> ITEMS_REG = DeferredRegister.create(ITEMS,RitualHex.ID);
    public static final RegistryObject<Item> Deep_Battery = ITEMS_REG.register("deep_battery",
            () -> new DeepBattery(new Item.Properties().stacksTo(1).fireResistant())
    );

    public static final void register(IEventBus evBus) {
        ITEMS_REG.register(evBus);
    }
}
