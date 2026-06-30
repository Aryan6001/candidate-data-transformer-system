package com.eightfold.transformer.parser;

import com.eightfold.transformer.extractor.EmailExtractor;
import com.eightfold.transformer.extractor.PhoneExtractor;
import com.eightfold.transformer.extractor.SkillExtractor;
import com.eightfold.transformer.model.Candidate;
import com.eightfold.transformer.model.Contact;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfResumeParser implements ResumeParser {
	@Override
	public Candidate parse(String filePath) throws IOException {

	    PDDocument document = Loader.loadPDF(new File(filePath));

	    PDFTextStripper stripper = new PDFTextStripper();

	    String text = stripper.getText(document);

	    document.close();

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