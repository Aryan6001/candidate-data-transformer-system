package com.eightfold.transformer.normalizer;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DateNormalizer {

    private static final List<DateTimeFormatter> FORMATTERS = List.of(

            DateTimeFormatter.ofPattern("MM/yyyy"),
            DateTimeFormatter.ofPattern("MMM yyyy"),
            DateTimeFormatter.ofPattern("MMMM yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM"),
            DateTimeFormatter.ofPattern("yyyy/MM")

    );

    private DateNormalizer() {
    }

    public static String normalize(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        value = value.trim();

        if (value.equalsIgnoreCase("Present")) {
            return "Present";
        }

        for (DateTimeFormatter formatter : FORMATTERS) {

            try {

                YearMonth ym = YearMonth.parse(value, formatter);

                return ym.toString();

            } catch (DateTimeParseException ignored) {
            }

            try {

                LocalDate date = LocalDate.parse(value, formatter);

                return date.format(DateTimeFormatter.ofPattern("yyyy-MM"));

            } catch (DateTimeParseException ignored) {
            }
        }

        return value;
    }
}