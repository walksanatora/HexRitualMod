package com.walksanator.ritualhex

import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist

import com.walksanator.ritualhex.hexes.HexRegister

import com.walksanator.ritualhex.potion.ModEffects
import com.walksanator.ritualhex.items.ItemRegister

/**
 * Main mod class. Should be an `object` declaration annotated with `@Mod`.
 * The modid should be declared in this object and should match the modId entry
 * in mods.toml.
 *
 * An example for blocks is in the `blocks` package of this mod.
 */
@Mod(RitualHex.ID)
object RitualHex {
    const val ID = "ritualhex"

    // the logger for our mod
    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        LOGGER.log(Level.INFO, "Hello world!")

        ModEffects.register(MOD_BUS)

        val obj = runForDist(
            clientTarget = {
                MOD_BUS.addListener(RitualHex::onClientSetup)
                Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(RitualHex::onServerSetup)
                "test"
            })
        println(obj)
        MOD_BUS.addListener(RitualHex::onCommonSetup)

        ItemRegister.register(MOD_BUS)

    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "RitualHex Server Client")
        HexRegister.registerPatterns()
    }

    private fun onCommonSetup(event: FMLCommonSetupEvent) {
        LOGGER.info("Common stuff")
        HexRegister.registerPatterns()
    }

    /**
     * Fired on the global Forge bus.
     */
    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.log(Level.INFO, "RitualHex Server Setup")
    }
}