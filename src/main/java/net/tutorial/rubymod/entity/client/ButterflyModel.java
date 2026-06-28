package net.tutorial.rubymod.entity.client;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

// 由 Blockbench 模型 butterfly.bbmodel 自动转换而成（盒式 UV，贴图 128x128）
public class ButterflyModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart butterfly;
    private final ModelPart leftWing;
    private final ModelPart rightWing;

    public ButterflyModel(ModelPart root) {
        this.root = root;
        this.butterfly = root.getChild("butterfly");
        this.leftWing = this.butterfly.getChild("left_wing");
        this.rightWing = this.butterfly.getChild("right_wing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition butterfly = root.addOrReplaceChild("butterfly", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = butterfly.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-1.5F, -13.0F, -1.5F, 3.0F, 3.0F, 3.0F)
                .texOffs(13, 0).addBox(-1.0F, -10.0F, -1.0F, 2.0F, 4.0F, 2.0F)
                .texOffs(58, 0).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 3.0F, 2.0F)
                .texOffs(75, 21).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 2.0F, 1.0F)
                .texOffs(90, 21).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F)
                .texOffs(102, 12).addBox(0.0F, -16.0F, 0.0F, 1.0F, 3.0F, 1.0F)
                .texOffs(80, 21).addBox(1.0F, -18.0F, 0.0F, 1.0F, 2.0F, 1.0F)
                .texOffs(95, 21).addBox(1.5F, -19.0F, 0.0F, 1.0F, 1.0F, 1.0F)
                .texOffs(107, 12).addBox(-1.0F, -16.0F, 0.0F, 1.0F, 3.0F, 1.0F)
                .texOffs(85, 21).addBox(-2.0F, -18.0F, 0.0F, 1.0F, 2.0F, 1.0F)
                .texOffs(100, 21).addBox(-2.5F, -19.0F, 0.0F, 1.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_wing = butterfly.addOrReplaceChild("left_wing", CubeListBuilder.create()
                .texOffs(67, 0).addBox(0.0F, 0.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(78, 0).addBox(4.0F, -1.0F, -0.5F, 3.0F, 3.0F, 1.0F)
                .texOffs(112, 12).addBox(0.0F, -2.0F, -0.5F, 5.0F, 2.0F, 1.0F)
                .texOffs(22, 0).addBox(5.0F, -3.0F, -0.5F, 4.0F, 4.0F, 1.0F)
                .texOffs(87, 0).addBox(8.0F, -3.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(0, 17).addBox(1.0F, -4.0F, -0.5F, 6.0F, 2.0F, 1.0F)
                .texOffs(15, 17).addBox(6.0F, -5.0F, -0.5F, 5.0F, 2.0F, 1.0F)
                .texOffs(28, 17).addBox(9.0F, -6.0F, -0.5F, 4.0F, 2.0F, 1.0F)
                .texOffs(39, 17).addBox(11.0F, -7.0F, -0.5F, 3.0F, 2.0F, 1.0F)
                .texOffs(98, 0).addBox(12.0F, -6.0F, -0.5F, 3.0F, 3.0F, 1.0F)
                .texOffs(107, 0).addBox(10.0F, 0.0F, -0.5F, 3.0F, 3.0F, 1.0F)
                .texOffs(33, 0).addBox(12.0F, -3.0F, -0.5F, 2.0F, 4.0F, 1.0F)
                .texOffs(116, 0).addBox(13.0F, -4.0F, -0.5F, 2.0F, 3.0F, 1.0F)
                .texOffs(0, 7).addBox(2.0F, -2.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(11, 7).addBox(7.0F, -1.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(22, 7).addBox(9.0F, -4.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(33, 7).addBox(0.0F, 3.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(44, 7).addBox(3.0F, 4.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(55, 7).addBox(6.0F, 5.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(48, 17).addBox(5.0F, 7.0F, -0.5F, 4.0F, 2.0F, 1.0F)
                .texOffs(59, 17).addBox(3.0F, 8.0F, -0.5F, 4.0F, 2.0F, 1.0F)
                .texOffs(70, 17).addBox(1.0F, 9.0F, -0.5F, 4.0F, 2.0F, 1.0F)
                .texOffs(66, 7).addBox(8.0F, 7.0F, -0.5F, 3.0F, 3.0F, 1.0F)
                .texOffs(75, 7).addBox(2.0F, 5.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(81, 17).addBox(5.0F, 9.0F, -0.5F, 3.0F, 2.0F, 1.0F),
                PartPose.offset(1.0F, -10.0F, 0.0F));

        PartDefinition right_wing = butterfly.addOrReplaceChild("right_wing", CubeListBuilder.create()
                .texOffs(86, 7).addBox(-4.0F, 0.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(97, 7).addBox(-7.0F, -1.0F, -0.5F, 3.0F, 3.0F, 1.0F)
                .texOffs(90, 17).addBox(-5.0F, -2.0F, -0.5F, 5.0F, 2.0F, 1.0F)
                .texOffs(40, 0).addBox(-9.0F, -3.0F, -0.5F, 4.0F, 4.0F, 1.0F)
                .texOffs(106, 7).addBox(-12.0F, -3.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(103, 17).addBox(-7.0F, -4.0F, -0.5F, 6.0F, 2.0F, 1.0F)
                .texOffs(0, 21).addBox(-11.0F, -5.0F, -0.5F, 5.0F, 2.0F, 1.0F)
                .texOffs(13, 21).addBox(-13.0F, -6.0F, -0.5F, 4.0F, 2.0F, 1.0F)
                .texOffs(24, 21).addBox(-14.0F, -7.0F, -0.5F, 3.0F, 2.0F, 1.0F)
                .texOffs(117, 7).addBox(-15.0F, -6.0F, -0.5F, 3.0F, 3.0F, 1.0F)
                .texOffs(0, 12).addBox(-13.0F, 0.0F, -0.5F, 3.0F, 3.0F, 1.0F)
                .texOffs(51, 0).addBox(-14.0F, -3.0F, -0.5F, 2.0F, 4.0F, 1.0F)
                .texOffs(9, 12).addBox(-15.0F, -4.0F, -0.5F, 2.0F, 3.0F, 1.0F)
                .texOffs(16, 12).addBox(-6.0F, -2.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(27, 12).addBox(-11.0F, -1.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(38, 12).addBox(-13.0F, -4.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(49, 12).addBox(-4.0F, 3.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(60, 12).addBox(-7.0F, 4.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(71, 12).addBox(-10.0F, 5.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(33, 21).addBox(-9.0F, 7.0F, -0.5F, 4.0F, 2.0F, 1.0F)
                .texOffs(44, 21).addBox(-7.0F, 8.0F, -0.5F, 4.0F, 2.0F, 1.0F)
                .texOffs(55, 21).addBox(-5.0F, 9.0F, -0.5F, 4.0F, 2.0F, 1.0F)
                .texOffs(82, 12).addBox(-11.0F, 7.0F, -0.5F, 3.0F, 3.0F, 1.0F)
                .texOffs(91, 12).addBox(-6.0F, 5.0F, -0.5F, 4.0F, 3.0F, 1.0F)
                .texOffs(66, 21).addBox(-8.0F, 9.0F, -0.5F, 3.0F, 2.0F, 1.0F),
                PartPose.offset(-1.0F, -10.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // 扑翅：两翅绕竖直轴一开一合，频率较快、幅度适中
        float flap = Mth.cos(ageInTicks * 1.2F) * 0.6F + 0.35F;
        this.leftWing.yRot = flap;
        this.rightWing.yRot = -flap;
        // 身体轻微上下浮动
        this.butterfly.y = 24.0F + Mth.sin(ageInTicks * 0.3F) * 0.6F;
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
