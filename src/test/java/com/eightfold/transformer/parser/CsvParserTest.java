package com.eightfold.transformer.parser;

import com.eightfold.transformer.model.Candidate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CsvParserTest {

    private final CsvParser csvParser = new CsvParser();

    @Test
    void shouldParseRecruiterCsvSuccessfully() throws Exception {

        String csvFile = "src/test/resources/recruiter.csv";

        Candidate candidate = csvParser.parse(csvFile);

        assertNotNull(candidate);

        assertEquals("1001", candidate.getCandidateId());

        assertEquals("John Smith", candidate.getFullName());

        assertNotNull(candidate.getContact());

        assertEquals(
                "john.smith@gmail.com",
                candidate.getContact().getEmails().get(0)
        );

        assertEquals(
                "+919876543210",
                candidate.getContact().getPhones().get(0)
        );
    }

    @Test
    void shouldThrowExceptionWhenFileDoesNotExist() {

        assertThrows(Exception.class, () ->
                csvParser.parse("invalid.csv")
        );
    }
}