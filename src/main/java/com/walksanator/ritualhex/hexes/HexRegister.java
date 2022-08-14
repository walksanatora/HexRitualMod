package com.walksanator.ritualhex.hexes;

import com.walksanator.ritualhex.hexes.glpyhs.*;
import at.petrak.hexcasting.api.PatternRegistry;
import at.petrak.hexcasting.api.spell.math.HexPattern;
import at.petrak.hexcasting.api.spell.math.HexDir;

import net.minecraft.resources.ResourceLocation;

public class HexRegister {
    public static void registerPatterns() {
        try {
            //HexPattern(EAST qawqadwedaqedwadwedwawadqawdewwdw)
            PatternRegistry.mapPattern(
                    HexPattern.fromAngles(
                            "qawqadwedaqedwadwedwawadqawdewwdw", HexDir.EAST
                    ),
                    new ResourceLocation("ritualhex", "amogus"),
                    OpAmogus.INSTANCE, true
            );
            //HexPattern(SOUTH_WEST daqaaedw)
            PatternRegistry.mapPattern(
                    HexPattern.fromAngles(
                            "daqaaedw", HexDir.SOUTH_EAST
                    ),
                    new ResourceLocation("ritualhex","soul_violation"),
                    OpViolation.INSTANCE,true
            );
        } catch (PatternRegistry.RegisterPatternException exn) {
            exn.printStackTrace();
        }
    }
}
