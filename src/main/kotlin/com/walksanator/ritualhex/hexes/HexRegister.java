package com.walksanator.ritualhex.hexes;

import com.walksanator.ritualhex.hexes.glpyhs.OpAmogus;
import at.petrak.hexcasting.api.PatternRegistry;
import at.petrak.hexcasting.api.spell.math.HexPattern;
import at.petrak.hexcasting.api.spell.math.HexDir;

import net.minecraft.resources.ResourceLocation;

public class HexRegister {
    public static void registerPatterns() {
        try {
            //HexPattern(EAST qawqadwedaqedwadweqewwdw)
            PatternRegistry.mapPattern(
                    HexPattern.fromAngles(
                            "qawqadwedaqedwadweqewwdw",
                            HexDir.EAST),
                    new ResourceLocation("ritualhex", "amogus"),
                    OpAmogus.INSTANCE, false
            );
        } catch (PatternRegistry.RegisterPatternException exn) {
            exn.printStackTrace();
        }
    }
}
