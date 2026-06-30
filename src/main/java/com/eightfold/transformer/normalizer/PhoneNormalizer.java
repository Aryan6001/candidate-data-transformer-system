package com.eightfold.transformer.normalizer;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class PhoneNormalizer {

    private static final PhoneNumberUtil PHONE_UTIL = PhoneNumberUtil.getInstance();

    private PhoneNormalizer() {
    }

    public static String normalize(String phone) {

        if (phone == null || phone.isBlank()) {
            return null;
        }

        try {

            Phonenumber.PhoneNumber number =
                    PHONE_UTIL.parse(phone, "IN");

            if (!PHONE_UTIL.isValidNumber(number)) {
                return phone;
            }

            return PHONE_UTIL.format(
                    number,
                    PhoneNumberUtil.PhoneNumberFormat.E164
            );

        } catch (Exception e) {
            return phone;
        }
    }
}