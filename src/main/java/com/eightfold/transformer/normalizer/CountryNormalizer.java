package com.eightfold.transformer.normalizer;

import java.util.HashMap;
import java.util.Map;

public class CountryNormalizer {

    private static final Map<String, String> COUNTRY_MAP = new HashMap<>();

    static {

        COUNTRY_MAP.put("india", "IN");
        COUNTRY_MAP.put("ind", "IN");

        COUNTRY_MAP.put("united states", "US");
        COUNTRY_MAP.put("usa", "US");

        COUNTRY_MAP.put("united kingdom", "GB");
        COUNTRY_MAP.put("uk", "GB");

        COUNTRY_MAP.put("canada", "CA");
        COUNTRY_MAP.put("australia", "AU");

    }

    private CountryNormalizer() {
    }

    public static String normalize(String country) {

        if (country == null || country.isBlank()) {
            return null;
        }

        return COUNTRY_MAP.getOrDefault(
                country.trim().toLowerCase(),
                country.trim()
        );
    }
}