package com.eightfold.transformer.merger;

import com.eightfold.transformer.model.Candidate;
import com.eightfold.transformer.model.Contact;

public class CandidateMerger {

    public Candidate merge(Candidate csv,
                           Candidate resume) {

        Candidate candidate = new Candidate();

        // Candidate Id
        candidate.setCandidateId(
                MergePolicy.mergeString(
                        csv.getCandidateId(),
                        resume.getCandidateId()
                )
        );

        // Full Name
        candidate.setFullName(
                MergePolicy.mergeString(
                        csv.getFullName(),
                        resume.getFullName()
                )
        );

     // Contact
        Contact contact = new Contact();

        contact.setEmails(
                MergePolicy.mergeList(
                        csv.getContact() != null ? csv.getContact().getEmails() : null,
                        resume.getContact() != null ? resume.getContact().getEmails() : null
                )
        );

        contact.setPhones(
                MergePolicy.mergeList(
                        csv.getContact() != null ? csv.getContact().getPhones() : null,
                        resume.getContact() != null ? resume.getContact().getPhones() : null
                )
        );

        candidate.setContact(contact);

        // Location
        candidate.setLocation(

                csv.getLocation() != null
                        ? csv.getLocation()
                        : resume.getLocation()

        );

        // Links
        candidate.setLinks(

                csv.getLinks() != null
                        ? csv.getLinks()
                        : resume.getLinks()

        );

        // Headline
        candidate.setHeadline(

                MergePolicy.mergeString(
                        csv.getHeadline(),
                        resume.getHeadline()
                )

        );

        // Experience
        candidate.setExperience(

                MergePolicy.mergeList(
                        csv.getExperience(),
                        resume.getExperience()
                )

        );

        // Education
        candidate.setEducation(

                MergePolicy.mergeList(
                        csv.getEducation(),
                        resume.getEducation()
                )

        );

        // Skills
        candidate.setSkills(

                MergePolicy.mergeList(
                        csv.getSkills(),
                        resume.getSkills()
                )

        );

        // Years of Experience
        candidate.setYearsExperience(

                csv.getYearsExperience() != null
                        ? csv.getYearsExperience()
                        : resume.getYearsExperience()

        );

        return candidate;
    }

}