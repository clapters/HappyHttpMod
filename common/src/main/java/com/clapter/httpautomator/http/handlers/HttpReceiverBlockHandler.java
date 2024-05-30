package com.clapter.httpautomator.http.handlers;

import com.clapter.httpautomator.CommonClass;
import com.clapter.httpautomator.blockentity.HttpReceiverBlockEntity;
import com.clapter.httpautomator.http.api.IHttpHandler;
import com.sun.net.httpserver.HttpExchange;
import net.minecraft.core.BlockPos;

import java.io.IOException;
import java.util.*;

public class HttpReceiverBlockHandler implements IHttpHandler {

    //MAYBE SWITCH TO SET FOR FASTER REMOVAL OF ENTRIES
    private List<HttpReceiverBlockEntity> entityList;
    private String url;
    private static final String ALLOWED_METHOD = "POST";

    public HttpReceiverBlockHandler(HttpReceiverBlockEntity entity, String url){
        this.entityList = new ArrayList<HttpReceiverBlockEntity>(Collections.singletonList(entity));
        this.url = url;
    }

    public static void create(HttpReceiverBlockEntity entity, String url){
        IHttpHandler handler = CommonClass.HTTP_SERVER.getHandlerByUrl(url);
        if(handler != null) {
            if (handler instanceof HttpReceiverBlockHandler receiverHandler) {
                //ADD TO EXISTING HADNLER
                receiverHandler.addBlockEntity(entity);
                return;
            }
            //ERROR BECAUSE URL ALREADY EXISTS
            return;
        }
        HttpReceiverBlockHandler newHandler = new HttpReceiverBlockHandler(entity, url);
        CommonClass.HTTP_SERVER.registerHandler(newHandler);
    }

    private void addBlockEntity(HttpReceiverBlockEntity entity){
        this.entityList.add(entity);
    }

    private void removeBlockEntity(HttpReceiverBlockEntity entity){
        this.entityList.remove(entity);
    }

    @Override
    public int getId() {
        //THE IDEA IS TO ASSIGN THE POSITION OF THE ENTITY AS IT'S ID
        //MIGHT NEED A BETTER WAY, BUT IT WORKS FOR TESTS
        return 0;
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
        entityList.forEach(HttpReceiverBlockEntity::onSignal);
    }
}
