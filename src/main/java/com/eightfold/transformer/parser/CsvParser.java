package com.eightfold.transformer.parser;

import com.eightfold.transformer.model.Candidate;
import com.eightfold.transformer.model.Contact;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;

public class CsvParser {

    public Candidate parse(String csvPath) throws IOException {

        try (Reader reader = new FileReader(csvPath)) {

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(reader);

            for (CSVRecord record : records) {

                // Create Contact object
                Contact contact = new Contact();
                contact.setEmails(Collections.singletonList(record.get("email")));
                contact.setPhones(Collections.singletonList(record.get("phone")));

                // Create Candidate object
                Candidate candidate = new Candidate();
                candidate.setCandidateId(record.get("candidate_id"));
                candidate.setFullName(record.get("name"));
                candidate.setHeadline(record.get("title"));
                candidate.setContact(contact);

                return candidate;
            }
        }

        return null;
    }
}