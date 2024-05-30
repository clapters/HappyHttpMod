package com.clapter.httpautomator.http.handlers;

import com.clapter.httpautomator.http.api.IHttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

//TEST HANDLER THAT GETS REGISTERED AUTOMATICALLY WITH THE ImlLoader.class
public class TestHttpHandler implements IHttpHandler {

    public TestHttpHandler() {}

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
