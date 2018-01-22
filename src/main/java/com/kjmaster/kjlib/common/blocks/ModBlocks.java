package com.kjmaster.kjlib.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    @SideOnly(Side.CLIENT)
    public static void registerMetaRender(String modID, Block block, int meta, String fileName) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta,
                new ModelResourceLocation(new ResourceLocation(modID, fileName), "inventory"));
    }

    private static void registerBlockstateMultiItem(String modID, Item item, int meta, String variantName, String path) {
        ResourceLocation loc = new ResourceLocation(modID, path);
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(loc, "type=" + variantName));
    }
}
