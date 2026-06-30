package com.eightfold.transformer.extractor;

import java.util.*;
import java.util.regex.*;

public class EmailExtractor {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

    public static List<String> extract(String text) {

        Set<String> emails = new LinkedHashSet<>();

        Matcher matcher = EMAIL_PATTERN.matcher(text);

        while (matcher.find()) {
            emails.add(matcher.group());
        }

        return new ArrayList<>(emails);
    }
}