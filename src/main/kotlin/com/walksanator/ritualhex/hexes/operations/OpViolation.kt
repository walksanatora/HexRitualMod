package com.walksanator.ritualhex.hexes.operations

import at.petrak.hexcasting.api.misc.ManaConstants
import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.mishaps.MishapBadOffhandItem
import at.petrak.hexcasting.xplat.IXplatAbstractions
import com.walksanator.ritualhex.potion.ModEffects.SoulSafety
import net.minecraft.network.chat.Style
import net.minecraft.network.chat.TextComponent
import net.minecraft.server.level.ServerPlayer
import net.minecraftforge.server.ServerLifecycleHooks
import kotlin.random.Random

//TODO: add a sort of arms race by allowing the potion effect to be given a potency and allow a sort of power conflict
object OpViolation : SpellOperator {
    override val argc = 0
    override val isGreat = true
    override fun execute(
        args: List<SpellDatum<*>>,
        ctx: CastingContext
    ): Triple<RenderedSpell, Int, List<ParticleSpray>> {

        val server = ServerLifecycleHooks.getCurrentServer()
        val pl = server.playerList
        val allPlayers: List<ServerPlayer> = pl.players
        val players: MutableList<ServerPlayer> = mutableListOf()

        for (player in allPlayers) {
            //TODO: get the effect and see if we outclass it in terms of power
            //https://nekoyue.github.io/ForgeJavaDocs-NG/javadoc/1.18.2/net/minecraft/world/entity/LivingEntity.html#getEffect(net.minecraft.world.effect.MobEffect)
            if (!player.hasEffect(SoulSafety.get())) {
                players.add(player)
            }
        }
        val numPlayers = players.count()
        val datum = if (numPlayers > 0) {
            val rand = Random.nextInt(0, numPlayers)
            val poorSchmuck = players[rand]
            val message = TextComponent("your soul has been stolen by "+ ctx.caster.name.contents)
            message.style = Style.EMPTY.withColor(0xAA4A44)
            poorSchmuck.sendMessage(message,poorSchmuck.uuid)
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