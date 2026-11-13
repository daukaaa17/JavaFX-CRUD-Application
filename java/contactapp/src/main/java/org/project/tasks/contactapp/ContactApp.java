package org.project.tasks.contactapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.project.tasks.contactapp.dao.DataSourceFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ContactApp.class.getResource("PersonView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("ContactApp");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        createPersonTable();
    }

    private void createPersonTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS person ("
                + "idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + "lastname VARCHAR(45) NOT NULL, "
                + "firstname VARCHAR(45) NOT NULL, "
                + "nickname VARCHAR(45) NOT NULL, "
                + "phone_number VARCHAR(15) NULL, "
                + "address VARCHAR(200) NULL, "
                + "email_address VARCHAR(150) NULL, "
                + "birth_date DATE NULL)";

        try (Connection connection = DataSourceFactory.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            System.err.println("Error creating person table: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}