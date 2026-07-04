package net.tutorial.rubymod.entity.client;

import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.EnderMan;
import net.tutorial.rubymod.RubyMod;

public class RubyEndermanRenderer extends EndermanRenderer {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RubyMod.MOD_ID, "textures/entity/ruby_enderman.png");

    public RubyEndermanRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(EnderMan entity) {
        return TEXTURE;
    }
}
