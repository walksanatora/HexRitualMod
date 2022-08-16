package com.walksanator.ritualhex.hexes.glpyhs

import at.petrak.hexcasting.api.misc.ManaConstants
import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.mishaps.MishapBadOffhandItem
import at.petrak.hexcasting.xplat.IXplatAbstractions
import com.walksanator.ritualhex.potion.ModEffects.SoulSafety
import net.minecraft.server.level.ServerPlayer
import net.minecraftforge.server.ServerLifecycleHooks
import kotlin.random.Random

object OpViolation : SpellOperator {
    override val argc = 0
    override val isGreat = true
    override fun execute(
        args: List<SpellDatum<*>>,
        ctx: CastingContext
    ): Triple<RenderedSpell, Int, List<ParticleSpray>> {

        val pl = ServerLifecycleHooks.getCurrentServer().playerList
        val allPlayers: List<ServerPlayer> = pl.players
        val players: MutableList<ServerPlayer> = mutableListOf()

        for (player in allPlayers) {
            if (!player.hasEffect(SoulSafety.get())) {
                players.add(player)
            }
        }
        val numPlayers = players.count()
        val datum = if (numPlayers > 0) {
            val rand = Random.nextInt(0, numPlayers)
            val poorSchmuck = players[rand]
            SpellDatum.make(poorSchmuck)
        } else {
            SpellDatum.make(Widget.NULL)
        }
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