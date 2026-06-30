package com.eightfold.transformer.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class JsonSchemaValidator {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonSchemaValidator() {
    }

    /**
     * Validates JSON against a schema.
     *
     * @param jsonNode JSON output
     * @param schemaPath Path to schema file
     * @throws IOException if schema cannot be read
     * @throws IllegalArgumentException if validation fails
     */
    public static void validate(JsonNode jsonNode,
                                String schemaPath) throws IOException {

        JsonSchemaFactory factory =
                JsonSchemaFactory.getInstance(
                        SpecVersion.VersionFlag.V7
                );

        JsonSchema schema =
                factory.getSchema(new File(schemaPath).toURI());

        Set<ValidationMessage> errors =
                schema.validate(jsonNode);

        if (!errors.isEmpty()) {

            StringBuilder builder = new StringBuilder();

            builder.append("JSON Schema Validation Failed:\n");

            for (ValidationMessage error : errors) {
                builder.append("- ")
                       .append(error.getMessage())
                       .append("\n");
            }

            throw new IllegalArgumentException(builder.toString());
        }
    }
}