package com.butilov.services;

import com.butilov.entities.RateObject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Dmitry Butilov
 * on 09.02.18.
 */
@Service
public class RatesDeserializerService extends JsonDeserializer<RateObject> {

    @Override
    public RateObject deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Map.Entry<String, JsonNode> map = node.fields().next();
        return new RateObject(map.getKey(), map.getValue().asDouble());
    }
}