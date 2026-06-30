package com.eightfold.transformer.parser;

import com.eightfold.transformer.model.Candidate;

import java.io.IOException;

public interface ResumeParser {

    /**
     * Parses a resume file and returns a Candidate object.
     *
     * @param filePath Resume file path
     * @return Candidate
     * @throws IOException if parsing fails
     */
    Candidate parse(String filePath) throws IOException;
}