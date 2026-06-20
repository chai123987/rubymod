package net.tutorial.rubymod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
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
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RubyMod.MOD_ID);

    // 红宝石
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties()));

    // 红宝石剑（用铁等级）
    public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword",
            () -> new SwordItem(Tiers.IRON, 3, -2.4F, new Item.Properties()));

    // 红宝石镐（自定义红宝石等级：速度10、耐久2000）
    public static final RegistryObject<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe",
            () -> new PickaxeItem(ModToolTiers.RUBY, 1, -2.8F, new Item.Properties()));

    // 红宝石傀儡刷怪蛋
    public static final RegistryObject<Item> RUBY_GOLEM_SPAWN_EGG = ITEMS.register("ruby_golem_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.RUBY_GOLEM, 0xB0171F, 0x7A0C14,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
