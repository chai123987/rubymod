package net.tutorial.rubymod.worldgen.biome;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.tutorial.rubymod.block.ModBlocks;

// 决定红宝石群系"地面铺什么"：顶层红宝石草方块，下面泥土。
public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource RUBY_GRASS =
            makeStateRule(ModBlocks.RUBY_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        // 在地表那一层、且在水面及以上 → 铺红宝石草；否则铺泥土
        SurfaceRules.RuleSource grassThenDirt = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, RUBY_GRASS)),
                DIRT);

        // 只在红宝石群系里套用上面这套地表
        return SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.RUBY_BIOME),
                grassThenDirt);
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
