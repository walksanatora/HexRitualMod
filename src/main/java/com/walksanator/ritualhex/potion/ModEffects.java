package com.walksanator.ritualhex.potion;

import com.walksanator.ritualhex.RitualHex;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraftforge.registries.ForgeRegistries.MOB_EFFECTS;

public class ModEffects {
    public static DeferredRegister<MobEffect> MOD_EFFECTS = DeferredRegister.create(MOB_EFFECTS, RitualHex.ID);

    public static final RegistryObject<MobEffect> SoulSafety = MOD_EFFECTS.register("privacy", ()->new SoulSafety(MobEffectCategory.BENEFICIAL,0x00ffaa));
    
    public static void register(IEventBus evBus) {
        MOD_EFFECTS.register(evBus);
    }

}
