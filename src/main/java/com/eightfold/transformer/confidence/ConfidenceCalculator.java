package com.eightfold.transformer.confidence;

import com.eightfold.transformer.constants.ConfidenceConstants;
import com.eightfold.transformer.constants.SourceType;
import com.eightfold.transformer.model.Candidate;
import com.eightfold.transformer.model.Provenance;
import com.eightfold.transformer.model.Skill;

import java.util.List;

public class ConfidenceCalculator {

    private ConfidenceCalculator() {
    }

    /**
     * Calculates overall confidence for the candidate.
     */
    public static double calculateOverallConfidence(Candidate candidate) {

        double total = 0.0;
        int count = 0;

        if (candidate.getProvenance() != null) {

            for (Provenance provenance : candidate.getProvenance()) {

                total += getSourceConfidence(provenance.getSource());
                count++;
            }
        }

        if (count == 0) {
            return ConfidenceConstants.DEFAULT;
        }

        double confidence = total / count;

        confidence = Math.round(confidence * 100.0) / 100.0;

        candidate.setOverallConfidence(confidence);

        return confidence;
    }

    /**
     * Assign confidence to every extracted skill.
     */
    public static void assignSkillConfidence(List<Skill> skills,
                                             SourceType source) {

        if (skills == null) {
            return;
        }

        double confidence = getSourceConfidence(source);

        for (Skill skill : skills) {
            skill.setConfidence(confidence);
        }
    }

    /**
     * Returns confidence score based on source.
     */
    public static double getSourceConfidence(SourceType source) {

        if (source == null) {
            return ConfidenceConstants.DEFAULT;
        }

        switch (source) {

            case RECRUITER_CSV:
                return ConfidenceConstants.RECRUITER_CSV;

            case RESUME:
                return ConfidenceConstants.RESUME;

            default:
                return ConfidenceConstants.DEFAULT;
        }
    }

}