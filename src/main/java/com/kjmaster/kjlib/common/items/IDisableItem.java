package com.kjmaster.kjlib.common.items;

public interface IDisableItem {

    /**
     * Represents an item that can be disabled in the configuration of the mod.
     * If this returns true, assume the item is not registered with the game, but may still be instantiated.
     * @return If the item has not been registered with the Forge Registry.
     */
    public boolean isDisabled();
}
