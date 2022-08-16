package com.walksanator.ritualhex.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SoulSafety extends MobEffect {
    public SoulSafety(MobEffectCategory mef, int color) {
        super(mef,color);
    }

    @Override
    public void applyEffectTick(LivingEntity targetEntity, int amplifier) {
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
