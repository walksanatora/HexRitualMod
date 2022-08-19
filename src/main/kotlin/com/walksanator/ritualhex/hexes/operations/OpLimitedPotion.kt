package com.walksanator.ritualhex.hexes.operations

import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.decoration.ArmorStand
import kotlin.math.max

class OpLimitedPotion(
    private val effect: MobEffect,
    private val baseCost: Int,
    private val allowPotency: Boolean,
    private val potencyCubic: Boolean,
    private val minPotency: Int,
    private val maxPotency: Int,
    private val maxTime: Int,
    override val isGreat: Boolean,
) : SpellOperator {
    override val argc: Int
        get() = if (this.allowPotency) 3 else 2

    override fun execute(
        args: List<SpellDatum<*>>,
        ctx: CastingContext
    ): Triple<RenderedSpell, Int, List<ParticleSpray>> {
        val target = args.getChecked<LivingEntity>(0, argc)
        if (target is ArmorStand)
            throw MishapInvalidIota.ofClass(SpellDatum.make(target), 0, LivingEntity::class.java)

        val duration = max(args.getChecked(1, argc), 0.0)

        val potency = if (this.allowPotency)
            args.getChecked<Double>(2, argc).coerceIn(minPotency.toDouble(), maxPotency.toDouble())
        else
            minPotency.toDouble()

        ctx.assertEntityInRange(target)

        val cost = this.baseCost * duration * if (potencyCubic) {
            potency * potency * potency
        } else {
            potency * potency
        }
        return Triple(
            Spell(effect, target, duration, potency,maxTime),
            cost.toInt(),
            listOf(ParticleSpray.cloud(target.position().add(0.0, target.eyeHeight / 2.0, 0.0), 1.0))
        )
    }

    private class Spell(val effect: MobEffect, val target: LivingEntity, val duration: Double, val potency: Double,val maxTime: Int) :
        RenderedSpell {
        override fun cast(ctx: CastingContext) {
            if (duration > 1.0 / 20.0) {
                val effectInst = MobEffectInstance(effect, (duration * 20).toInt().coerceIn(1,maxTime), potency.toInt() - 1)
                target.addEffect(effectInst)
            }
        }
    }
}
