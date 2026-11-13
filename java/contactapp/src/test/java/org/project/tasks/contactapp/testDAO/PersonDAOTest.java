package org.project.tasks.contactapp.testDAO;

import org.junit.jupiter.api.*;
import org.project.tasks.contactapp.dao.DataSourceFactory;
import org.project.tasks.contactapp.dao.PersonDAO;
import org.project.tasks.contactapp.model.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

    private static Connection connection;
    private static PersonDAO personDAO;

    @BeforeAll
    static void setUp() throws SQLException {
        // Create an in-memory SQLite database for testing
        connection = DataSourceFactory.getConnection();
        personDAO = new PersonDAO();

        // Create the person table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS person ("
                + "idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + "lastname VARCHAR(45) NOT NULL, "
                + "firstname VARCHAR(45) NOT NULL, "
                + "nickname VARCHAR(45) NOT NULL, "
                + "phone_number VARCHAR(15) NULL, "
                + "address VARCHAR(200) NULL, "
                + "email_address VARCHAR(150) NULL, "
                + "birth_date DATE NULL)";
        try (var statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        }
    }

    @AfterAll
    static void tearDown() throws SQLException {
        // Close the connection after all tests are done
        if (connection != null) {
            connection.close();
        }
    }

    @BeforeEach
    void clearTable() throws SQLException {
        // Clear the person table before each test
        try (var statement = connection.createStatement()) {
            statement.execute("DELETE FROM person");
        }
    }

    @Test
    void testAddPerson() throws SQLException {
        // Create a new person
        Person person = new Person(0, "Doe", "John", "JD", "1234567890", "123 Main St", "john@example.com", LocalDate.of(1990, 1, 1));

        // Add the person to the database
        personDAO.addPerson(person);

        // Retrieve all persons from the database
        List<Person> persons = personDAO.findAll();

        // Verify that the person was added
        assertEquals(1, persons.size());
        assertEquals("Doe", persons.get(0).getLastname());
        assertEquals("John", persons.get(0).getFirstname());
        assertEquals("JD", persons.get(0).getNickname());
        assertEquals("1234567890", persons.get(0).getPhoneNumber());
        assertEquals("123 Main St", persons.get(0).getAddress());
        assertEquals("john@example.com", persons.get(0).getEmailAddress());
        assertEquals(LocalDate.of(1990, 1, 1), persons.get(0).getBirthDate());
    }

    @Test
    void testFindAll() throws SQLException {
        // Add two persons to the database
        Person person1 = new Person(0, "Doe", "John", "JD", "1234567890", "123 Main St", "john@example.com", LocalDate.of(1990, 1, 1));
        Person person2 = new Person(0, "Smith", "Jane", "JS", "0987654321", "456 Elm St", "jane@example.com", LocalDate.of(1995, 5, 5));
        personDAO.addPerson(person1);
        personDAO.addPerson(person2);

        // Retrieve all persons from the database
        List<Person> persons = personDAO.findAll();

        // Verify that both persons were retrieved
        assertEquals(2, persons.size());
        assertEquals("Doe", persons.get(0).getLastname());
        assertEquals("Smith", persons.get(1).getLastname());
    }

    @Test
    void testUpdatePerson() throws SQLException {
        // Add a person to the database
        Person person = new Person(0, "Doe", "John", "JD", "1234567890", "123 Main St", "john@example.com", LocalDate.of(1990, 1, 1));
        personDAO.addPerson(person);

        // Retrieve the person from the database
        List<Person> persons = personDAO.findAll();
        Person retrievedPerson = persons.get(0);

        // Update the person's details
        retrievedPerson.setLastname("Smith");
        retrievedPerson.setFirstname("Jane");
        retrievedPerson.setNickname("JS");
        retrievedPerson.setPhoneNumber("0987654321");
        retrievedPerson.setAddress("456 Elm St");
        retrievedPerson.setEmailAddress("jane@example.com");
        retrievedPerson.setBirthDate(LocalDate.of(1995, 5, 5));
        personDAO.updatePerson(retrievedPerson);

        // Retrieve the updated person from the database
        List<Person> updatedPersons = personDAO.findAll();
        Person updatedPerson = updatedPersons.get(0);

        // Verify that the person was updated
        assertEquals("Smith", updatedPerson.getLastname());
        assertEquals("Jane", updatedPerson.getFirstname());
        assertEquals("JS", updatedPerson.getNickname());
        assertEquals("0987654321", updatedPerson.getPhoneNumber());
        assertEquals("456 Elm St", updatedPerson.getAddress());
        assertEquals("jane@example.com", updatedPerson.getEmailAddress());
        assertEquals(LocalDate.of(1995, 5, 5), updatedPerson.getBirthDate());
    }

    @Test
    void testDeletePerson() throws SQLException {
        // Add a person to the database
        Person person = new Person(0, "Doe", "John", "JD", "1234567890", "123 Main St", "john@example.com", LocalDate.of(1990, 1, 1));
        personDAO.addPerson(person);

        // Retrieve the person from the database
        List<Person> persons = personDAO.findAll();
        Person retrievedPerson = persons.get(0);

        // Delete the person from the database
        personDAO.deletePerson(retrievedPerson.getId());

        // Verify that the person was deleted
        List<Person> remainingPersons = personDAO.findAll();
        assertEquals(0, remainingPersons.size());
    }
}