package com.clapter.httpautomator.http.handlers;

import com.clapter.httpautomator.CommonClass;
import com.clapter.httpautomator.blockentity.HttpReceiverBlockEntity;
import com.clapter.httpautomator.http.api.IHttpHandler;
import com.sun.net.httpserver.HttpExchange;
import net.minecraft.core.BlockPos;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class HttpReceiverBlockHandler implements IHttpHandler {

    private HttpReceiverBlockEntity entity;
    private String url;
    private static final String ALLOWED_METHOD = "POST";

    public HttpReceiverBlockHandler(HttpReceiverBlockEntity entity, String url){
        this.entity = entity;
        this.url = url;
        CommonClass.HTTP_SERVER.registerHandler(this);
    }


    @Override
    public int getId() {
        //THE IDEA IS TO ASSIGN THE POSITION OF THE ENTITY AS IT'S ID
        //MIGHT NEED A BETTER WAY, BUT IT WORKS FOR TESTS
        return entity.getBlockPos().hashCode();
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public List<String> httpMethods() {
        return List.of(ALLOWED_METHOD);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if(entity == null){
            //TODO: Entity unloaded -> Remove the handler
            return;
        }
        entity.onSignal();
    }
}
