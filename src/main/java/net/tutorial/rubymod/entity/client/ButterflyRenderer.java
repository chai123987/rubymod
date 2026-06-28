package net.tutorial.rubymod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.tutorial.rubymod.RubyMod;
import net.tutorial.rubymod.entity.custom.ButterflyEntity;

public class ButterflyRenderer extends MobRenderer<ButterflyEntity, ButterflyModel<ButterflyEntity>> {

    public ButterflyRenderer(EntityRendererProvider.Context context) {
        super(context, new ButterflyModel<>(context.bakeLayer(ModModelLayers.BUTTERFLY)), 0.2F);
    }

    @Override
    protected void scale(ButterflyEntity entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(0.5F, 0.5F, 0.5F); // 蝴蝶偏小
    }

    @Override
    public ResourceLocation getTextureLocation(ButterflyEntity entity) {
        return new ResourceLocation(RubyMod.MOD_ID, "textures/entity/butterfly.png");
    }
}
