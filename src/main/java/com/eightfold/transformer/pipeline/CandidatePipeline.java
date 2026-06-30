package com.eightfold.transformer.pipeline;

import com.eightfold.transformer.config.Config;
import com.eightfold.transformer.config.ConfigLoader;
import com.eightfold.transformer.confidence.ConfidenceCalculator;
import com.eightfold.transformer.constants.SourceType;
import com.eightfold.transformer.merger.CandidateMerger;
import com.eightfold.transformer.model.Candidate;
import com.eightfold.transformer.normalizer.CandidateNormalizer;
import com.eightfold.transformer.parser.CsvParser;
import com.eightfold.transformer.parser.DocxResumeParser;
import com.eightfold.transformer.parser.PdfResumeParser;
import com.eightfold.transformer.parser.ResumeParser;
import com.eightfold.transformer.projection.ProjectionEngine;
import com.eightfold.transformer.provenance.ProvenanceTracker;
import com.eightfold.transformer.validation.CandidateValidator;
import com.eightfold.transformer.validation.JsonSchemaValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class CandidatePipeline {

    private final ConfigLoader configLoader = new ConfigLoader();
    private final CsvParser csvParser = new CsvParser();
    private final CandidateMerger merger = new CandidateMerger();
    private final ProjectionEngine projectionEngine = new ProjectionEngine();
    private final ObjectMapper mapper = new ObjectMapper();

    public void execute(String csvFile,
                        String resumeFile,
                        String configFile) throws Exception {

        // 1. Load Configuration
        Config config = configLoader.load(configFile);

        // 2. Parse CSV
        Candidate csvCandidate = csvParser.parse(csvFile);

        ProvenanceTracker.generate(
                csvCandidate,
                SourceType.RECRUITER_CSV,
                "structured_csv"
        );

        // 3. Parse Resume
        ResumeParser resumeParser = getResumeParser(resumeFile);

        Candidate resumeCandidate = resumeParser.parse(resumeFile);

        ProvenanceTracker.generate(
                resumeCandidate,
                SourceType.RESUME,
                "resume_parsing"
        );

        // 4. Merge Candidates
        Candidate candidate =
                merger.merge(csvCandidate, resumeCandidate);

        // 5. Normalize
        CandidateNormalizer.normalize(candidate);

        // 6. Calculate Confidence
        ConfidenceCalculator.calculateOverallConfidence(candidate);

        // 7. Validate Candidate
        CandidateValidator.validate(candidate);

        // 8. Apply Projection
        ObjectNode output =
                projectionEngine.project(candidate, config);

        // 9. Validate JSON Schema
        JsonSchemaValidator.validate(
                output,
                "src/main/resources/candidate-schema.json"
        );

        // 10. Write Output
        writeOutput(output);

        System.out.println("candidate.json generated successfully.");
    }

    private ResumeParser getResumeParser(String file) {

        if (file.toLowerCase().endsWith(".pdf")) {
            return new PdfResumeParser();
        }

        if (file.toLowerCase().endsWith(".docx")) {
            return new DocxResumeParser();
        }

        throw new IllegalArgumentException(
                "Unsupported resume format: " + file
        );
    }

    private void writeOutput(ObjectNode output) throws IOException {

        File outDir = new File("output");

        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(
                        new File(outDir, "candidate.json"),
                        output
                );
    }
}