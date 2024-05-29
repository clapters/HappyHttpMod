package com.clapter.httpautomator.platform;

import com.clapter.httpautomator.Constants;
import com.clapter.httpautomator.platform.services.IBlockEntityRegistry;
import com.clapter.httpautomator.platform.services.IBlockRegistry;
import com.clapter.httpautomator.platform.services.IItemRegistry;
import com.clapter.httpautomator.platform.services.IPlatformHelper;
import com.clapter.httpautomator.utils.ImplLoader;

import java.util.ServiceLoader;

public class Services {

    //public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final IBlockRegistry BLOCK_REGISTRY = ImplLoader.loadSingle(IBlockRegistry.class);
    public static final IItemRegistry ITEM_REGISTRY = ImplLoader.loadSingle(IItemRegistry.class);
    public static final IBlockEntityRegistry BLOCK_ENTITIES_REGISTRY = ImplLoader.loadSingle(IBlockEntityRegistry.class);
}