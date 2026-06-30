package com.eightfold.transformer.validation;

import com.eightfold.transformer.model.Candidate;
import com.eightfold.transformer.model.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldValidateCandidateSuccessfully() {

        Contact contact = new Contact();
        contact.setEmails(List.of("john@gmail.com"));
        contact.setPhones(List.of("+919876543210"));

        Candidate candidate = new Candidate();
        candidate.setCandidateId("1001");
        candidate.setFullName("John Smith");
        candidate.setContact(contact);

        assertDoesNotThrow(() ->
                CandidateValidator.validate(candidate));
    }

    @Test
    void shouldFailWhenCandidateIsNull() {

        assertThrows(
                IllegalArgumentException.class,
                () -> CandidateValidator.validate(null)
        );
    }

    @Test
    void shouldFailWhenNameIsMissing() {

        Contact contact = new Contact();
        contact.setEmails(List.of("john@gmail.com"));

        Candidate candidate = new Candidate();
        candidate.setContact(contact);

        assertThrows(
                IllegalArgumentException.class,
                () -> CandidateValidator.validate(candidate)
        );
    }

    @Test
    void shouldFailWhenContactIsMissing() {

        Candidate candidate = new Candidate();
        candidate.setCandidateId("1001");
        candidate.setFullName("John Smith");

        assertThrows(
                IllegalArgumentException.class,
                () -> CandidateValidator.validate(candidate)
        );
    }

    @Test
    void shouldFailWhenEmailAndPhoneAreMissing() {

        Contact contact = new Contact();

        Candidate candidate = new Candidate();
        candidate.setCandidateId("1001");
        candidate.setFullName("John Smith");
        candidate.setContact(contact);

        assertThrows(
                IllegalArgumentException.class,
                () -> CandidateValidator.validate(candidate)
        );
    }

    @Test
    void shouldValidateJsonSchemaSuccessfully() throws Exception {

        ObjectNode json = mapper.createObjectNode();

        json.put("candidateId", "1001");
        json.put("fullName", "John Smith");

        json.set("contact", mapper.createObjectNode());

        assertDoesNotThrow(() ->
                JsonSchemaValidator.validate(
                        json,
                        "src/main/resources/candidate-schema.json"
                ));
    }

    @Test
    void shouldFailInvalidJsonSchema() {

        ObjectNode json = mapper.createObjectNode();

        json.put("candidateId", "1001");

        assertThrows(
                Exception.class,
                () -> JsonSchemaValidator.validate(
                        json,
                        "src/main/resources/candidate-schema.json"
                )
        );
    }

}