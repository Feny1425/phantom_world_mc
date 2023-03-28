// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class small_phantom<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "small_phantom"), "main");
	private final ModelPart Body;
	private final ModelPart arm1;
	private final ModelPart arm2;

	public small_phantom(ModelPart root) {
		this.Body = root.getChild("Body");
		this.arm1 = root.getChild("arm1");
		this.arm2 = root.getChild("arm2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -4.5667F, -0.9167F, 12.0F, 9.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.0F, -3.5667F, -1.9167F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(-0.5F, -2.6667F, -2.4167F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 21).addBox(-3.0F, 0.4333F, -1.9167F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 21).addBox(-4.0F, 1.4333F, -1.9167F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 7).addBox(2.0F, 1.4333F, -1.9167F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.5667F, -4.0833F));

		PartDefinition arm1 = partdefinition.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(14, 0).addBox(-1.0F, 0.25F, -9.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 16.0F, 0.0F));

		PartDefinition cube_r1 = arm1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 0).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition arm2 = partdefinition.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(14, 0).addBox(-1.0F, 0.25F, -9.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 16.0F, 0.0F));

		PartDefinition cube_r2 = arm2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(14, 0).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.3491F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}