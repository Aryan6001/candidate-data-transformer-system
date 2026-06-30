package com.eightfold.transformer.parser;

import com.eightfold.transformer.extractor.EmailExtractor;
import com.eightfold.transformer.extractor.PhoneExtractor;
import com.eightfold.transformer.extractor.SkillExtractor;
import com.eightfold.transformer.model.Candidate;
import com.eightfold.transformer.model.Contact;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.IOException;

public class DocxResumeParser implements ResumeParser {

    @Override
    public Candidate parse(String filePath) throws IOException {

        FileInputStream fis = new FileInputStream(filePath);

        XWPFDocument document = new XWPFDocument(fis);

        String text = document.getDocument().getBody().toString();

        document.close();
        fis.close();

        // Create Contact object
        Contact contact = new Contact();
        contact.setEmails(EmailExtractor.extract(text));
        contact.setPhones(PhoneExtractor.extract(text));

        // Create Candidate object
        Candidate candidate = new Candidate();
        candidate.setContact(contact);
        candidate.setSkills(SkillExtractor.extract(text));

        return candidate;
    }
}