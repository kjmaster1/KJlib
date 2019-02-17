package com.kjmaster.kjlib;

import com.kjmaster.kjlib.common.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = KJLib.MODID, version = KJLib.VERSION)
public class KJLib
{
    public static final String MODID = "kjlib";
    public static final String VERSION = "1.0.5";
    public static final String CLIENT_PROXY = "com.kjmaster.kjlib.client.ClientProxy";
    public static final String COMMON_PROXY = "com.kjmaster.kjlib.common.CommonProxy";
    public static Logger LOGGER;

    @SidedProxy(clientSide = KJLib.CLIENT_PROXY, serverSide = KJLib.COMMON_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
