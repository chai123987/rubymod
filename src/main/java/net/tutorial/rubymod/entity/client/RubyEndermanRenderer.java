package net.tutorial.rubymod.entity.client;

import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tutorial.rubymod.RubyMod;
import net.tutorial.rubymod.entity.custom.RubyEndermanEntity;

// 复用原版末影人模型，只换贴图为红色版
public class RubyEndermanRenderer extends EndermanRenderer {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RubyMod.MOD_ID, "textures/entity/ruby_enderman.png");

    public RubyEndermanRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(RubyEndermanEntity entity) {
        return TEXTURE;
    }
}
