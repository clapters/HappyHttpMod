package com.clapter.httpautomator.http.handlers;

import com.clapter.httpautomator.http.api.IHttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class HttpReceiverBlockHandler implements IHttpHandler {

    public HttpReceiverBlockHandler() {}

    @Override
    public String getUrl() {
        return "/test";
    }

    @Override
    public List<String> httpMethods() {
        return List.of("GET");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("REQUEST GET");
    }

}
