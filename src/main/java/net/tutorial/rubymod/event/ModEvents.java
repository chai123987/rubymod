package net.tutorial.rubymod.event;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.fml.common.Mod;
import net.tutorial.rubymod.RubyMod;
import net.tutorial.rubymod.entity.ModEntities;
import net.tutorial.rubymod.entity.custom.RubyCreeperEntity;
import net.tutorial.rubymod.entity.custom.RubySkeletonEntity;
import net.tutorial.rubymod.entity.custom.RubyGolemEntity;
import net.tutorial.rubymod.entity.custom.AdamantineRubyGolemEntity;
import net.tutorial.rubymod.entity.custom.RubySpiderEntity;
import net.tutorial.rubymod.entity.custom.RubyEvokerEntity;
import net.tutorial.rubymod.entity.custom.RubyPillagerEntity;
import net.tutorial.rubymod.entity.custom.RubyPlayerEntity;
import net.tutorial.rubymod.entity.custom.ButterflyEntity;
import net.tutorial.rubymod.entity.custom.RubyEndermanEntity;
import net.tutorial.rubymod.item.ModItems;

@Mod.EventBusSubscriber(modid = RubyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.RUBY_GOLEM.get(), RubyGolemEntity.createAttributes().build());
        event.put(ModEntities.RUBY_CREEPER.get(), RubyCreeperEntity.createAttributes().build());
        event.put(ModEntities.RUBY_SKELETON.get(), RubySkeletonEntity.createAttributes().build());
        event.put(ModEntities.ADAMANTINE_RUBY_GOLEM.get(), AdamantineRubyGolemEntity.createAttributes().build());
        event.put(ModEntities.RUBY_SPIDER.get(), RubySpiderEntity.createAttributes().build());
        event.put(ModEntities.RUBY_EVOKER.get(), RubyEvokerEntity.createAttributes().build());
        event.put(ModEntities.RUBY_PILLAGER.get(), RubyPillagerEntity.createAttributes().build());
        event.put(ModEntities.RUBY_PLAYER.get(), RubyPlayerEntity.createAttributes().build());
        event.put(ModEntities.BUTTERFLY.get(), ButterflyEntity.createAttributes().build());
        event.put(ModEntities.RUBY_ENDERMAN.get(), RubyEndermanEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        // 怪物生成规则：在黑暗的地表生成
        event.register(ModEntities.RUBY_GOLEM.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                SpawnPlacementRegisterEvent.Operation.AND);

        event.register(ModEntities.RUBY_CREEPER.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                SpawnPlacementRegisterEvent.Operation.AND);

        event.register(ModEntities.RUBY_SKELETON.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                SpawnPlacementRegisterEvent.Operation.AND);

        event.register(ModEntities.RUBY_SPIDER.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                SpawnPlacementRegisterEvent.Operation.AND);

        event.register(ModEntities.RUBY_EVOKER.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                SpawnPlacementRegisterEvent.Operation.AND);

        event.register(ModEntities.RUBY_PILLAGER.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                SpawnPlacementRegisterEvent.Operation.AND);
        // 注意：红宝石玩家是BOSS，只能用刷怪蛋放，不注册自然生成

        // 蝴蝶：像动物一样在地面、明亮处生成
        event.register(ModEntities.BUTTERFLY.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules,
                SpawnPlacementRegisterEvent.Operation.AND);

        event.register(ModEntities.RUBY_ENDERMAN.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                SpawnPlacementRegisterEvent.Operation.AND);
    }

    // 把蝴蝶和红宝石末影人刷怪蛋放进【原版的"刷怪蛋"创造栏】
    @SubscribeEvent
    public static void addSpawnEggToVanillaTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.BUTTERFLY_SPAWN_EGG.get());
            event.accept(ModItems.RUBY_ENDERMAN_SPAWN_EGG.get());
        }
    }
}
