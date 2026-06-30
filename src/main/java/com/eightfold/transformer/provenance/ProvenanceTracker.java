package com.eightfold.transformer.provenance;

import java.util.ArrayList;

import com.eightfold.transformer.confidence.ConfidenceCalculator;
import com.eightfold.transformer.constants.SourceType;
import com.eightfold.transformer.model.Candidate;
import com.eightfold.transformer.model.Provenance;

public class ProvenanceTracker {

    private ProvenanceTracker() {
    }

    /**
     * Adds provenance for a field.
     */
    public static void addProvenance(Candidate candidate,
                                     String field,
                                     SourceType source,
                                     String method) {

        if (candidate.getProvenance() == null) {
            candidate.setProvenance(new ArrayList<>());
        }

        Provenance provenance = new Provenance();

        provenance.setField(field);
        provenance.setSource(source);
        provenance.setMethod(method);
        provenance.setConfidence(
                ConfidenceCalculator.getSourceConfidence(source)
        );

        candidate.getProvenance().add(provenance);
    }

    /**
     * Automatically generate provenance for populated fields.
     */
    public static void generate(Candidate candidate,
                                SourceType source,
                                String method) {

        if (candidate.getFullName() != null)
            addProvenance(candidate, "full_name", source, method);

        if (candidate.getContact() != null) {

            if (candidate.getContact().getEmails() != null &&
                    !candidate.getContact().getEmails().isEmpty()) {

                addProvenance(candidate, "emails", source, method);
            }

            if (candidate.getContact().getPhones() != null &&
                    !candidate.getContact().getPhones().isEmpty()) {

                addProvenance(candidate, "phones", source, method);
            }
        }

        if (candidate.getLocation() != null)
            addProvenance(candidate, "location", source, method);

        if (candidate.getSkills() != null &&
                !candidate.getSkills().isEmpty()) {

            addProvenance(candidate, "skills", source, method);
        }

        if (candidate.getExperience() != null &&
                !candidate.getExperience().isEmpty()) {

            addProvenance(candidate, "experience", source, method);
        }

        if (candidate.getEducation() != null &&
                !candidate.getEducation().isEmpty()) {

            addProvenance(candidate, "education", source, method);
        }

        if (candidate.getLinks() != null)
            addProvenance(candidate, "links", source, method);

        if (candidate.getHeadline() != null)
            addProvenance(candidate, "headline", source, method);

        if (candidate.getYearsExperience() != null)
            addProvenance(candidate, "years_experience", source, method);
    }

}