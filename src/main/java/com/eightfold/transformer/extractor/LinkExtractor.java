package com.eightfold.transformer.extractor;

import com.eightfold.transformer.model.Link;

import java.util.regex.*;

public class LinkExtractor {

    private static final Pattern URL_PATTERN =
            Pattern.compile("(https?://\\S+)");

    public static Link extract(String text) {

        Link links = new Link();

        Matcher matcher = URL_PATTERN.matcher(text);

        while (matcher.find()) {

            String url = matcher.group();

            if (url.contains("linkedin.com")) {
                links.setLinkedin(url);
            }
            else if (url.contains("github.com")) {
                links.setGithub(url);
            }
            else {
                links.setPortfolio(url);
            }
        }

        return links;
    }
}