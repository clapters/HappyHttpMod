package com.clapter.httpautomator.http;

import com.clapter.httpautomator.http.api.IHttpHandler;
import com.clapter.httpautomator.http.api.IHttpServer;
import com.clapter.httpautomator.utils.ImplLoader;
import com.sun.net.httpserver.HttpServer;
import org.apache.http.HttpMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class HttpServerImpl implements IHttpServer {

    private HttpServer server;

    private List<IHttpHandler> handlerList;

    public HttpServerImpl(){
    }

    public boolean startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.setExecutor(null); // creates a default executor
        server.start();
        return true;
    }

    public void initHandlers(){
        handlerList = ImplLoader.loadAll(IHttpHandler.class);
        handlerList.forEach(handler -> {
            server.createContext(handler.getUrl(), handler);
        });
    }



}
