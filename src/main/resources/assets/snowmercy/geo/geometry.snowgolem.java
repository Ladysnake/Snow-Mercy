// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class geometry.snowgolem<T extends Entity> extends EntityModel<T> {
// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
public static final ModelLayerLocation LAYER_LOCATION=new ModelLayerLocation(new ResourceLocation("modid","geometry.snowgolem"),"main");
private final ModelPart head;
private final ModelPart piece1;
private final ModelPart piece2;

public geometry.snowgolem(ModelPart root){
        this.head=root.getChild("head");
        this.piece1=root.getChild("piece1");
        this.piece2=root.getChild("piece2");
        }

public static LayerDefinition createBodyLayer(){
        MeshDefinition meshdefinition=new MeshDefinition();
        PartDefinition partdefinition=meshdefinition.getRoot();

        PartDefinition head=partdefinition.addOrReplaceChild("head",CubeListBuilder.create().texOffs(0,0).mirror().addBox(-4.0F,-8.0F,-4.0F,8.0F,8.0F,8.0F,new CubeDeformation(-0.5F)).mirror(false),PartPose.offset(0.0F,4.0F,0.0F));

        PartDefinition helmet_r1=head.addOrReplaceChild("helmet_r1",CubeListBuilder.create().texOffs(32,0).mirror().addBox(-4.0F,-4.0F,-4.0F,8.0F,8.0F,8.0F,new CubeDeformation(0.0F)).mirror(false),PartPose.offsetAndRotation(0.0F,-4.0F,0.0F,-0.1309F,0.0F,0.0F));

        PartDefinition piece1=partdefinition.addOrReplaceChild("piece1",CubeListBuilder.create().texOffs(0,16).mirror().addBox(-5.0F,-10.0F,-5.0F,10.0F,10.0F,10.0F,new CubeDeformation(-0.5F)).mirror(false),PartPose.offset(0.0F,13.0F,0.0F));

        PartDefinition mortar=piece1.addOrReplaceChild("mortar",CubeListBuilder.create(),PartPose.offset(0.0F,-2.0F,-6.0F));

        PartDefinition launcher_r1=mortar.addOrReplaceChild("launcher_r1",CubeListBuilder.create().texOffs(48,38).mirror().addBox(-4.0F,-7.0F,-4.0F,8.0F,14.0F,8.0F,new CubeDeformation(-0.5F)).mirror(false),PartPose.offsetAndRotation(0.0F,0.0F,0.0F,0.3054F,0.0F,0.0F));

        PartDefinition icicle=mortar.addOrReplaceChild("icicle",CubeListBuilder.create().texOffs(80,49).addBox(-1.5F,-11.0F,0.0F,3.0F,11.0F,0.0F,new CubeDeformation(0.0F)),PartPose.offsetAndRotation(-1.5F,-5.0F,0.0F,0.3054F,0.0F,0.0F));

        PartDefinition cube_r1=icicle.addOrReplaceChild("cube_r1",CubeListBuilder.create().texOffs(80,49).addBox(-1.5F,-5.5F,0.0F,3.0F,11.0F,0.0F,new CubeDeformation(0.0F)),PartPose.offsetAndRotation(0.0F,-5.5F,0.0F,0.0F,-1.5708F,0.0F));

        PartDefinition icicle2=mortar.addOrReplaceChild("icicle2",CubeListBuilder.create().texOffs(80,49).addBox(-1.5F,-11.0F,0.0F,3.0F,11.0F,0.0F,new CubeDeformation(0.0F)),PartPose.offsetAndRotation(1.5F,-5.0F,0.0F,0.3054F,0.0F,0.0F));

        PartDefinition cube_r2=icicle2.addOrReplaceChild("cube_r2",CubeListBuilder.create().texOffs(80,49).addBox(-1.5F,-5.5F,0.0F,3.0F,11.0F,0.0F,new CubeDeformation(0.0F)),PartPose.offsetAndRotation(0.0F,-5.5F,0.0F,0.0F,-1.5708F,0.0F));

        PartDefinition icicle3=mortar.addOrReplaceChild("icicle3",CubeListBuilder.create().texOffs(80,49).addBox(-1.5F,-11.0F,0.0F,3.0F,11.0F,0.0F,new CubeDeformation(0.0F)),PartPose.offsetAndRotation(-1.5F,-4.0F,-3.0F,0.3054F,0.0F,0.0F));

        PartDefinition cube_r3=icicle3.addOrReplaceChild("cube_r3",CubeListBuilder.create().texOffs(80,49).addBox(-1.5F,-5.5F,0.0F,3.0F,11.0F,0.0F,new CubeDeformation(0.0F)),PartPose.offsetAndRotation(0.0F,-5.5F,0.0F,0.0F,-1.5708F,0.0F));

        PartDefinition icicle4=mortar.addOrReplaceChild("icicle4",CubeListBuilder.create().texOffs(80,49).addBox(-1.5F,-11.0F,0.0F,3.0F,11.0F,0.0F,new CubeDeformation(0.0F)),PartPose.offsetAndRotation(1.5F,-4.0F,-3.0F,0.3054F,0.0F,0.0F));

        PartDefinition cube_r4=icicle4.addOrReplaceChild("cube_r4",CubeListBuilder.create().texOffs(80,49).addBox(-1.5F,-5.5F,0.0F,3.0F,11.0F,0.0F,new CubeDeformation(0.0F)),PartPose.offsetAndRotation(0.0F,-5.5F,0.0F,0.0F,-1.5708F,0.0F));

        PartDefinition piece2=partdefinition.addOrReplaceChild("piece2",CubeListBuilder.create().texOffs(0,36).mirror().addBox(-6.0F,-12.0F,-6.0F,12.0F,12.0F,12.0F,new CubeDeformation(-0.5F)).mirror(false),PartPose.offset(0.0F,24.0F,0.0F));

        return LayerDefinition.create(meshdefinition,128,64);
        }

@Override
public void setupAnim(T entity,float limbSwing,float limbSwingAmount,float ageInTicks,float netHeadYaw,float headPitch){

        }

@Override
public void renderToBuffer(PoseStack poseStack,VertexConsumer buffer,int packedLight,int packedOverlay,float red,float green,float blue,float alpha){
        head.render(poseStack,buffer,packedLight,packedOverlay);
        piece1.render(poseStack,buffer,packedLight,packedOverlay);
        piece2.render(poseStack,buffer,packedLight,packedOverlay);
        }
        }