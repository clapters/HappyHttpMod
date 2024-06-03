package com.clapter.httpautomator.utils;

import javax.json.*;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    public static Map<String, String> getParametersAsMap(InputStream requestBody) {
        Map<String, String> parameters = new HashMap<>();
        try (JsonReader jsonReader = Json.createReader(requestBody)) {
            JsonObject jsonObject = jsonReader.readObject();
            for (Map.Entry<String, JsonValue> entry : jsonObject.entrySet()) {
                if(!entry.getValue().getValueType().equals(JsonValue.ValueType.STRING))continue; //TODO: Stringify
                parameters.put(entry.getKey(), ((JsonString)entry.getValue()).getString());
            }
        }
        return parameters;
    }

}
