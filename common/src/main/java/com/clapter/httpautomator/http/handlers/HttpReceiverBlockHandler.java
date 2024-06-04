package com.clapter.httpautomator.http.handlers;

import com.clapter.httpautomator.CommonClass;
import com.clapter.httpautomator.blockentity.HttpReceiverBlockEntity;
import com.clapter.httpautomator.http.api.IHttpHandler;
import com.clapter.httpautomator.utils.JsonUtils;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
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
            //ERROR BECAUSE URL ALREADY EXISTS AND IS NOT A RECEIVER HANDLER
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
        entityList.forEach((blockEntity) -> {
            if(this.shouldEntityBePowered(exchange, blockEntity)){
                blockEntity.onSignal();
            }
        });
        this.writeResponse(exchange);
    }

    private boolean shouldEntityBePowered(HttpExchange exchange, HttpReceiverBlockEntity entity){
        try {
            Map<String, String> params = getAllParameters(exchange);
            if(params.isEmpty()){
                return entity.getValues().parameterMap.isEmpty();
            }
            return this.allParametersValidForEntity(params, entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> getAllParameters(HttpExchange exchange) throws IOException {
        Map<String, String> parameters = new HashMap<>();
        // Get POST parameters
        if ("post".equalsIgnoreCase(exchange.getRequestMethod())) {
            InputStreamReader isr =  new InputStreamReader(exchange.getRequestBody(),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            int b;
            StringBuilder buf = new StringBuilder(512);
            while ((b = br.read()) != -1) {
                buf.append((char) b);
            }
            String requestBody = buf.toString();
            br.close();
            isr.close();
            parameters.putAll(parseQueryString(exchange.getRequestHeaders(), requestBody));
        }
        //get GET parameters
        if("get".equalsIgnoreCase(exchange.getRequestMethod())){
            URI requestURI = exchange.getRequestURI();
            String query = requestURI.getQuery();
            if (query != null) {
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    String[] keyValue = pair.split("=");
                    String key = URLDecoder.decode(keyValue[0], "UTF-8");
                    String value = "";
                    if (keyValue.length > 1) {
                        value = URLDecoder.decode(keyValue[1], "UTF-8");
                    }
                    parameters.put(key, value);
                }
            }
        }
        return parameters;
    }

    private Map<String, String> parseQueryString(Headers requestHeaders, String requestBody) {
        //TODO: CHECK IF REQUEST BODY IS VALID JSON
        return JsonUtils.getParametersAsMap(requestBody);
    }

    private boolean allParametersValidForEntity(Map<String, String> params, HttpReceiverBlockEntity entity) {
        for(Map.Entry<String, String> entry : entity.getValues().parameterMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            if(!params.containsKey(key)) return false;
            else if(!params.get(key).equals(value)) return false;
        }
        return true;
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
