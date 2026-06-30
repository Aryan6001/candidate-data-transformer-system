package com.eightfold.transformer.normalizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillNormalizerTest {

    @Test
    void shouldNormalizeJavaSkill() {

        String skill = "core java";

        assertEquals(
                "Java",
                SkillNormalizer.normalize(skill)
        );
    }

    @Test
    void shouldNormalizeSpringBootSkill() {

        String skill = "springboot";

        assertEquals(
                "Spring Boot",
                SkillNormalizer.normalize(skill)
        );
    }

    @Test
    void shouldNormalizeAwsSkill() {

        String skill = "amazon web services";

        assertEquals(
                "AWS",
                SkillNormalizer.normalize(skill)
        );
    }

    @Test
    void shouldNormalizeJavascriptSkill() {

        String skill = "js";

        assertEquals(
                "JavaScript",
                SkillNormalizer.normalize(skill)
        );
    }

    @Test
    void shouldIgnoreCase() {

        String skill = "JAVA";

        assertEquals(
                "Java",
                SkillNormalizer.normalize(skill)
        );
    }

    @Test
    void shouldTrimSpaces() {

        String skill = "   Java   ";

        assertEquals(
                "Java",
                SkillNormalizer.normalize(skill)
        );
    }

    @Test
    void shouldReturnOriginalWhenSkillNotFound() {

        String skill = "Apache Kafka";

        assertEquals(
                "Apache Kafka",
                SkillNormalizer.normalize(skill)
        );
    }

    @Test
    void shouldReturnNullForNullInput() {

        assertNull(
                SkillNormalizer.normalize(null)
        );
    }

    @Test
    void shouldReturnNullForBlankInput() {

        assertNull(
                SkillNormalizer.normalize("")
        );
    }

}