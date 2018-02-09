package com.butilov.services;

import com.butilov.entities.RateObject;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

/**
 * Created by Dmitry Butilov
 * on 09.02.18.
 */
public class RatesDeserializerService implements JsonDeserializer<RateObject> {

    public RateObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        RateObject rateObject = null;
        if (json.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> entries = json.getAsJsonObject().entrySet();
            if (entries.size() > 0) {
                Map.Entry<String, JsonElement> entry = entries.iterator().next();
                rateObject = new RateObject(entry.getKey(), entry.getValue().getAsDouble());
            }
        }
        return rateObject;
    }
}