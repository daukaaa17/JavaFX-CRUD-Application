# JavaFX Contact Management Application

A desktop contact manager built with **JavaFX** and **SQLite**. It supports the four basic CRUD operations (create, read, update, delete) on a list of contacts, with a table view for browsing and a separate form window for adding and editing.

Developed as a learning project to practise object-oriented design, JDBC database access and unit testing in Java.

<!-- Add a screenshot: save it as docs/screenshot.png and uncomment the next line -->
<!-- ![ContactApp screenshot](docs/screenshot.png) -->

## Features

- View all contacts in a table (ID, last name, first name, nickname, phone, address, email, birth date)
- Add a new contact through a form window
- Update the selected contact
- Delete the selected contact
- Data is stored locally in an SQLite file; the `person` table is created automatically on first launch

## Tech stack

| Area | Technology |
|---|---|
| Language | Java 17 |
| UI | JavaFX 17.0.6 (FXML), ControlsFX 11.1.2 |
| Database | SQLite via `sqlite-jdbc` 3.49.0.0 (plain JDBC, `PreparedStatement`) |
| Testing | JUnit 5.10.0 |
| Build | Maven (wrapper included) |

## Project structure

```
src/
├── main/
│   ├── java/org/project/tasks/contactapp/
│   │   ├── ContactApp.java            # Application entry point, creates the table on start
│   │   ├── model/Person.java          # Contact entity using JavaFX properties
│   │   ├── dao/
│   │   │   ├── DataSourceFactory.java # Opens the SQLite connection
│   │   │   └── PersonDAO.java         # findAll / addPerson / updatePerson / deletePerson
│   │   └── view/
│   │       ├── PersonController.java      # Main table window and button handlers
│   │       └── PersonFormController.java  # Add / edit form
│   └── resources/
│       ├── db/ddl/person-ddl.sql      # Table definition
│       └── org/project/tasks/contactapp/
│           ├── PersonView.fxml        # Main window layout
│           └── PersonForm.fxml        # Form window layout
└── test/java/org/project/tasks/contactapp/testDAO/
    └── PersonDAOTest.java             # JUnit tests for the DAO
```

The code follows a simple layered design: **model** (data), **dao** (database access), **view** (controllers and FXML layouts).

## Database

Single table `person`:

| Column | Type | Notes |
|---|---|---|
| `idperson` | INTEGER | Primary key, auto-increment |
| `lastname`, `firstname`, `nickname` | VARCHAR(45) | Required |
| `phone_number` | VARCHAR(15) | Optional |
| `address` | VARCHAR(200) | Optional |
| `email_address` | VARCHAR(150) | Optional |
| `birth_date` | DATE | Optional in the schema |

The database file `identifier.sqlite` is created in the working directory when the app first starts.

## Getting started

**Requirements:** JDK 17 or newer. Maven is not required, because the project includes the Maven wrapper.

## Run the application
./mvnw clean javafx:run          # Linux / macOS

mvnw.cmd clean javafx:run        # Windows

## Running the tests
./mvnw test                      # Linux / macOS

mvnw.cmd test                    # Windows

`PersonDAOTest` covers the four DAO operations: adding a person, listing all persons, updating a person and deleting a person.

Note: the tests use the same `identifier.sqlite` file as the application and clear the `person` table before each test, so run them against a copy of the data if you have contacts you want to keep.

## Known limitations

- Only the three required fields (last name, first name, nickname) are validated, and a validation failure is printed to the console rather than shown in the UI.
- Birth date is optional in the database schema, but the DAO and the form currently expect it to be set.
- The tests share the application's database file instead of using a separate in-memory database.

## Possible improvements

- Show validation errors in the interface
- Handle empty birth dates
- Use a separate database for tests
- Search and filter contacts
