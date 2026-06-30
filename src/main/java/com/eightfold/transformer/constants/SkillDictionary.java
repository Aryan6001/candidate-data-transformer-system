package com.eightfold.transformer.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class SkillDictionary {

    private static final Map<String, String> SKILLS = new HashMap<>();

    static {

        // Java
        SKILLS.put("java", "Java");
        SKILLS.put("core java", "Java");

        // Spring
        SKILLS.put("spring", "Spring");
        SKILLS.put("springboot", "Spring Boot");
        SKILLS.put("spring boot", "Spring Boot");

        // C++
        SKILLS.put("c++", "C++");
        SKILLS.put("cplusplus", "C++");
        SKILLS.put("c plus plus", "C++");

        // Python
        SKILLS.put("python", "Python");

        // JavaScript
        SKILLS.put("javascript", "JavaScript");
        SKILLS.put("js", "JavaScript");

        // SQL
        SKILLS.put("sql", "SQL");
        SKILLS.put("mysql", "MySQL");
        SKILLS.put("postgresql", "PostgreSQL");

        // Web
        SKILLS.put("html", "HTML");
        SKILLS.put("css", "CSS");

        // Cloud
        SKILLS.put("aws", "AWS");
        SKILLS.put("amazon web services", "AWS");

        // DevOps
        SKILLS.put("docker", "Docker");
        SKILLS.put("kubernetes", "Kubernetes");

        // Version Control
        SKILLS.put("git", "Git");
        SKILLS.put("github", "GitHub");

        // Data
        SKILLS.put("machine learning", "Machine Learning");
        SKILLS.put("deep learning", "Deep Learning");
    }

    private SkillDictionary() {
    }

    public static String normalize(String skill) {

        if (skill == null || skill.isBlank()) {
            return null;
        }

        return SKILLS.getOrDefault(
                skill.trim().toLowerCase(),
                skill.trim()
        );
    }


	public static Map<String, String> getSkills() {
		 return Collections.unmodifiableMap(SKILLS);
	}

}