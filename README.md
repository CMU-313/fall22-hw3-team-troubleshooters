![Open in Codespaces](https://classroom.github.com/assets/open-in-codespaces-abfff4d4e15f9e1bd8274d9a39a0befe03a0632bb0f153d0ec72ff541cedbe34.svg)
<h3 align="center">
  <img src="https://teedy.io/img/github-title.png" alt="Teedy" width=500 />
</h3>

[![License: GPL v2](https://img.shields.io/badge/License-GPL%20v2-blue.svg)](https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html)
![Coverage](https://raw.githubusercontent.com/CMU-313/Teedy/badges/badges/jacoco.svg?token=GHSAT0AAAAAABYAA6NCYJCAXNYCQUUBMWB6YYKLW2Q)

Teedy is an open source, lightweight document management system for individuals and businesses.

![New!](https://teedy.io/img/laptop-demo.png?20180301)

# New Feature: Adding Document Fields for Admissions Properties

The information below outlines the changes to several layers of Teedy to enable new fields in documents to record critical information about applicants. The information will be used to consider applicants. The added fields are:

- Applicant GPA
- Applicant Age
- Applicant Gender (female/male/other)
- Applicant Experience (Scored on a scale of 1-10)
- Applicant Skills (Scored on a scale of 1-10)
- Applied Program (MBA, MSCS, MSISM)

The following descriptions and changes will be labeled with the contributer, e.g. (Jasmine)


## Changes to Documentation Database (Jasmine)

**Documents changed:**
1. `Document.java`
2. `DocumentDao.java`
3. `DocumentDto.java`
4. `dbupdate-028-0.sql` (new added document)
5. `config-properties` (files in docs-web and docs-core)

**Test File:**
`TestJpa.java`

This is the first step to adding new fields to the document database. All work here is done in the backend. Through adding these new fields and building, new columns are added to the database and more information can be attached to a document. 

In `Document.java` and `DocumentDto.java`, the new fields are declared as String variables.Furthermore, `get` and `set` functions are added for each of the fields.

In `DocumentDao.java`, these new parameter fields are added to assist in search and update of document files. 

The file `dbupdate-028-0.sql` is added as a data migration to build the new database. This file uses sql files to alter the document table and add in the columns with data type `String` and `varchar(500)`. To make sure that this file runs in the build, I changed `config.properties` files in `docs-web` and `docs-core` to include `db.version=28`. Edit: after seeing how Hikma used numeric data types like doubles and ints in her front-end form, I decided to revisit this section and change my data types for Age, GPA, Skills, and Experience. Now, GPA is declared as a `double` and Age, Skills, Experience are declared as `int`.

All tests are run in `TestJpa.java`, where a new document was created with new fields. AssertEquals and AssertNotNull were used to make sure that the document was indeed created and fields were populated with the correct values. All the tests passed. The coverage for this test is 100% for the lines I have written. 

## Changes to Document Upload UI (Hikma)

**Documents changed:**
1. `document.edit.html`
2. `en.json`

**Testing Procedure:** 
Changes were deployed on a local server and manually examined to verify successful changes.

This is the frontend side to the work done to include the additional information for each applicant.

In `document.edit.html`, the new fields allow applicant to enter GPA and age (numerical values) as well as 
gender, and applied program (categorical values). 

In `en.json`, there are new entries for the labels that correspond to the additional fields added. 

## Changes to Document Upload REST both forward and backward (Charly and Rahul)

**Documents changed:**
1. `DocumentResouce.java`
2. `DocumentDao.java`

**Testing Procedure:** 
Changes were deployed on a local server and examination of json replies and requests manually to verify success.

This is the middleware side to the work, so it depends on an agreed frontend and backend interface. I handled some 
calculations and sent information between the two ends.

In `DocumentResouce.java`, the edits were to update and add functions, so that the front end form inputs can be sent
to the back end database, whether on creation or on update. We also changed some functions to calculate the average sum statistics for a few variables. We used an iterative loop and looped all documents.

In `DocumentDao.java`, the edits were to update and add functions, so that the front end form inputs can be sent
to the back end database, whether on creation or on update. We also changed some of the getDocument function so that 
there was no longer a null error when trying to examine the new document data from a document.


# Features

- Responsive user interface
- Optical character recognition
- LDAP authentication ![New!](https://www.sismics.com/public/img/new.png)
- Support image, PDF, ODT, DOCX, PPTX files
- Video file support
- Flexible search engine with suggestions and highlighting
- Full text search in all supported files
- All [Dublin Core](http://dublincore.org/) metadata
- Custom user-defined metadata ![New!](https://www.sismics.com/public/img/new.png)
- Workflow system ![New!](https://www.sismics.com/public/img/new.png)
- 256-bit AES encryption of stored files
- File versioning ![New!](https://www.sismics.com/public/img/new.png)
- Tag system with nesting
- Import document from email (EML format)
- Automatic inbox scanning and importing
- User/group permission system
- 2-factor authentication
- Hierarchical groups
- Audit log
- Comments
- Storage quota per user
- Document sharing by URL
- RESTful Web API
- Webhooks to trigger external service
- Fully featured Android client
- [Bulk files importer](https://github.com/sismics/docs/tree/master/docs-importer) (single or scan mode)
- Tested to one million documents


# Native Installation

## Requirements

Before building Teedy from source, you will need to install several prerequisites, including Java 11+, Maven 3+, NPM, Grunt, Tesseract 4, ffmpeg, and mediainfo.
We give instructions for installing these prerequisites on several platforms below.

### Linux (Ubuntu 22.04)

```console
sudo apt install \
  default-jdk \
  ffmpeg \
  grunt \
  maven \
  npm \
  tesseract-ocr \
  tesseract-ocr-ara \
  tesseract-ocr-ces \
  tesseract-ocr-chi-sim \
  tesseract-ocr-chi-tra \
  tesseract-ocr-dan \
  tesseract-ocr-deu \
  tesseract-ocr-fin \
  tesseract-ocr-fra \
  tesseract-ocr-heb \
  tesseract-ocr-hin \
  tesseract-ocr-hun \
  tesseract-ocr-ita \
  tesseract-ocr-jpn \
  tesseract-ocr-kor \
  tesseract-ocr-lav \
  tesseract-ocr-nld \
  tesseract-ocr-nor \
  tesseract-ocr-pol \
  tesseract-ocr-por \
  tesseract-ocr-rus \
  tesseract-ocr-spa \
  tesseract-ocr-swe \
  tesseract-ocr-tha \
  tesseract-ocr-tur \
  tesseract-ocr-ukr \
  tesseract-ocr-vie
```

### Mac

```console
brew install \
  ffmpeg \
  grunt-cli \
  maven \
  mediainfo \
  npm \
  openjdk \
  tesseract \
  tesseract-lang
```

### Windows

It is highly recommended that you proceed to install Windows Subsystem Linux (WSL), following the link: [Install Linux on Windows with WSL
](https://docs.microsoft.com/en-us/windows/wsl/install). This will allow you to run a Linux distro (Ubuntu's the default) within the Windows environment, and you can then proceed to follow the Linux (Ubuntu 22.04) instructions to install the dependencies.

**Note**: This would mean that you should proceed to execute the following instructions within the Linux environment as well.

## Installation Steps

Clone the repository to your local machine (`git clone https://github.com/CMU-313/Teedy`) and then use Maven to build Teedy from source by running the following command from the root directory:

```console
mvn clean -DskipTests install
```

After successfully building Teedy from source, you can launch a Teedy instance by running the following commands from the root directory:

```console
cd docs-web
mvn jetty:run
```

**The default admin password is "admin". Don't forget to change it before going to production.**

# License

Teedy is released under the terms of the GPL license. See `COPYING` for more
information or see <http://opensource.org/licenses/GPL-2.0>.
