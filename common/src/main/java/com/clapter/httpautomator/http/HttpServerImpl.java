package com.clapter.httpautomator.http;

import com.clapter.httpautomator.http.api.IHttpHandler;
import com.clapter.httpautomator.http.api.IHttpServer;
import com.clapter.httpautomator.utils.ImplLoader;
import com.sun.net.httpserver.HttpServer;
import org.apache.http.HttpMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpServerImpl implements IHttpServer {

    private HttpServer server;

    private Map<Integer, IHttpHandler> handlerMap;

    public HttpServerImpl(){
        handlerMap = new HashMap<Integer, IHttpHandler>();
    }

    public boolean startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.setExecutor(null); // creates a default executor
        server.start();
        return true;
    }

    @Override
    public void initHandlers(){
        List<IHttpHandler> handlerList = ImplLoader.loadAll(IHttpHandler.class);
        handlerList.forEach(this::registerHandler);
    }

    @Override
    public void registerHandler(IHttpHandler handler) {
        if(!handlerMap.containsKey(handler.getId())) {
            registerAndPutInMap(handler);
        }else{
            //ALREADY CONTAINING A HANDLER FOR THAT ID (FOR THAT BLOCK IN OUR TEST)
            //FOR NOW JUST OVERRIDE WITH NEW ONE

            server.removeContext(handlerMap.get(handler.getId()).getUrl());
            registerAndPutInMap(handler);

        }
    }

    private void registerAndPutInMap(IHttpHandler handler) {
        server.createContext(handler.getUrl(), handler);
        handlerMap.put(handler.getId(), handler);
    }

    @Override
    public void stopServer() {
        if(server != null){
            server.stop(1);
            server = null;
            //System.gc();
        }
    }


}
