package com.eightfold.transformer.normalizer;

public class NameNormalizer {

    private NameNormalizer() {
    }

    public static String normalize(String name) {

        if (name == null || name.isBlank()) {
            return null;
        }

        name = name.trim().replaceAll("\\s+", " ");

        StringBuilder builder = new StringBuilder();

        for (String word : name.split(" ")) {

            if (word.isBlank()) {
                continue;
            }

            builder.append(
                    Character.toUpperCase(word.charAt(0))
            );

            if (word.length() > 1) {
                builder.append(
                        word.substring(1).toLowerCase()
                );
            }

            builder.append(" ");
        }

        return builder.toString().trim();
    }
}