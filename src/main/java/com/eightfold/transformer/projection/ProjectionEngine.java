package com.eightfold.transformer.projection;

import com.eightfold.transformer.config.Config;
import com.eightfold.transformer.config.FieldMapping;
import com.eightfold.transformer.model.Candidate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ProjectionEngine {

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Applies runtime projection to Candidate.
     */
    public ObjectNode project(Candidate candidate, Config config) {

        JsonNode candidateJson = mapper.valueToTree(candidate);

        ObjectNode output = mapper.createObjectNode();

        for (FieldMapping field : config.getFields()) {

            String outputField = field.getPath();
            String sourceField = field.getFrom();

            if (sourceField == null || sourceField.isBlank()) {
                sourceField = outputField;
            }

            JsonNode value = findNode(candidateJson, sourceField);

            if (value != null && !value.isMissingNode()) {

                output.set(outputField, value);

            } else {

                switch (config.getOn_missing()) {

                    case "null":
                        output.putNull(outputField);
                        break;

                    case "empty":
                        output.put(outputField, "");
                        break;

                    default:
                        break;
                }

            }
        }

        if (config.isInclude_confidence()) {

            output.set(
                    "overall_confidence",
                    candidateJson.get("overallConfidence")
            );

        }

        if (config.isInclude_provenance()) {

            output.set(
                    "provenance",
                    candidateJson.get("provenance")
            );

        }

        return output;
    }

    /**
     * Finds nested property.
     *
     * Supports:
     * fullName
     * contact.emails
     * location.city
     */
    private JsonNode findNode(JsonNode root,
                              String path) {

        String[] parts = path.split("\\.");

        JsonNode current = root;

        for (String part : parts) {

            if (part.contains("[")) {

                String field =
                        part.substring(0, part.indexOf("["));

                int index =
                        Integer.parseInt(
                                part.substring(
                                        part.indexOf("[") + 1,
                                        part.indexOf("]")
                                )
                        );

                current = current.path(field);

                if (current.isArray() && current.size() > index) {

                    current = current.get(index);

                } else {

                    return null;

                }

            } else {

                current = current.path(part);

            }

        }

        return current;
    }

}