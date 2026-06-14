package net.tutorial.rubymod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tutorial.rubymod.RubyMod;
import net.tutorial.rubymod.entity.ModEntities;

public class ModItems {
    // 所有“物品”类型的统一注册器
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RubyMod.MOD_ID);

    // 1) 普通材料物品：红宝石
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties()));

    // 2) 一把用红宝石做的剑（这里直接用铁的工具等级，简单稳妥）
    //    想要自定义等级，可以实现 Tier 接口后替换 Tiers.IRON。
    public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword",
            () -> new SwordItem(Tiers.IRON, 3, -2.4F, new Item.Properties()));

    // 3) 生物的刷怪蛋（Forge 提供的 ForgeSpawnEggItem 更方便）
    public static final RegistryObject<Item> RUBY_GOLEM_SPAWN_EGG = ITEMS.register("ruby_golem_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.RUBY_GOLEM, 0xB0171F, 0x7A0C14,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
