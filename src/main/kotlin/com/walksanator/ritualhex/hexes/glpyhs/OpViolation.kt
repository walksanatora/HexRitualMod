package com.walksanator.ritualhex.hexes.glpyhs

import at.petrak.hexcasting.api.misc.ManaConstants
import at.petrak.hexcasting.api.spell.ParticleSpray
import at.petrak.hexcasting.api.spell.RenderedSpell
import at.petrak.hexcasting.api.spell.SpellDatum
import at.petrak.hexcasting.api.spell.SpellOperator
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.mishaps.MishapBadOffhandItem
import at.petrak.hexcasting.xplat.IXplatAbstractions
import net.minecraft.server.level.ServerPlayer
import net.minecraftforge.server.ServerLifecycleHooks
import org.apache.logging.log4j.LogManager
import kotlin.random.Random

object OpViolation : SpellOperator {
    override val argc = 0
    override val isGreat = true
    override fun execute(
        args: List<SpellDatum<*>>,
        ctx: CastingContext
    ): Triple<RenderedSpell, Int, List<ParticleSpray>> {
        val logger = LogManager.getLogger("ritualhex")

        val pl = ServerLifecycleHooks.getCurrentServer().playerList
        val players: List<ServerPlayer> = pl.players
        val numPlayers = players.count()
        val rand = Random.nextInt(0,numPlayers)
        val poorSchmuck = players[rand]
        val datum = SpellDatum.make(poorSchmuck)
        val (handStack, hand) = ctx.getHeldItemToOperateOn {
            val datumHolder = IXplatAbstractions.INSTANCE.findDataHolder(it)

            datumHolder != null && datumHolder.writeDatum(datum, true)
        }
        val datumHolder = IXplatAbstractions.INSTANCE.findDataHolder(handStack)
            ?: throw MishapBadOffhandItem.of(handStack, hand, "iota.write")
        if (!datumHolder.writeDatum(datum, true))
            throw MishapBadOffhandItem.of(handStack, hand, "iota.readonly", datum.display())
        return Triple(
            Spell(datum),
            ManaConstants.CRYSTAL_UNIT*5*pl.players.count(),
            listOf(ParticleSpray.cloud(ctx.caster.position(), 10.0)
            )
        )
    }
    private data class Spell(val datum: SpellDatum<*>) : RenderedSpell {
        override fun cast(ctx: CastingContext) {
            val (handStack) = ctx.getHeldItemToOperateOn {
                val datumHolder = IXplatAbstractions.INSTANCE.findDataHolder(it)

                datumHolder != null && datumHolder.writeDatum(datum, true)
            }

            val datumHolder = IXplatAbstractions.INSTANCE.findDataHolder(handStack)
            datumHolder?.writeDatum(datum, false)

            return
        }
    }
}