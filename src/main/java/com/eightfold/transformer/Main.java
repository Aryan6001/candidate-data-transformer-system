package com.eightfold.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eightfold.transformer.pipeline.CandidatePipeline;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        if (args.length != 6) {
            printUsage();
            return;
        }

        String csvFile = null;
        String resumeFile = null;
        String configFile = null;

        for (int i = 0; i < args.length; i += 2) {

            switch (args[i]) {

                case "--csv":
                    csvFile = args[i + 1];
                    break;

                case "--resume":
                    resumeFile = args[i + 1];
                    break;

                case "--config":
                    configFile = args[i + 1];
                    break;

                default:
                    System.out.println("Unknown argument: " + args[i]);
                    printUsage();
                    return;
            }
        }

        try {

            logger.info("======================================");
            logger.info("Candidate Data Transformer Started");
            logger.info("======================================");

            CandidatePipeline pipeline = new CandidatePipeline();

            pipeline.execute(
                    csvFile,
                    resumeFile,
                    configFile
            );

            logger.info("======================================");
            logger.info("Transformation Completed Successfully");
            logger.info("Output generated in output/candidate.json");
            logger.info("======================================");

        } catch (Exception e) {

            logger.error("Application Failed");

            logger.error(e.getMessage(), e);

        }
    }

    private static void printUsage() {

        System.out.println();

        System.out.println("Usage:");

        System.out.println();

        System.out.println(
                "java -jar candidate-data-transformer.jar "
                        + "--csv <recruiter.csv> "
                        + "--resume <resume.pdf|resume.docx> "
                        + "--config <config.json>"
        );

        System.out.println();

        System.out.println("Example:");

        System.out.println(
                "java -jar candidate-data-transformer.jar "
                        + "--csv input/recruiter.csv "
                        + "--resume input/resume.pdf "
                        + "--config input/config.json"
        );

        System.out.println();
    }
}
