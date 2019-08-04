package letiu.pistronics.render;

import letiu.pistronics.Pistronics;
import letiu.pistronics.block.PistronicsBlocks;
import letiu.pistronics.entity.BlockGroupEntity;
import letiu.pistronics.piston.BlockData;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import javax.annotation.Nullable;
import java.util.List;

public class RenderBlockGroupEntity extends Render<BlockGroupEntity> {

    private ResourceLocation box_texture = new ResourceLocation(Pistronics.MOD_ID + ":textures/entities/stopper.png");
    public static final Factory FACTORY = new Factory();

    public RenderBlockGroupEntity(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(BlockGroupEntity entity) {
        return this.box_texture;
    }

    @Override
    public void doRender(BlockGroupEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {

        renderLivingLabel(entity, "BlockGroup", x + 0.5D, y - 1D, z + 0.5D, 16);

        List<BlockData> blockDataList = entity.getBlockDataList();

        if (blockDataList != null) {
            for (BlockData blockData : blockDataList) {
                renderBlockData(entity, blockData, x, y, z, partialTicks);
            }
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    private void renderBlockData(BlockGroupEntity entity, BlockData blockData, double x, double y, double z, float partialTicks) {

        x += blockData.getBlockPos().getX();
        y += blockData.getBlockPos().getY();
        z += blockData.getBlockPos().getZ();

        IBlockState iblockstate = blockData.getBlockState();

        if (iblockstate != null && iblockstate.getRenderType() == EnumBlockRenderType.MODEL) {

            World world = entity.getWorldObj();

            if (iblockstate != world.getBlockState(new BlockPos(entity)) && iblockstate.getRenderType() != EnumBlockRenderType.INVISIBLE) {
                this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                GlStateManager.pushMatrix();
                GlStateManager.disableLighting();
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferbuilder = tessellator.getBuffer();

                if (this.renderOutlines) {
                    GlStateManager.enableColorMaterial();
                    GlStateManager.enableOutlineMode(this.getTeamColor(entity));
                }

                bufferbuilder.begin(7, DefaultVertexFormats.BLOCK);
                BlockPos blockpos = new BlockPos(entity.posX, entity.getEntityBoundingBox().maxY, entity.posZ);
                GlStateManager.translate((float)(x - (double)blockpos.getX()), (float)(y - (double)blockpos.getY()), (float)(z - (double)blockpos.getZ()));
                BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
                blockrendererdispatcher.getBlockModelRenderer().renderModel(world, blockrendererdispatcher.getModelForState(iblockstate), iblockstate, blockpos, bufferbuilder, false, MathHelper.getPositionRandom(blockpos)); // TODO figure out the random thing
                tessellator.draw();

                if (this.renderOutlines) {
                    GlStateManager.disableOutlineMode();
                    GlStateManager.disableColorMaterial();
                }

                GlStateManager.enableLighting();
                GlStateManager.popMatrix();
            }
        }
    }

    public static class Factory implements IRenderFactory<BlockGroupEntity> {

        @Override
        public Render<? super BlockGroupEntity> createRenderFor(RenderManager manager) {
            return new RenderBlockGroupEntity(manager);
        }

    }
}
