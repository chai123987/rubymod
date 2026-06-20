package net.tutorial.rubymod.entity.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.tutorial.rubymod.RubyMod;
import net.tutorial.rubymod.entity.custom.RubyGolemEntity;

// 用通用人形模型(从僵尸模型骨架烘焙)，自带走路/攻击挥手动画
public class RubyGolemRenderer extends HumanoidMobRenderer<RubyGolemEntity, HumanoidModel<RubyGolemEntity>> {

    public RubyGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.ZOMBIE)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(RubyGolemEntity entity) {
        return new ResourceLocation(RubyMod.MOD_ID, "textures/entity/ruby_golem.png");
    }
}
