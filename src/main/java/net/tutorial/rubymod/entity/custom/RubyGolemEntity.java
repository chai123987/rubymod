package net.tutorial.rubymod.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tutorial.rubymod.entity.ModEntities;
import net.tutorial.rubymod.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class RubyGolemEntity extends Animal {

    public RubyGolemEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    // 实体的属性：血量、移动速度等
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 16.0D);
    }

    // 行为（AI Goal）。数字越小优先级越高。
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this)); // 会游泳，不会淹死
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D)); // 受伤逃跑
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D)); // 繁殖
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.of(ModItems.RUBY.get()), false)); // 被红宝石吸引
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D)); // 幼崽跟随父母
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D)); // 随机走动
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F)); // 看玩家
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this)); // 随机张望
    }

    // 用红宝石可以喂养/繁殖
    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ModItems.RUBY.get());
    }

    // 繁殖后代
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ModEntities.RUBY_GOLEM.get().create(level);
    }
}
