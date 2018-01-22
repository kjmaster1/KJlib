package com.kjmaster.kjlib.common.items;

import net.minecraft.creativetab.CreativeTabs;

public class MetaItemBase extends ItemBase {

    public MetaItemBase(String name, CreativeTabs tab) {
        super(name, tab);
        this.setHasSubtypes(true);
    }

    public MetaItemBase(String name, CreativeTabs tab, int maxSize) {
        super(name, tab, maxSize);
        this.setHasSubtypes(true);
    }
}
