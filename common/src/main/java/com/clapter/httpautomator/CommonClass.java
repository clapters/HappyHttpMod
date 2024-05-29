package com.clapter.httpautomator;

import com.clapter.httpautomator.registry.ModBlocks;

public class CommonClass {

    public static void init() {
        ModBlocks.registerBlocks();
    }

    //On Server Starting Callback. Is used for starting the HTTP-Server
    public static void onServerStarting(){
        System.out.println("SERVER STARTING");
    }

}