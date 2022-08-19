package com.walksanator.ritualhex.items

import at.petrak.hexcasting.api.item.ManaHolderItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import at.petrak.hexcasting.api.utils.*

//It is called Iolite
class DeepBattery(Props: Properties) : Item(Props),ManaHolderItem {

    //return 0 if not full, Int.MAX_VALUE if full, that way we can suck out the mose mana from an item stack
    override fun getMana(stack: ItemStack) : Int {
        val mp = stack.getLong("ritualhex.media")

        //check if the amount of media in just the shards is greater than the Integer limit (it will loop over)
        return mp.coerceIn(0L,Int.MAX_VALUE.toLong()).toInt()
    }

    //always return Int.MAX_VALUE that way it can get as much media as possible
    override fun getMaxMana(items: ItemStack?): Int {
        return Int.MAX_VALUE
    }

    //returns true
    override fun manaProvider(stack: ItemStack): Boolean {
        return true
    }

    //return true
    override fun canRecharge(stack: ItemStack?): Boolean {
        return true
    }

    //as a number of charged we have, and the remainder of mana that cannot be put into a single charged
    override fun setMana(stack: ItemStack, mediaAmmount: Int) {
        stack.putLong("ritualhex.media",mediaAmmount.toLong())
    }

    override fun insertMana(stack: ItemStack?, amount: Int, simulate: Boolean): Int {
        if (amount<0){if (!simulate){stack!!.putLong("ritualhex.media",Long.MAX_VALUE)};return amount}
        val mp = stack!!.getLong("ritualhex.media")
        val taken = if ((amount.toLong()+mp)<mp) {
            Long.MAX_VALUE - mp
        } else {
            amount.toLong()
        }
        if (!simulate){stack.putLong("ritualhex.media",mp+taken)}
        return amount - taken.toInt()
    }

    //takes a stack (containing this item), the cost we are extracting, and a flag on if we are just kidding
    override fun withdrawMana(stack: ItemStack, cost: Int, simulate: Boolean): Int {
        val mp = stack.getLong("ritualhex.media")

        if (cost < 0) {
            if (!simulate) {stack.putLong("ritualhex.media",0L)}
            return mp.coerceIn(0L,Int.MAX_VALUE.toLong()).toInt()
        }

        val r = if (mp >= cost){
            val nmp = mp - cost.toLong()
            if (!simulate) {stack.putLong("ritualhex.media",nmp)}
            cost
        } else {
            if (!simulate) {stack.putLong("ritualhex.media",0)}
            mp.coerceIn(0L,Int.MAX_VALUE.toLong()).toInt()
        }
        return r
    }

    //it is SHINY
    override fun isFoil(stack: ItemStack): Boolean {
        return true
    }

}

