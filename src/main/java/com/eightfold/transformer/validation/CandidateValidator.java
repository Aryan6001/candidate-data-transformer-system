package com.eightfold.transformer.validation;

import com.eightfold.transformer.model.Candidate;

public class CandidateValidator {

    private CandidateValidator() {
    }

    /**
     * Validates mandatory candidate fields.
     *
     * @param candidate Candidate object
     * @throws IllegalArgumentException if validation fails
     */
    public static void validate(Candidate candidate) {

        if (candidate == null) {
            throw new IllegalArgumentException("Candidate cannot be null.");
        }

        if (isBlank(candidate.getFullName())) {
            throw new IllegalArgumentException("Candidate name is required.");
        }

        if (candidate.getContact() == null) {
            throw new IllegalArgumentException("Contact information is missing.");
        }

        boolean hasEmail =
                candidate.getContact().getEmails() != null &&
                !candidate.getContact().getEmails().isEmpty();

        boolean hasPhone =
                candidate.getContact().getPhones() != null &&
                !candidate.getContact().getPhones().isEmpty();

        if (!hasEmail && !hasPhone) {
            throw new IllegalArgumentException(
                    "Candidate must have at least one email or phone."
            );
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}