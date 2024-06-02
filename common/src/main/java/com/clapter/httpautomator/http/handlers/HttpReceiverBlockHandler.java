package com.clapter.httpautomator.http.handlers;

import com.clapter.httpautomator.CommonClass;
import com.clapter.httpautomator.blockentity.HttpReceiverBlockEntity;
import com.clapter.httpautomator.http.api.IHttpHandler;
import com.sun.net.httpserver.HttpExchange;
import net.minecraft.core.BlockPos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        String validatedUrl = validateUrl(url);
        IHttpHandler handler = CommonClass.HTTP_SERVER.getHandlerByUrl(validatedUrl);
        if(handler != null) {
            if (handler instanceof HttpReceiverBlockHandler receiverHandler) {
                //ADD TO EXISTING HADNLER
                receiverHandler.addBlockEntity(entity);
                return;
            }
            //ERROR BECAUSE URL ALREADY EXISTS
            return;
        }
        HttpReceiverBlockHandler newHandler = new HttpReceiverBlockHandler(entity, validatedUrl);
        CommonClass.HTTP_SERVER.registerHandler(newHandler);
    }

    private static String validateUrl(String url){
        if(!url.startsWith("/")) return "/"+url;
        return url;
    }

    private void addBlockEntity(HttpReceiverBlockEntity entity){
        this.entityList.add(entity);
    }

    private void removeBlockEntity(HttpReceiverBlockEntity entity){
        this.entityList.remove(entity);
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
        this.writeResponse(exchange);
    }

    private void writeResponse(HttpExchange exchange) throws IOException {
        String redirect = this.getRedirect(exchange);
        if(redirect != null){
            exchange.getResponseHeaders().add("Location", redirect);
            exchange.sendResponseHeaders(308, 0);
        }else{
            String response = "OK";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        exchange.close();
    }

    //REDIRECTS CLIENT, IF ONE OF THE BLOCKS HAS A REDIRECTION SETTING
    //BECAUSE OF MENY BLOCKS HAVING SAME ENDPOINT URL, IT TAKES THE LAST FOUND REDIRECT
    //URL
    private String getRedirect(HttpExchange exchange) throws IOException {
        String redirectUrl = null;
        for(HttpReceiverBlockEntity entity : this.entityList){
            if(entity.getValues().redirectClientUrl != null &&
                    !entity.getValues().redirectClientUrl.isEmpty()){
                redirectUrl = entity.getValues().redirectClientUrl;
            }
        }
        return redirectUrl;
    }

}
