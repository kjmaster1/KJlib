package com.kjmaster.kjlib.common.blocks.item;

import com.kjmaster.kjlib.KJLib;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockBase extends ItemBlock {

    public ItemBlockBase(Block block) {
        super(block);
        if (!(block instanceof IMetaBlockName)) {
            KJLib.LOGGER.warn(KJLib.MODID + ": Something went wrong! The following block is not an instance"
             + " of IMetaBlockName!: " + block.getRegistryName());
        }
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "_" + ((IMetaBlockName) this.block).getSpecialName(stack);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }
}
