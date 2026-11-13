package org.project.tasks.contactapp.dao;


import org.project.tasks.contactapp.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    public List<Person> findAll() throws SQLException {
        List<Person> persons = new ArrayList<>();
        String query = "SELECT * FROM person";
        try (Connection cnx = DataSourceFactory.getConnection();
             Statement stmt = cnx.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                persons.add(new Person(
                        rs.getInt("idperson"),
                        rs.getString("lastname"),
                        rs.getString("firstname"),
                        rs.getString("nickname"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getString("email_address"),
                        rs.getDate("birth_date").toLocalDate()
                ));
            }
        }
        return persons;
    }

    public void addPerson(Person person) throws SQLException {
        String query = "INSERT INTO person (lastname, firstname, nickname, phone_number, address, email_address, birth_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection cnx = DataSourceFactory.getConnection();
             PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setString(1, person.getLastname());
            stmt.setString(2, person.getFirstname());
            stmt.setString(3, person.getNickname());
            stmt.setString(4, person.getPhoneNumber());
            stmt.setString(5, person.getAddress());
            stmt.setString(6, person.getEmailAddress());
            stmt.setDate(7, Date.valueOf(person.getBirthDate()));
            stmt.executeUpdate();
        }
    }

    public void updatePerson(Person person) throws SQLException {
        String query = "UPDATE person SET lastname=?, firstname=?, nickname=?, phone_number=?, address=?, email_address=?, birth_date=? WHERE idperson=?";
        try (Connection cnx = DataSourceFactory.getConnection();
             PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setString(1, person.getLastname());
            stmt.setString(2, person.getFirstname());
            stmt.setString(3, person.getNickname());
            stmt.setString(4, person.getPhoneNumber());
            stmt.setString(5, person.getAddress());
            stmt.setString(6, person.getEmailAddress());
            stmt.setDate(7, Date.valueOf(person.getBirthDate()));
            stmt.setInt(8, person.getId());
            stmt.executeUpdate();
        }
    }

    public void deletePerson(int id) throws SQLException {
        String query = "DELETE FROM person WHERE idperson=?";
        try (Connection cnx = DataSourceFactory.getConnection();
             PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}