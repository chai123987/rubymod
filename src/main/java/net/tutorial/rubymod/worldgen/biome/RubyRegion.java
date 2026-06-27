package net.tutorial.rubymod.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

// TerraBlender 的核心：把红宝石群系按一组"气候坐标"塞进主世界。
// 这些 span(...) 范围大致对应温带、内陆的地块，权重在主类里注册时给。
public class RubyRegion extends Region {
    public RubyRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry,
                          Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        new ParameterUtils.ParameterPointListBuilder()
                .temperature(Climate.Parameter.span(-0.15F, 0.25F))
                .humidity(Climate.Parameter.span(-0.1F, 0.35F))
                .continentalness(Climate.Parameter.span(-0.11F, 0.55F))
                .erosion(Climate.Parameter.span(-1.0F, 1.0F))
                .depth(Climate.Parameter.point(0.0F))
                .weirdness(Climate.Parameter.span(-1.0F, 1.0F))
                .build()
                .forEach(point -> mapper.accept(Pair.of(point, ModBiomes.RUBY_BIOME)));
    }
}
