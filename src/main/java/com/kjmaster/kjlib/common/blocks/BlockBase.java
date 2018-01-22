package com.kjmaster.kjlib.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockBase extends Block {

    private boolean hasTile = false;
    private Class<? extends TileEntity> tileClass;

    public BlockBase(String name, Material mat, CreativeTabs tab,
                     float hardness, float resistance, String tool, int harvest) {
        this(name, mat, tab, hardness, resistance);
        setHarvestLevel(tool, harvest);
    }

    public BlockBase(String name, Material mat, CreativeTabs tab, float hardness, float resistance, float light) {
        this(name, mat, tab, hardness, resistance);
        setLightLevel(light);
    }

    public BlockBase(String name, Material mat, CreativeTabs tab, float hardness, float resistance) {
        super(mat);
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(hardness);
        setResistance(resistance);
        setCreativeTab(tab);
    }

    public BlockBase(String name, Material mat, CreativeTabs tab, float hardness, float resistance, boolean hasTile, Class<? extends TileEntity> tileClass) {
        this(name, mat, tab, hardness, resistance);
        this.hasTile = hasTile;
        this.tileClass = tileClass;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return hasTile;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        try {
             return tileClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
