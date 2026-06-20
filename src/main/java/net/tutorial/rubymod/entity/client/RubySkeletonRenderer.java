package net.tutorial.rubymod.entity.client;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tutorial.rubymod.RubyMod;
import net.tutorial.rubymod.entity.custom.RubySkeletonEntity;

public class RubySkeletonRenderer extends AbstractSkeletonRenderer<RubySkeletonEntity> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RubyMod.MOD_ID, "textures/entity/ruby_skeleton.png");

    public RubySkeletonRenderer(EntityRendererProvider.Context context) {
        super(context, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
    }

    @Override
    public ResourceLocation getTextureLocation(RubySkeletonEntity entity) {
        return TEXTURE;
    }
}
