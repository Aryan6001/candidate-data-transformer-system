package com.eightfold.transformer.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtil() {
    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }

    public static JsonNode readJson(String filePath) throws IOException {
        return MAPPER.readTree(new File(filePath));
    }

    public static void writeJson(Object object, String filePath) throws IOException {
        MAPPER.writerWithDefaultPrettyPrinter()
                .writeValue(new File(filePath), object);
    }

    public static String toPrettyJson(Object object) throws IOException {
        return MAPPER.writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);
    }
}