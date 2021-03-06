package com.kjmaster.kjlib.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFacingBase extends BlockBase {

    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockFacingBase(String name, Material mat, CreativeTabs tab, float hardness, float resistance, String tool, int harvest) {
        super(name, mat, tab, hardness, resistance, tool, harvest);
        setState();
    }

    public BlockFacingBase(String name, Material mat, CreativeTabs tab, float hardness, float resistance, float light) {
        super(name, mat, tab, hardness, resistance, light);
        setState();
    }

    public BlockFacingBase(String name, Material mat, CreativeTabs tab, float hardness, float resistance) {
        super(name, mat, tab, hardness, resistance);
        setState();
    }

    public BlockFacingBase(String name, Material mat, CreativeTabs tab, float hardness, float resistance, boolean hasTile, Class<? extends TileEntity> tileClass) {
        super(name, mat, tab, hardness, resistance, hasTile, tileClass);
        setState();
    }

    private void setState() {
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }
}
