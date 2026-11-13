package org.project.tasks.contactapp.model;

import java.time.LocalDate;
import javafx.beans.property.*;

public class Person {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty lastname = new SimpleStringProperty();
    private final StringProperty firstname = new SimpleStringProperty();
    private final StringProperty nickname = new SimpleStringProperty();
    private final StringProperty phoneNumber = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty emailAddress = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>();

    // Constructor
    public Person(int id, String lastname, String firstname, String nickname, String phoneNumber, String address, String emailAddress, LocalDate birthDate) {
        this.id.set(id);
        this.lastname.set(lastname);
        this.firstname.set(firstname);
        this.nickname.set(nickname);
        this.phoneNumber.set(phoneNumber);
        this.address.set(address);
        this.emailAddress.set(emailAddress);
        this.birthDate.set(birthDate);
    }

    // Getters for properties
    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public StringProperty nicknameProperty() {
        return nickname;
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty emailAddressProperty() {
        return emailAddress;
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    // Regular getters and setters
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getLastname() {
        return lastname.get();
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getNickname() {
        return nickname.get();
    }

    public void setNickname(String nickname) {
        this.nickname.set(nickname);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getEmailAddress() {
        return emailAddress.get();
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }
}
