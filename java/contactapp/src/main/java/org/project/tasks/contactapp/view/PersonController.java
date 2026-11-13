package org.project.tasks.contactapp.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.project.tasks.contactapp.ContactApp;
import org.project.tasks.contactapp.dao.PersonDAO;
import org.project.tasks.contactapp.model.Person;

import java.io.IOException;
import java.sql.SQLException;

public class PersonController {

    @FXML
    private TableView<Person> personTable;

    private PersonDAO personDAO = new PersonDAO();

    @FXML
    public void initialize() throws SQLException {
        refreshTable();
    }

    @FXML
    public void handleAddPerson() throws IOException, SQLException {
        showPersonForm(null);
    }

    @FXML
    public void handleUpdatePerson() throws IOException, SQLException {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            showPersonForm(selectedPerson);
        }
    }

    @FXML
    public void handleDeletePerson() throws SQLException {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            personDAO.deletePerson(selectedPerson.getId());
            refreshTable();
        }
    }

    private void showPersonForm(Person person) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(ContactApp.class.getResource("PersonForm.fxml"));
        Parent root = fxmlLoader.load();
        PersonFormController controller = fxmlLoader.getController();
        controller.setPerson(person);
        Stage stage = new Stage();
        stage.setTitle(person == null ? "Add Person" : "Update Person");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        refreshTable();
    }

    private void refreshTable() throws SQLException {
        personTable.getItems().setAll(personDAO.findAll());
    }
}
