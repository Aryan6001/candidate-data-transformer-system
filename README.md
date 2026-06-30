# Candidate Data Transformer

A Java-based application that transforms candidate information from multiple sources into a unified JSON profile. The application parses recruiter CSV data and resumes (PDF/DOCX), normalizes the extracted information, merges records, validates the output, and generates a configurable JSON profile.

---

## Features

- Parse recruiter CSV files
- Parse PDF and DOCX resumes
- Normalize phone numbers (E.164 format)
- Normalize skills using canonical mapping
- Merge candidate data from multiple sources
- Track data provenance
- Generate confidence scores
- Validate mandatory fields
- Produce configurable JSON output
- Comprehensive JUnit 5 test coverage

---

## Technology Stack

- Java 17
- Maven
- Apache Commons CSV
- Apache PDFBox
- Apache POI
- Jackson
- JUnit 5

---

## Project Structure

```
candidate-data-transformer
│
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   │
│   └── test
│       ├── java
│       └── resources
│
├── output
├── pom.xml
└── README.md
```

---

## Prerequisites

- Java 17 or later
- Apache Maven 3.8+
- Eclipse IDE (optional)

Verify installation:

```bash
java -version
mvn -version
```

---

## Build the Project

Clone the repository:

```bash
git clone https://github.com/Aryan6001/candidate-data-transformer.git
```

Navigate to the project:

```bash
cd candidate-data-transformer
```

Compile and package:

```bash
mvn clean package
```

Expected output:

```
BUILD SUCCESS
```

---

## Run Unit Tests

```bash
mvn test
```

Expected output:

```
Tests run: 42
Failures: 0
Errors: 0

BUILD SUCCESS
```

---

## Run the Application

The application accepts three command-line arguments:

- Recruiter CSV
- Resume (PDF or DOCX)
- Configuration JSON

Example:

```bash
java -jar target/candidate-data-transformer-1.0.0.jar \
--csv src/test/resources/recruiter.csv \
--resume src/test/resources/resume.pdf \
--config src/test/resources/config.json
```

For a DOCX resume:

```bash
java -jar target/candidate-data-transformer-1.0.0.jar \
--csv src/test/resources/recruiter.csv \
--resume src/test/resources/resume.docx \
--config src/test/resources/config.json
```

---

## Running from Eclipse

1. Import the project as an **Existing Maven Project**.
2. Right-click the project.
3. Select **Run As → Java Application**.
4. Open **Run Configurations**.
5. Under **Arguments**, enter:

```
--csv src/test/resources/recruiter.csv --resume src/test/resources/resume.pdf --config src/test/resources/config.json
```

6. Click **Run**.

---

## Sample Input Files

```
src/test/resources/

recruiter.csv
resume.pdf
resume.docx
config.json
```

---

## Output

The generated candidate profile is written as:

```
output/candidate.json
```

Example output:

```json
{
  "candidate_id": "CAND-1001",
  "full_name": "John Doe",
  "email": "john.doe@gmail.com",
  "phone": "+919876543210",
  "skills": [
    "Java",
    "Spring Boot",
    "Docker"
  ],
  "overall_confidence": 0.95
}
```

---

## Design Highlights

- Modular architecture
- Independent parser components
- Configurable output schema
- Data provenance tracking
- Confidence scoring
- Extensive unit testing

---

## Test Results

```
Tests run: 42
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

---

## Author

**Aryan Chavan**

GitHub: https://github.com/Aryan6001
