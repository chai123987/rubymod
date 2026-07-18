package net.tutorial.rubymod.item;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tutorial.rubymod.RubyMod;
import net.tutorial.rubymod.entity.ModEntities;

public class ModItems {


    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(
                    ForgeRegistries.ITEMS,
                    RubyMod.MOD_ID
            );


    public static final RegistryObject<Item> RUBY_ENDERMAN_SPAWN_EGG =
            ITEMS.register(
                    "ruby_enderman_spawn_egg",
                    () -> new SpawnEggItem(
                            ModEntities.RUBY_ENDERMAN.get(),
                            0x111111,
                            0x550055,
                            new Item.Properties()
                    )
            );


    public static final RegistryObject<Item> RUBY_GOLEM_SPAWN_EGG =
            ITEMS.register(
                    "ruby_golem_spawn_egg",
                    () -> new SpawnEggItem(
                            ModEntities.RUBY_GOLEM.get(),
                            0xff0000,
                            0x990000,
                            new Item.Properties()
                    )
            );


    public static final RegistryObject<Item> RUBY_CREEPER_SPAWN_EGG =
            ITEMS.register(
                    "ruby_creeper_spawn_egg",
                    () -> new SpawnEggItem(
                            ModEntities.RUBY_CREEPER.get(),
                            0x00ff00,
                            0x005500,
                            new Item.Properties()
                    )
            );


    public static final RegistryObject<Item> RUBY_SKELETON_SPAWN_EGG =
            ITEMS.register(
                    "ruby_skeleton_spawn_egg",
                    () -> new SpawnEggItem(
                            ModEntities.RUBY_SKELETON.get(),
                            0xffffff,
                            0x555555,
                            new Item.Properties()
                    )
            );


    public static final RegistryObject<Item> RUBY_SPIDER_SPAWN_EGG =
            ITEMS.register(
                    "ruby_spider_spawn_egg",
                    () -> new SpawnEggItem(
                            ModEntities.RUBY_SPIDER.get(),
                            0x222222,
                            0xff0000,
                            new Item.Properties()
                    )
            );


    public static final RegistryObject<Item> RUBY_EVOKER_SPAWN_EGG =
            ITEMS.register(
                    "ruby_evoker_spawn_egg",
                    () -> new SpawnEggItem(
                            ModEntities.RUBY_EVOKER.get(),
                            0x5555ff,
                            0xffffff,
                            new Item.Properties()
                    )
            );


    public static final RegistryObject<Item> RUBY_PILLAGER_SPAWN_EGG =
            ITEMS.register(
                    "ruby_pillager_spawn_egg",
                    () -> new SpawnEggItem(
                            ModEntities.RUBY_PILLAGER.get(),
                            0x333333,
                            0x777777,
                            new Item.Properties()
                    )
            );


    public static void addItemsToCreativeTab(
            BuildCreativeModeTabContentsEvent event
    ){

        if(event.getTabKey()== CreativeModeTabs.SPAWN_EGGS){

            event.accept(RUBY_ENDERMAN_SPAWN_EGG);
            event.accept(RUBY_GOLEM_SPAWN_EGG);
            event.accept(RUBY_CREEPER_SPAWN_EGG);
            event.accept(RUBY_SKELETON_SPAWN_EGG);
            event.accept(RUBY_SPIDER_SPAWN_EGG);
            event.accept(RUBY_EVOKER_SPAWN_EGG);
            event.accept(RUBY_PILLAGER_SPAWN_EGG);

        }
    }
}
