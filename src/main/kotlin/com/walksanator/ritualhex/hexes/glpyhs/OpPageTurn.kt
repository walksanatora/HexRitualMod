package com.walksanator.ritualhex.hexes.glpyhs

import at.petrak.hexcasting.api.spell.ConstManaOperator
import at.petrak.hexcasting.api.spell.SpellDatum
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.mishaps.MishapBadOffhandItem
import at.petrak.hexcasting.xplat.IXplatAbstractions
import at.petrak.hexcasting.common.lib.HexItems
import net.minecraft.network.chat.TextComponent
import at.petrak.hexcasting.common.items.ItemSpellbook


class OpPageTurn(
        private val rotateRight: Boolean
    ) : ConstManaOperator {
    override val argc = 0
    override val isGreat = false

    override fun execute(args: List<SpellDatum<*>>, ctx: CastingContext): List<SpellDatum<*>> {
        val (handStack, hand) = ctx.getHeldItemToOperateOn {
            val dataHolder = IXplatAbstractions.INSTANCE.findDataHolder(it)
            dataHolder != null && (dataHolder.readDatum(ctx.world) != null || dataHolder.emptyDatum() != null)
        }

        if (handStack.`is`(HexItems.SPELLBOOK.asItem())) {
            ItemSpellbook.RotatePageIdx(handStack,rotateRight)
        } else {
            throw MishapBadOffhandItem(handStack,hand,TextComponent("Spellbook"))
        }

        return listOf()
    }

}