package com.eightfold.transformer.normalizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNormalizerTest {

    @Test
    void shouldNormalizeIndianPhoneNumber() {

        String phone = "9876543210";

        String normalized = PhoneNormalizer.normalize(phone);

        assertEquals("+919876543210", normalized);
    }

    @Test
    void shouldNormalizePhoneWithCountryCode() {

        String phone = "+91 98765 43210";

        String normalized = PhoneNormalizer.normalize(phone);

        assertEquals("+919876543210", normalized);
    }

    @Test
    void shouldNormalizePhoneWithSpaces() {

        String phone = "98765 43210";

        String normalized = PhoneNormalizer.normalize(phone);

        assertEquals("+919876543210", normalized);
    }

    @Test
    void shouldNormalizePhoneWithHyphens() {

        String phone = "98765-43210";

        String normalized = PhoneNormalizer.normalize(phone);

        assertEquals("+919876543210", normalized);
    }

    @Test
    void shouldReturnNullForNullInput() {

        assertNull(
                PhoneNormalizer.normalize(null)
        );
    }

    @Test
    void shouldReturnNullForBlankInput() {

        assertNull(
                PhoneNormalizer.normalize(" ")
        );
    }

    @Test
    void shouldReturnOriginalForInvalidPhone() {

        String invalid = "123";

        assertEquals(
                invalid,
                PhoneNormalizer.normalize(invalid)
        );
    }

    @Test
    void shouldHandleInternationalNumber() {

        String phone = "+1 415 555 2671";

        String normalized = PhoneNormalizer.normalize(phone);

        assertNotNull(normalized);
    }

}