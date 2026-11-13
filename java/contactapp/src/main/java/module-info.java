module org.project.tasks.contactapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.graphics;

    requires org.controlsfx.controls;

    opens org.project.tasks.contactapp to javafx.fxml;
    exports org.project.tasks.contactapp;
    opens org.project.tasks.contactapp.view to javafx.fxml;
    exports org.project.tasks.contactapp.view to javafx.fxml;

    opens org.project.tasks.contactapp.model to javafx.base;
    exports org.project.tasks.contactapp.model;
    exports org.project.tasks.contactapp.dao;
}