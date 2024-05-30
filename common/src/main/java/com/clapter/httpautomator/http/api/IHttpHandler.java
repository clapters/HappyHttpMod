package com.clapter.httpautomator.http.api;

import com.sun.net.httpserver.HttpHandler;

import java.util.List;

public interface IHttpHandler extends HttpHandler {

    int getId();
    String getUrl();
    List<String> httpMethods();
    default void handle(){
        //DEFAULT IMPLEMENTATION
    }
}
