package net.tutorial.rubymod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tutorial.rubymod.block.ModBlocks;
import net.tutorial.rubymod.entity.ModEntities;
import net.tutorial.rubymod.item.ModCreativeModeTabs;
import net.tutorial.rubymod.item.ModItems;
import org.slf4j.Logger;

// modid 必须和 gradle.properties / mods.toml 中保持一致
@Mod(RubyMod.MOD_ID)
public class RubyMod {
    public static final String MOD_ID = "rubymod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RubyMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // 注册所有 DeferredRegister 到模组事件总线
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        // 客户端渲染相关的注册放在 ModClientEvents（仅客户端加载），更安全
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("RubyMod common setup complete");
    }
}
