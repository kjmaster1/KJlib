package com.kjmaster.kjlib.common.container;

/*
 * This file ("StackUtil.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2017 Ellpeck
 */

import java.util.Collection;

import com.kjmaster.kjlib.KJLib;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public final class StackUtil
{

    public static ItemStack validateCopy(ItemStack stack)
    {
        if(isValid(stack))
        {
            return stack.copy();
        }
        else
        {
            return getEmpty();
        }
    }

    public static ItemStack validateCheck(ItemStack stack)
    {
        if(isValid(stack))
        {
            return stack;
        }
        else
        {
            return getEmpty();
        }
    }

    public static boolean isValid(ItemStack stack)
    {
        if(stack == null)
        {
            KJLib.LOGGER.warn("Null ItemStack detected", stack);
        }
        return !stack.isEmpty();
    }

    public static ItemStack getEmpty()
    {
        return ItemStack.EMPTY;
    }

    public static int getStackSize(ItemStack stack)
    {
        if(!isValid(stack))
        {
            return 0;
        }
        else{
            return stack.getCount();
        }
    }

    public static ItemStack setStackSize(ItemStack stack, int size)
    {
        return setStackSize(stack, size, false);
    }

    public static ItemStack setStackSize(ItemStack stack, int size, boolean containerOnEmpty)
    {
        if(size <= 0)
        {
            if(isValid(stack) && containerOnEmpty){
                return stack.getItem().getContainerItem(stack);
            }
            else{
                return getEmpty();
            }
        }
        stack.setCount(size);
        return stack;
    }

    public static ItemStack addStackSize(ItemStack stack, int size)
    {
        return addStackSize(stack, size, false);
    }

    public static ItemStack addStackSize(ItemStack stack, int size, boolean containerOnEmpty){
        return setStackSize(stack, getStackSize(stack)+size, containerOnEmpty);
    }

    public static boolean isIInvEmpty(NonNullList<ItemStack> slots)
    {
        for(ItemStack stack : slots)
        {
            if(StackUtil.isValid(stack))
            {
                return false;
            }
        }

        return true;
    }

    public static NonNullList<ItemStack> createSlots(int size)
    {
        return NonNullList.withSize(size, getEmpty());
    }

    public static boolean isEmpty(Collection<ItemStack> stacks)
    {
        if (stacks.isEmpty()) return true;
        else for (ItemStack s : stacks) if (!s.isEmpty()) return false;
        return true;
    }
}
