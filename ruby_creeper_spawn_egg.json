package net.tutorial.rubymod.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Skeleton;
import net.tutorial.rubymod.RubyMod;

// 继承原版骷髅渲染器：自带骷髅模型 + 持弓/持剑姿势 + 盔甲层，只换红色贴图
public class RubySkeletonRenderer extends SkeletonRenderer {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RubyMod.MOD_ID, "textures/entity/ruby_skeleton.png");

    public RubySkeletonRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Skeleton entity) {
        return TEXTURE;
    }
}
