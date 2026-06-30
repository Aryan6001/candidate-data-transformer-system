package com.eightfold.transformer.merger;

import com.eightfold.transformer.model.Candidate;
import com.eightfold.transformer.model.Contact;
import com.eightfold.transformer.model.Skill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CandidateMergerTest {

    private CandidateMerger merger;

    @BeforeEach
    void setUp() {
        merger = new CandidateMerger();
    }

    @Test
    void shouldMergeCandidateBasicInformation() {

        Contact csvContact = new Contact();
        csvContact.setEmails(List.of("john@gmail.com"));
        csvContact.setPhones(List.of("+919876543210"));

        Candidate csvCandidate = new Candidate();
        csvCandidate.setCandidateId("1001");
        csvCandidate.setFullName("John Smith");
        csvCandidate.setHeadline("Software Engineer");
        csvCandidate.setContact(csvContact);

        Contact resumeContact = new Contact();
        resumeContact.setEmails(List.of("john@gmail.com"));
        resumeContact.setPhones(List.of("+919876543210"));

        Candidate resumeCandidate = new Candidate();
        resumeCandidate.setFullName("John Smith");
        resumeCandidate.setHeadline("Java Developer");
        resumeCandidate.setContact(resumeContact);

        Candidate merged = merger.merge(csvCandidate, resumeCandidate);

        assertNotNull(merged);
        assertEquals("1001", merged.getCandidateId());
        assertEquals("John Smith", merged.getFullName());
        assertEquals("Software Engineer", merged.getHeadline());
    }

    @Test
    void shouldMergeEmailsWithoutDuplicates() {

        Contact csvContact = new Contact();
        csvContact.setEmails(List.of("john@gmail.com"));

        Candidate csv = new Candidate();
        csv.setContact(csvContact);

        Contact resumeContact = new Contact();
        resumeContact.setEmails(List.of(
                "john@gmail.com",
                "john.smith@gmail.com"));

        Candidate resume = new Candidate();
        resume.setContact(resumeContact);

        Candidate merged = merger.merge(csv, resume);

        assertEquals(2, merged.getContact().getEmails().size());
        assertTrue(merged.getContact().getEmails().contains("john@gmail.com"));
        assertTrue(merged.getContact().getEmails().contains("john.smith@gmail.com"));
    }

    @Test
    void shouldMergeSkillsWithoutDuplicates() {

        Skill java = new Skill();
        java.setName("Java");

        Skill spring = new Skill();
        spring.setName("Spring Boot");

        Candidate csv = new Candidate();
        csv.setSkills(List.of(java));

        Candidate resume = new Candidate();
        resume.setSkills(List.of(java, spring));

        Candidate merged = merger.merge(csv, resume);

        assertNotNull(merged.getSkills());
        assertEquals(2, merged.getSkills().size());
    }

    @Test
    void shouldPreferCsvHeadlineWhenAvailable() {

        Candidate csv = new Candidate();
        csv.setHeadline("Senior Java Developer");

        Candidate resume = new Candidate();
        resume.setHeadline("Java Developer");

        Candidate merged = merger.merge(csv, resume);

        assertEquals("Senior Java Developer", merged.getHeadline());
    }

    @Test
    void shouldUseResumeValueWhenCsvValueIsMissing() {

        Candidate csv = new Candidate();
        csv.setHeadline(null);

        Candidate resume = new Candidate();
        resume.setHeadline("Java Developer");

        Candidate merged = merger.merge(csv, resume);

        assertEquals("Java Developer", merged.getHeadline());
    }

    @Test
    void shouldHandleEmptyListsGracefully() {

        Candidate csv = new Candidate();
        csv.setSkills(List.of());

        Candidate resume = new Candidate();
        resume.setSkills(List.of());

        Candidate merged = merger.merge(csv, resume);

        assertNotNull(merged.getSkills());
        assertTrue(merged.getSkills().isEmpty());
    }

    @Test
    void shouldMergePhoneNumbersWithoutDuplicates() {

        Contact csvContact = new Contact();
        csvContact.setPhones(List.of("+919876543210"));

        Candidate csv = new Candidate();
        csv.setContact(csvContact);

        Contact resumeContact = new Contact();
        resumeContact.setPhones(List.of(
                "+919876543210",
                "+919999999999"));

        Candidate resume = new Candidate();
        resume.setContact(resumeContact);

        Candidate merged = merger.merge(csv, resume);

        assertEquals(2, merged.getContact().getPhones().size());
    }

    @Test
    void shouldThrowExceptionWhenBothCandidatesAreNull() {

        assertThrows(
                NullPointerException.class,
                () -> merger.merge(null, null)
        );
    }
}