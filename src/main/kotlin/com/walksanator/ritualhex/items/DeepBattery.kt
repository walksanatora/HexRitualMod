package com.walksanator.ritualhex.items

import at.petrak.hexcasting.api.item.ManaHolderItem
import at.petrak.hexcasting.api.misc.ManaConstants
import net.minecraft.nbt.CompoundTag
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

    //take the p1 as the amount of mana extracted (which can be ~2k charged amethyst), and save that to our nbt
    //as a number of charged we have, and the remainder of mana that cannot be put into a single charged
    override fun setMana(stack: ItemStack, mediaAmmount: Int) {
        stack.putLong("ritualhex.media",mediaAmmount.toLong())
    }

    //returns true
    override fun manaProvider(stack: ItemStack): Boolean {
        return true
    }

    //return true
    override fun canRecharge(stack: ItemStack?): Boolean {
        return true
    }

    //takes a stack (containing this item), the cost we are extracting, and a flag on if we are just kidding
    override fun withdrawMana(stack: ItemStack, cost: Int, simulate: Boolean): Int {
        val mp = stack.getLong("ritualhex.media")

        return if (mp >= cost){
            val nmp = mp - cost.toLong()
            if (!simulate) {stack.putLong("ritualhex.media",nmp)}
            cost
        } else {
            if (!simulate) {stack.putLong("ritualhex.media",0)}
            mp.toInt()
        }
    }

    //it is SHINY
    override fun isFoil(stack: ItemStack): Boolean {
        return true
    }

}

