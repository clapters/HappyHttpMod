package com.clapter.httpautomator;

import com.clapter.httpautomator.http.HttpClientImpl;
import com.clapter.httpautomator.http.HttpServerImpl;
import com.clapter.httpautomator.http.api.IHttpClient;
import com.clapter.httpautomator.http.api.IHttpServer;
import com.clapter.httpautomator.registry.ModBlockEntities;
import com.clapter.httpautomator.registry.ModBlocks;
import com.clapter.httpautomator.registry.ModItems;
import com.clapter.httpautomator.registry.ModNetworkPackets;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

import java.io.IOException;

public class CommonClass {

    public static final IHttpServer HTTP_SERVER = new HttpServerImpl();
    public static final IHttpClient HTTP_CLIENT = new HttpClientImpl();

    public static void init() {
        ModBlocks.registerBlocks();
        ModBlockEntities.registerBlockEntities();
        ModItems.registerItems();
    }

    public static void registerPackets(){
        ModNetworkPackets.registerPackets();
    }

    //On Server Starting Callback. Is used for starting the HTTP-Server
    public static void onServerStarting(){
        try {
            if(HTTP_SERVER.startServer()){
                Constants.LOG.info("HTTP Server started on: ");
            }
        }catch (IOException e){
            //SOMETHING WENT WRONG
            e.printStackTrace();
        }
        catch (Exception e){
            //e.printStackTrace();
        }
    }

    public static void onServerStarted(){
        HTTP_SERVER.initHandlers();
    }

    public static void onServerStopping(){
        HTTP_SERVER.stopServer();
    }

}