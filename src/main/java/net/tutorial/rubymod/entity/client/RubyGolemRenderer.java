package net.tutorial.rubymod.entity.client;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.tutorial.rubymod.RubyMod;
import net.tutorial.rubymod.entity.custom.RubyGolemEntity;

// 用原版僵尸的人形模型(自带走路/攻击动画)，套上我们的红色贴图
public class RubyGolemRenderer extends HumanoidMobRenderer<RubyGolemEntity, ZombieModel<RubyGolemEntity>> {

    public RubyGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(RubyGolemEntity entity) {
        return new ResourceLocation(RubyMod.MOD_ID, "textures/entity/ruby_golem.png");
    }
}
