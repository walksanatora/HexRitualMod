package com.walksanator.ritualhex.items

import at.petrak.hexcasting.api.item.ManaHolderItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class DeepBattery(Props: Properties) : Item(Props),ManaHolderItem {

    override fun getMana(items: ItemStack) : Int {
        TODO("Not yet implemented")
    }

    override fun getMaxMana(items: ItemStack?): Int {
        TODO("Not yet implemented")
    }

    override fun setMana(p0: ItemStack?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun manaProvider(p0: ItemStack?): Boolean {
        TODO("Not yet implemented")
    }

    override fun canRecharge(p0: ItemStack?): Boolean {
        TODO("Not yet implemented")
    }
}