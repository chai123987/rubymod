package net.tutorial.rubymod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tutorial.rubymod.RubyMod;
import net.tutorial.rubymod.entity.custom.RubyGolemEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RubyMod.MOD_ID);

    public static final RegistryObject<EntityType<RubyGolemEntity>> RUBY_GOLEM =
            ENTITY_TYPES.register("ruby_golem",
                    () -> EntityType.Builder.of(RubyGolemEntity::new, MobCategory.CREATURE)
                            .sized(0.9f, 0.9f) // 碰撞箱 宽 x 高
                            .build("ruby_golem"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
