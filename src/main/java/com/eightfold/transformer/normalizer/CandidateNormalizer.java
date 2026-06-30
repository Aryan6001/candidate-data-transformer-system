package com.eightfold.transformer.normalizer;

import com.eightfold.transformer.model.Candidate;
import com.eightfold.transformer.model.Contact;
import com.eightfold.transformer.model.Education;
import com.eightfold.transformer.model.Experience;
import com.eightfold.transformer.model.Location;
import com.eightfold.transformer.model.Skill;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class CandidateNormalizer {

    private CandidateNormalizer() {
        // Prevent instantiation
    }

    /**
     * Normalize all candidate fields.
     */
    public static void normalize(Candidate candidate) {

        if (candidate == null) {
            return;
        }

        normalizeName(candidate);
        normalizeContact(candidate);
        normalizeSkills(candidate);
        normalizeExperience(candidate);
        normalizeEducation(candidate);
    }

    /**
     * Normalize candidate name.
     */
    private static void normalizeName(Candidate candidate) {

        if (candidate.getFullName() != null) {

            candidate.setFullName(
                    NameNormalizer.normalize(candidate.getFullName())
            );
        }
    }

    /**
     * Normalize emails and phone numbers.
     */
    private static void normalizeContact(Candidate candidate) {

        Contact contact = candidate.getContact();

        if (contact == null) {
            return;
        }

        // Normalize Emails
        if (contact.getEmails() != null) {

            Set<String> emails = new LinkedHashSet<>();

            for (String email : contact.getEmails()) {

                if (email != null && !email.isBlank()) {
                    emails.add(email.trim().toLowerCase());
                }
            }

            contact.setEmails(new ArrayList<>(emails));
        }

        // Normalize Phones
        if (contact.getPhones() != null) {

            Set<String> phones = new LinkedHashSet<>();

            for (String phone : contact.getPhones()) {

                String normalized = PhoneNormalizer.normalize(phone);

                if (normalized != null) {
                    phones.add(normalized);
                }
            }

            contact.setPhones(new ArrayList<>(phones));
        }
    }

    /**
     * Normalize skills and remove duplicates.
     */
    private static void normalizeSkills(Candidate candidate) {

        if (candidate.getSkills() == null) {
            return;
        }

        List<Skill> normalizedSkills = new ArrayList<>();
        Set<String> uniqueSkills = new LinkedHashSet<>();

        for (Skill skill : candidate.getSkills()) {

            if (skill == null || skill.getName() == null) {
                continue;
            }

            String normalizedSkill =
                    SkillNormalizer.normalize(skill.getName());

            if (normalizedSkill != null &&
                    uniqueSkills.add(normalizedSkill)) {

                skill.setName(normalizedSkill);
                normalizedSkills.add(skill);
            }
        }

        candidate.setSkills(normalizedSkills);
    }

    /**
     * Normalize experience information.
     */
    private static void normalizeExperience(Candidate candidate) {

        if (candidate.getExperience() == null) {
            return;
        }

        for (Experience experience : candidate.getExperience()) {

            if (experience == null) {
                continue;
            }

            // Normalize Country
            Location location = experience.getLocation();

            if (location != null && location.getCountry() != null) {

                location.setCountry(
                        CountryNormalizer.normalize(
                                location.getCountry()
                        )
                );
            }

            // Normalize Dates
            if (experience.getStartDate() != null) {

                experience.setStartDate(
                        DateNormalizer.normalize(
                                experience.getStartDate()
                        )
                );
            }

            if (experience.getEndDate() != null) {

                experience.setEndDate(
                        DateNormalizer.normalize(
                                experience.getEndDate()
                        )
                );
            }
        }
    }

    /**
     * Normalize education information.
     */
    private static void normalizeEducation(Candidate candidate) {

        if (candidate.getEducation() == null) {
            return;
        }

        for (Education education : candidate.getEducation()) {

            if (education == null) {
                continue;
            }

            if (education.getInstitution() != null) {

                education.setInstitution(
                        education.getInstitution().trim()
                );
            }

            if (education.getDegree() != null) {

                education.setDegree(
                        education.getDegree().trim()
                );
            }

            if (education.getField() != null) {

                education.setField(
                        education.getField().trim()
                );
            }
        }
    }
}