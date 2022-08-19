package com.walksanator.ritualhex.hexes;

import at.petrak.hexcasting.api.PatternRegistry;
import at.petrak.hexcasting.api.misc.ManaConstants;
import at.petrak.hexcasting.api.spell.math.HexDir;
import at.petrak.hexcasting.api.spell.math.HexPattern;
import at.petrak.hexcasting.common.casting.operators.spells.OpPotionEffect;
import com.walksanator.ritualhex.hexes.glpyhs.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;

import static com.walksanator.ritualhex.potion.ModEffects.SoulSafety;

public class HexRegister {
    public static void registerPatterns() {
        try {
            //HexPattern(NORTH_EAST awqawdwwedaqedwadweqewwd), greater amogus
            PatternRegistry.mapPattern(
                    HexPattern.fromAngles(
                            "awqawdwwedaqedwadweqewwd", HexDir.NORTH_EAST
                    ),
                    new ResourceLocation("ritualhex", "amogus"),
                    OpAmogus.INSTANCE, true
            );
            //HexPattern(SOUTH_WEST daqaaedw), greater identity theft
            PatternRegistry.mapPattern(
                    HexPattern.fromAngles(
                            "daqaaedw", HexDir.SOUTH_EAST
                    ),
                    new ResourceLocation("ritualhex","soul_violation"),
                    OpViolation.INSTANCE,true
            );
            //HexPattern(SOUTH_WEST wawawwaqqqqa), zenith of prevent identity theft
            PatternRegistry.mapPattern(
                    HexPattern.fromAngles("qqqqqwaqawawa",HexDir.EAST),
                    new ResourceLocation("ritualhex","soul_protection"),
                    new OpPotionEffect(SoulSafety.get(), ManaConstants.SHARD_UNIT*15,false,false,true),
                    true
            );
            //TODO: Implement missing nadirs/zeniths
            //TODO: Missing zeniths: ,Water Breathing,Saturation,Luck,Slow falling,Conduit Power,Dolphins Grace,Hero Of The Village?
            //TODO: Missing nadirs: Glowing,Mining Fatigue,Nausea,Hunger,Bad Luck,Bad Omen
            //HexPattern(EAST qqqqqeawawa), Speed zenith
            PatternRegistry.mapPattern(
                    HexPattern.fromAngles("qqqqqeawawa",HexDir.EAST),
                    new ResourceLocation("ritualhex","potion/speed"),
                    new OpLimitedPotion(MobEffects.MOVEMENT_SPEED,ManaConstants.CRYSTAL_UNIT*3,true,true,1,64,999999999,true),
                    true
            );
            //HexPattern(EAST aaddaqqqqedwdw), Invisibility zenith
            PatternRegistry.mapPattern(
                    HexPattern.fromAngles("aaddaqqqqedwdw",HexDir.EAST),
                    new ResourceLocation("ritualhex","potion/invisibility"),
                    new OpPotionEffect(MobEffects.INVISIBILITY,ManaConstants.CRYSTAL_UNIT,false,true,true),
                    true
            );
            //HexPattern(NORTH_EAST waqaaqqqqedwdwd), Jump Boost zenith
            PatternRegistry.mapPattern(
                    HexPattern.fromAngles("waqaaqqqqedwdwd",HexDir.NORTH_EAST),
                    new ResourceLocation("ritualhex","potion/jump_boost"),
                    new OpPotionEffect(MobEffects.JUMP,ManaConstants.CRYSTAL_UNIT,true,true,true),
                    true
            );
            //HexPattern(North_East dedaqqqq), Fire resistance zenith
            PatternRegistry.mapPattern(
                    HexPattern.fromAngles("dedaqqqq",HexDir.NORTH_EAST),
                    new ResourceLocation("ritualhex","potion/fire_resistance"),
                    new OpPotionEffect(MobEffects.FIRE_RESISTANCE,ManaConstants.CRYSTAL_UNIT*2,false,false,true),
                    true
            );
            //HexPattern(SOUTH_WEST qqaw)
            PatternRegistry.mapPattern(
                    HexPattern.fromAngles("qqaw",HexDir.SOUTH_EAST),
                    new ResourceLocation("ritualhex","page/right"),
                    new OpPageTurn(true),false
            );
            //HexPattern(SOUTH_EAST wdee)
            PatternRegistry.mapPattern(
                    HexPattern.fromAngles("wdee",HexDir.SOUTH_EAST),
                    new ResourceLocation("ritualhex","page/left"),
                    new OpPageTurn(false), false
            );
        } catch (PatternRegistry.RegisterPatternException exn) {
            exn.printStackTrace();
        }
    }
}
