package me.walksanator.RitualHex;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(RitualHex.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RitualHex {
    public static final String MODID = "ritualhex";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RitualHex() {}

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        // do something when the setup is run on both client and server
        LOGGER.info("HELLO from common setup!");
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // do something when the setup is run on only the client
        LOGGER.info("HELLO from client setup!");
    }
}
