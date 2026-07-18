package net.tutorial.rubymod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tutorial.rubymod.RubyMod;
import net.tutorial.rubymod.entity.custom.*;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RubyMod.MOD_ID);


    public static final RegistryObject<EntityType<RubyGolemEntity>> RUBY_GOLEM =
            ENTITY_TYPES.register("ruby_golem",
                    () -> EntityType.Builder.of(
                            RubyGolemEntity::new,
                            MobCategory.MONSTER)
                            .sized(0.6f,1.95f)
                            .fireImmune()
                            .build("ruby_golem"));


    public static final RegistryObject<EntityType<RubyCreeperEntity>> RUBY_CREEPER =
            ENTITY_TYPES.register("ruby_creeper",
                    () -> EntityType.Builder.of(
                            RubyCreeperEntity::new,
                            MobCategory.MONSTER)
                            .sized(0.6f,1.7f)
                            .build("ruby_creeper"));


    public static final RegistryObject<EntityType<RubySkeletonEntity>> RUBY_SKELETON =
            ENTITY_TYPES.register("ruby_skeleton",
                    () -> EntityType.Builder.of(
                            RubySkeletonEntity::new,
                            MobCategory.MONSTER)
                            .sized(0.6f,1.99f)
                            .fireImmune()
                            .build("ruby_skeleton"));


    public static final RegistryObject<EntityType<AdamantineRubyGolemEntity>> ADAMANTINE_RUBY_GOLEM =
            ENTITY_TYPES.register("adamantine_ruby_golem",
                    () -> EntityType.Builder.of(
                            AdamantineRubyGolemEntity::new,
                            MobCategory.MONSTER)
                            .sized(1.4f,3.0f)
                            .fireImmune()
                            .build("adamantine_ruby_golem"));


    public static final RegistryObject<EntityType<RubySpiderEntity>> RUBY_SPIDER =
            ENTITY_TYPES.register("ruby_spider",
                    () -> EntityType.Builder.of(
                            RubySpiderEntity::new,
                            MobCategory.MONSTER)
                            .sized(1.4f,0.9f)
                            .build("ruby_spider"));


    public static final RegistryObject<EntityType<RubyEvokerEntity>> RUBY_EVOKER =
            ENTITY_TYPES.register("ruby_evoker",
                    () -> EntityType.Builder.of(
                            RubyEvokerEntity::new,
                            MobCategory.MONSTER)
                            .sized(0.6f,1.95f)
                            .build("ruby_evoker"));


    public static final RegistryObject<EntityType<RubyEvokerFangsEntity>> RUBY_EVOKER_FANGS =
            ENTITY_TYPES.register("ruby_evoker_fangs",
                    () -> EntityType.Builder.of(
                            RubyEvokerFangsEntity::new,
                            MobCategory.MISC)
                            .sized(0.5f,0.8f)
                            .clientTrackingRange(6)
                            .updateInterval(2)
                            .build("ruby_evoker_fangs"));


    public static final RegistryObject<EntityType<RubyPillagerEntity>> RUBY_PILLAGER =
            ENTITY_TYPES.register("ruby_pillager",
                    () -> EntityType.Builder.of(
                            RubyPillagerEntity::new,
                            MobCategory.MONSTER)
                            .sized(0.6f,1.95f)
                            .build("ruby_pillager"));


    public static final RegistryObject<EntityType<RubyPlayerEntity>> RUBY_PLAYER =
            ENTITY_TYPES.register("ruby_player",
                    () -> EntityType.Builder.of(
                            RubyPlayerEntity::new,
                            MobCategory.MONSTER)
                            .sized(0.6f,1.95f)
                            .build("ruby_player"));


    public static final RegistryObject<EntityType<RubyEndermanEntity>> RUBY_ENDERMAN =
            ENTITY_TYPES.register("ruby_enderman",
                    () -> EntityType.Builder.of(
                            RubyEndermanEntity::new,
                            MobCategory.MONSTER)
                            .sized(0.6f,2.9f)
                            .clientTrackingRange(8)
                            .build("ruby_enderman"));


    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
