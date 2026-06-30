package com.eightfold.transformer.parser;

import com.eightfold.transformer.model.Candidate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResumeParserTest {

    @Test
    void shouldParsePdfResumeSuccessfully() throws Exception {

        ResumeParser parser = new PdfResumeParser();

        Candidate candidate =
                parser.parse("src/test/resources/resume.pdf");

        assertNotNull(candidate);

        assertNotNull(candidate.getContact());

        assertFalse(
                candidate.getContact().getEmails().isEmpty()
        );

        assertFalse(
                candidate.getSkills().isEmpty()
        );
    }

    @Test
    void shouldParseDocxResumeSuccessfully() throws Exception {

        ResumeParser parser = new DocxResumeParser();

        Candidate candidate =
                parser.parse("src/test/resources/resume.docx");

        assertNotNull(candidate);

        assertNotNull(candidate.getContact());

        assertFalse(
                candidate.getContact().getEmails().isEmpty()
        );

        assertFalse(
                candidate.getSkills().isEmpty()
        );
    }

    @Test
    void shouldFailForInvalidResumeFile() {

        ResumeParser parser = new PdfResumeParser();

        assertThrows(Exception.class, () ->
                parser.parse("invalid.pdf")
        );
    }
}