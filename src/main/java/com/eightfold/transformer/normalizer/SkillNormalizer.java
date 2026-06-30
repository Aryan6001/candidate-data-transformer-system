package com.eightfold.transformer.normalizer;

import com.eightfold.transformer.constants.SkillDictionary;

public class SkillNormalizer {

    private SkillNormalizer() {
    }

    public static String normalize(String skill) {

        return SkillDictionary.normalize(skill);

    }

}