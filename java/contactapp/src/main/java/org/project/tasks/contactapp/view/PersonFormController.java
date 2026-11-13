package org.project.tasks.contactapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.project.tasks.contactapp.dao.PersonDAO;
import org.project.tasks.contactapp.model.Person;

import java.sql.SQLException;
import java.time.LocalDate;

public class PersonFormController {

    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField nicknameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailField;
    @FXML
    private DatePicker birthDatePicker;

    private Person person;
    private boolean isUpdate;

    public void setPerson(Person person) {
        this.person = person;
        if (person != null) {
            // Bind the form fields to the Person properties
            lastNameField.setText(person.getLastname());
            firstNameField.setText(person.getFirstname());
            nicknameField.setText(person.getNickname());
            phoneNumberField.setText(person.getPhoneNumber());
            addressField.setText(person.getAddress());
            emailField.setText(person.getEmailAddress());
            birthDatePicker.setValue(person.getBirthDate());
            isUpdate = true;
        } else {
            // Clear the form fields for adding a new person
            lastNameField.clear();
            firstNameField.clear();
            nicknameField.clear();
            phoneNumberField.clear();
            addressField.clear();
            emailField.clear();
            birthDatePicker.setValue(null);
            isUpdate = false;
        }
    }

    @FXML
    private void handleSave() throws SQLException {
        // Get the values from the form fields
        String lastname = lastNameField.getText();
        String firstname = firstNameField.getText();
        String nickname = nicknameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String address = addressField.getText();
        String email = emailField.getText();
        LocalDate birthDate = birthDatePicker.getValue();

        // Validate required fields
        if (lastname.isEmpty() || firstname.isEmpty() || nickname.isEmpty()) {
            System.out.println("Required fields (Last Name, First Name, Nickname) are missing.");
            return;
        }

        PersonDAO personDAO = new PersonDAO();
        if (isUpdate) {
            // Update the existing person
            person.setLastname(lastname);
            person.setFirstname(firstname);
            person.setNickname(nickname);
            person.setPhoneNumber(phoneNumber);
            person.setAddress(address);
            person.setEmailAddress(email);
            person.setBirthDate(birthDate);
            personDAO.updatePerson(person);
        } else {
            // Add a new person
            Person newPerson = new Person(0, lastname, firstname, nickname, phoneNumber, address, email, birthDate);
            personDAO.addPerson(newPerson);
        }

        // Close the form window
        Stage stage = (Stage) lastNameField.getScene().getWindow();
        stage.close();
    }
}