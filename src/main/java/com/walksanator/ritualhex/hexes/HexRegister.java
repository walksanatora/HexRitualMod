package com.walksanator.ritualhex.hexes;

import at.petrak.hexcasting.api.misc.ManaConstants;
import at.petrak.hexcasting.common.casting.operators.spells.OpPotionEffect;
import com.walksanator.ritualhex.hexes.glpyhs.*;
import at.petrak.hexcasting.api.PatternRegistry;
import at.petrak.hexcasting.api.spell.math.HexPattern;
import at.petrak.hexcasting.api.spell.math.HexDir;

import static com.walksanator.ritualhex.potion.ModEffects.SoulSafety;
import net.minecraft.resources.ResourceLocation;

public class HexRegister {
    public static void registerPatterns() {
        try {
            //HexPattern(NORTH_EAST awqawdwwedaqedwadweqewwd)
            PatternRegistry.mapPattern(
                    HexPattern.fromAngles(
                            "awqawdwwedaqedwadweqewwd", HexDir.NORTH_EAST
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
            //HexPattern(SOUTH_WEST wawawwaqqqqa)
            PatternRegistry.mapPattern(
                    HexPattern.fromAngles("wawawwaqqqqa",HexDir.SOUTH_EAST),
                    new ResourceLocation("ritualhex","soul_protection"),
                    new OpPotionEffect(SoulSafety.get(), ManaConstants.SHARD_UNIT*15,false,true,true),
                    true
            );
        } catch (PatternRegistry.RegisterPatternException exn) {
            exn.printStackTrace();
        }
    }
}
