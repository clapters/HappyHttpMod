package com.clapter.httpautomator;

import com.clapter.httpautomator.http.HttpServerImpl;
import com.clapter.httpautomator.http.api.IHttpServer;
import com.clapter.httpautomator.registry.ModBlockEntities;
import com.clapter.httpautomator.registry.ModBlocks;
import com.clapter.httpautomator.registry.ModItems;

import java.io.IOException;

public class CommonClass {

    private static IHttpServer httpServer;

    public static void init() {
        ModBlocks.registerBlocks();
        ModBlockEntities.registerBlockEntities();
        ModItems.registerItems();
    }

    //On Server Starting Callback. Is used for starting the HTTP-Server
    public static void onServerStarting(){
        httpServer = new HttpServerImpl();
        try {
            if(httpServer.startServer()){
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
        if(httpServer == null){
            return;
        }
        httpServer.initHandlers();
    }

    public static void onServerStopping(){
        if(httpServer != null){
            httpServer.stopServer();
        }
    }


}