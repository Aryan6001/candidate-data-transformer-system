package com.eightfold.transformer.extractor;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.*;
import java.util.regex.*;

public class PhoneExtractor {

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("(\\+?\\d[\\d\\s\\-()]{8,15})");

    public static List<String> extract(String text) {

        Set<String> phones = new LinkedHashSet<>();

        Matcher matcher = PHONE_PATTERN.matcher(text);

        PhoneNumberUtil util = PhoneNumberUtil.getInstance();

        while (matcher.find()) {

            String phone = matcher.group().trim();

            try {

                Phonenumber.PhoneNumber number =
                        util.parse(phone, "IN");

                phones.add(
                        util.format(
                                number,
                                PhoneNumberUtil.PhoneNumberFormat.E164
                        )
                );

            } catch (Exception ignored) {
            }

        }

        return new ArrayList<>(phones);
    }
}