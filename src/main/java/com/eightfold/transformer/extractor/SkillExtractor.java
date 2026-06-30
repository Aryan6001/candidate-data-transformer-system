package com.eightfold.transformer.extractor;

import com.eightfold.transformer.constants.SkillDictionary;
import com.eightfold.transformer.model.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillExtractor {

    public static List<Skill> extract(String text) {

        List<Skill> skills = new ArrayList<>();

        String lower = text.toLowerCase();

        for (String alias : SkillDictionary.getSkills().keySet()) {

            if (lower.contains(alias.toLowerCase())) {

                Skill skill = new Skill();
                skill.setName(SkillDictionary.normalize(alias));
                skill.setConfidence(0.90);
                skill.setSources(List.of("Resume"));

                skills.add(skill);
            }
        }

        return skills;
    }
}