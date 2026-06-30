package com.eightfold.transformer.pipeline;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PipelineTest {

    private static final String CSV =
            "src/test/resources/recruiter.csv";

    private static final String PDF =
            "src/test/resources/resume.pdf";

    private static final String DOCX =
            "src/test/resources/resume.docx";

    private static final String CONFIG =
            "src/main/resources/config.json";

    private static final String OUTPUT =
            "output/candidate.json";

    @AfterEach
    void cleanup() {

        File output = new File(OUTPUT);

        if (output.exists()) {
            output.delete();
        }
    }

    @Test
    void shouldExecutePipelineWithPdfResume() {

        CandidatePipeline pipeline = new CandidatePipeline();

        assertDoesNotThrow(() ->
                pipeline.execute(
                        CSV,
                        PDF,
                        CONFIG
                ));

        File output = new File(OUTPUT);

        assertTrue(output.exists());
        assertTrue(output.length() > 0);
    }

    @Test
    void shouldExecutePipelineWithDocxResume() {

        CandidatePipeline pipeline = new CandidatePipeline();

        assertDoesNotThrow(() ->
                pipeline.execute(
                        CSV,
                        DOCX,
                        CONFIG
                ));

        File output = new File(OUTPUT);

        assertTrue(output.exists());
        assertTrue(output.length() > 0);
    }

    @Test
    void shouldFailForMissingCsv() {

        CandidatePipeline pipeline = new CandidatePipeline();

        assertThrows(
                Exception.class,
                () -> pipeline.execute(
                        "invalid.csv",
                        PDF,
                        CONFIG
                )
        );
    }

    @Test
    void shouldFailForMissingResume() {

        CandidatePipeline pipeline = new CandidatePipeline();

        assertThrows(
                Exception.class,
                () -> pipeline.execute(
                        CSV,
                        "invalid.pdf",
                        CONFIG
                )
        );
    }

    @Test
    void shouldFailForMissingConfig() {

        CandidatePipeline pipeline = new CandidatePipeline();

        assertThrows(
                Exception.class,
                () -> pipeline.execute(
                        CSV,
                        PDF,
                        "invalid.json"
                )
        );
    }

}
