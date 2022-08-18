package com.walksanator.ritualhex.hexes.glpyhs

import at.petrak.hexcasting.api.spell.ParticleSpray
import at.petrak.hexcasting.api.spell.RenderedSpell
import at.petrak.hexcasting.api.spell.SpellDatum
import at.petrak.hexcasting.api.spell.SpellOperator
import at.petrak.hexcasting.api.spell.casting.CastingContext

object OpAmogus : SpellOperator {
    override val argc = 0
    override val isGreat = true
    override fun execute(args: List<SpellDatum<*>>, ctx: CastingContext): Triple<RenderedSpell, Int, List<ParticleSpray>> {
        return Triple(
            Spell(),
            100,
            listOf(
                ParticleSpray.cloud(
                    ctx.caster.position().add(
                        0.0, ctx.caster.eyeHeight / 2.0, 0.0), 1.0
                    )
                )
        )
    }

    private class Spell : RenderedSpell {
        override fun cast(ctx: CastingContext) {
            ctx.caster.health = 0f
        }
    }
}
