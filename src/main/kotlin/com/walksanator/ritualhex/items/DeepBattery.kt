package com.walksanator.ritualhex.items

import at.petrak.hexcasting.api.item.ManaHolderItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

//It is called Iolite
class DeepBattery(Props: Properties) : Item(Props),ManaHolderItem {

    //return 0 if not full, Int.MAX_VALUE if full, that way we can suck out the mose mana from an item stack
    override fun getMana(items: ItemStack) : Int {
        TODO("Not yet implemented")
    }

    //always return Int.MAX_VALUE that way it can get as much media as possible
    override fun getMaxMana(items: ItemStack?): Int {
        return Int.MAX_VALUE
    }

    //take the p1 as the amount of mana extracted (which can be ~2k charged amethyst), and save that to our nbt
    //as a number of charged we have, and the remainder of mana that cannot be put into a single charged
    override fun setMana(p0: ItemStack?, p1: Int) {
        TODO("Not yet implemented")
    }

    //return true
    override fun manaProvider(stack: ItemStack?): Boolean {
        TODO("Not yet implemented")
    }

    //return true
    override fun canRecharge(stack: ItemStack?): Boolean {
        TODO("Not yet implemented")
    }

    //it is SHINY
    override fun isFoil(stack: ItemStack): Boolean {
        return true
    }
}