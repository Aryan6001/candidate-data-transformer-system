package com.eightfold.transformer.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConfigLoader {

    private final ObjectMapper objectMapper;

    public ConfigLoader() {

        objectMapper = new ObjectMapper();

        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false
        );
    }

    /**
     * Loads configuration from JSON file.
     *
     * @param configPath Path to config.json
     * @return Config object
     * @throws IOException if file cannot be read
     */
    public Config load(String configPath) throws IOException {

        File file = new File(configPath);

        if (!file.exists()) {
            throw new IOException("Configuration file not found: " + configPath);
        }

        return objectMapper.readValue(file, Config.class);
    }
}