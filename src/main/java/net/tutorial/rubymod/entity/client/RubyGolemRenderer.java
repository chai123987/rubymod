package net.tutorial.rubymod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.tutorial.rubymod.RubyMod;
import net.tutorial.rubymod.entity.custom.RubyGolemEntity;

public class RubyGolemRenderer extends MobRenderer<RubyGolemEntity, RubyGolemModel<RubyGolemEntity>> {

    public RubyGolemRenderer(EntityRendererProvider.Context context) {
        // 0.5F = 阴影大小
        super(context, new RubyGolemModel<>(context.bakeLayer(ModModelLayers.RUBY_GOLEM)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(RubyGolemEntity entity) {
        return new ResourceLocation(RubyMod.MOD_ID, "textures/entity/ruby_golem.png");
    }
}
