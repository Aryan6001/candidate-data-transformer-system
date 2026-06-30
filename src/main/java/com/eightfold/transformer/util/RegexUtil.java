package com.eightfold.transformer.util;

import java.util.regex.Pattern;

public final class RegexUtil {

    private RegexUtil() {
    }

    public static final Pattern EMAIL_PATTERN =
            Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

    public static final Pattern PHONE_PATTERN =
            Pattern.compile("(\\+?\\d[\\d\\s\\-()]{8,15})");

    public static final Pattern LINKEDIN_PATTERN =
            Pattern.compile("https?://(www\\.)?linkedin\\.com/\\S+");

    public static final Pattern GITHUB_PATTERN =
            Pattern.compile("https?://(www\\.)?github\\.com/\\S+");

    public static final Pattern URL_PATTERN =
            Pattern.compile("https?://\\S+");

    public static final Pattern YEAR_PATTERN =
            Pattern.compile("\\b(19|20)\\d{2}\\b");
}