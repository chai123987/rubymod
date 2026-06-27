package net.tutorial.rubymod;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tutorial.rubymod.block.ModBlocks;
import net.tutorial.rubymod.entity.ModEntities;
import net.tutorial.rubymod.item.ModCreativeModeTabs;
import net.tutorial.rubymod.item.ModItems;
import net.tutorial.rubymod.item.ModToolTiers;
import net.tutorial.rubymod.worldgen.biome.ModSurfaceRules;
import net.tutorial.rubymod.worldgen.biome.RubyRegion;
import org.slf4j.Logger;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import java.util.List;

@Mod(RubyMod.MOD_ID)
public class RubyMod {
    public static final String MOD_ID = "rubymod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RubyMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // 把红宝石等级排在钻石之后、下界合金之前，挖掘判定才正确
            TierSortingRegistry.registerTier(
                    ModToolTiers.RUBY,
                    new ResourceLocation(MOD_ID, "ruby"),
                    List.of(Tiers.DIAMOND),
                    List.of(Tiers.NETHERITE)
            );

            // 注册红宝石群系（权重 2，比之前更低 → 整体更少见；配合收窄的气候参数，成大块出现）
            Regions.register(new RubyRegion(new ResourceLocation(MOD_ID, "ruby_region"), 2));
            // 注册红宝石群系的地表规则（红宝石草 + 泥土）
            SurfaceRuleManager.addSurfaceRules(
                    SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
        });
        LOGGER.info("RubyMod common setup complete");
    }
}
