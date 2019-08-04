package letiu.pistronics.entity;

import letiu.pistronics.Pistronics;
import letiu.pistronics.render.RenderBlockGroupEntity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PistronicsEntities {

    public static void init() {

        int id = 1;
        EntityRegistry.registerModEntity(BlockGroupEntity.REGISTRY_NAME, BlockGroupEntity.class, "BlockGroup", id++, Pistronics.INSTANCE, 64, 3, true);

    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(BlockGroupEntity.class, RenderBlockGroupEntity.FACTORY);
    }
}
