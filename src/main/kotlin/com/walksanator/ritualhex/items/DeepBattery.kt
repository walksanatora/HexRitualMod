package com.walksanator.ritualhex.items

import at.petrak.hexcasting.api.item.ManaHolderItem
import at.petrak.hexcasting.api.misc.ManaConstants
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.nbt.CompoundTag
import kotlin.math.floor

//It is called Iolite
class DeepBattery(Props: Properties) : Item(Props),ManaHolderItem {

    //return 0 if not full, Int.MAX_VALUE if full, that way we can suck out the mose mana from an item stack
    override fun getMana(stack: ItemStack) : Int {
        val chargedCount = if (stack.hasTag()){
            stack.tag!!.getInt("ritualhex.chargedCount")
        } else { 0 }
        val manaRemainder = if (stack.hasTag()){
            stack.tag!!.getInt("ritualhex.manaRemainder")
        } else { 0 }

        //check if the amount of media in just the shards is greater than the Integer limit (it will loop over)
        return if (chargedCount < Int.MAX_VALUE) {
            0
        } else {
            Int.MAX_VALUE
        }
    }

    //always return Int.MAX_VALUE that way it can get as much media as possible
    override fun getMaxMana(items: ItemStack?): Int {
        return Int.MAX_VALUE
    }

    //take the p1 as the amount of mana extracted (which can be ~2k charged amethyst), and save that to our nbt
    //as a number of charged we have, and the remainder of mana that cannot be put into a single charged
    override fun setMana(stack: ItemStack, mediaAmmount: Int) {
        val chargedCount = if (stack.hasTag()){
            stack.tag!!.getInt("ritualhex.chargedCount")
        } else { 0 }
        val manaRemainder = if (stack.hasTag()){
            stack.tag!!.getInt("ritualhex.manaRemainder")
        } else { 0 }

        //get the remainder of charged amethyst and the number of charged stored
        val remainder: Int = (mediaAmmount % ManaConstants.SHARD_UNIT)
        val charged: Int =  floor(mediaAmmount.toDouble() / ManaConstants.SHARD_UNIT).toInt()

        //see if we had to get any more charged by converting up
        val convertUp = floor(remainder.toDouble() / ManaConstants.SHARD_UNIT).toInt()
        val realRemainder = (remainder % ManaConstants.SHARD_UNIT)

        //combine old and new values
        val newRemainder = realRemainder + manaRemainder
        val newCharged = charged + chargedCount + convertUp

        if (!stack.hasTag()) {
            stack.tag = CompoundTag()
        }

        stack.tag!!.putInt("ritualhex.chargedCount",newCharged)
        stack.tag!!.putInt("ritualhex.manaRemainder",newRemainder)
    }

    //returns true
    override fun manaProvider(stack: ItemStack): Boolean {
        return true
        /*
        val chargedCount = if (stack.hasTag()){
            stack.tag!!.getInt("ritualhex.chargedCount")
        } else { 0 }
        val manaRemainder = if (stack.hasTag()){
            stack.tag!!.getInt("ritualhex.manaRemainder")
        } else { 0 }
        return !((chargedCount == 0) and (manaRemainder == 0))
        */
    }

    //return true
    override fun canRecharge(stack: ItemStack?): Boolean {
        return true
    }

    //takes a stack (containing this item), the cost we are extracting, and a flag on if we are just kidding
    override fun withdrawMana(stack: ItemStack, cost: Int, simulate: Boolean): Int {
        val chargedCount = if (stack.hasTag()){
            stack.tag!!.getInt("ritualhex.chargedCount")
        } else { 0 }
        val manaRemainder = if (stack.hasTag()){
            stack.tag!!.getInt("ritualhex.manaRemainder")
        } else { 0 }

        //check if the amount of media in just the shards is greater than the Integer limit (it will loop over)
        val mp = if (chargedCount > (cost/ManaConstants.SHARD_UNIT) ) {
                cost
        } else {
            if ((chargedCount*ManaConstants.SHARD_UNIT) + manaRemainder < cost) {
                (chargedCount*ManaConstants.SHARD_UNIT) + manaRemainder
            } else {
                cost
            }
        }

        val newRemainder: Int
        var newCharged: Int

        if (manaRemainder >= mp) {
            newRemainder = manaRemainder - mp
            newCharged = chargedCount
        } else {
            val chargedConsumed = floor(mp.toDouble()/ManaConstants.SHARD_UNIT).toInt()
            newCharged = chargedCount - chargedConsumed
            if (manaRemainder > (mp % ManaConstants.SHARD_UNIT)) {
                newRemainder = manaRemainder - (mp % ManaConstants.SHARD_UNIT)
            } else {
                newCharged -= 1
                newRemainder = manaRemainder - (mp % ManaConstants.SHARD_UNIT) + ManaConstants.SHARD_UNIT
            }
        }

        if (!stack.hasTag()) {
            stack.tag = CompoundTag()
        }

        stack.tag!!.putInt("ritualhex.chargedCount",newCharged)
        stack.tag!!.putInt("ritualhex.manaRemainder",newRemainder)
        return mp
    }

    //it is SHINY
    override fun isFoil(stack: ItemStack): Boolean {
        return true
    }

}

