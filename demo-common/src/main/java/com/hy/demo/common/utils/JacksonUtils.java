package com.hy.demo.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;

/**
 * JacksonUtils
 *
 * @author yuhaiyang
 * @date 2018/6/4
 */
public class JacksonUtils {

    public static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }

    public static ObjectNode put(Map<String, String> map) {
        ObjectNode objectNode = createObjectNode();
        map.forEach((key, value) -> {
            objectNode.put(key, value);
        });
        return objectNode;
    }

    public static ObjectNode putNode(Map<String, JsonNode> map) {
        ObjectNode objectNode = createObjectNode();
        map.forEach((key, value) -> {
            objectNode.set(key, value);
        });
        return objectNode;
    }

    public static ObjectNode put(String key, String value) {
        ObjectNode objectNode = createObjectNode();
        objectNode.put(key, value);
        return objectNode;
    }

    public static ObjectNode putNode(String key, JsonNode value) {
        ObjectNode objectNode = createObjectNode();
        objectNode.set(key, value);
        return objectNode;
    }

    public static String writeValue(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            // TODO: 2018/6/4
            e.printStackTrace();
        }
        return null;
    }

}
