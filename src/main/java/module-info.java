module com.project.paradoxplatformer {
    
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires java.desktop;
    requires commons.lang3;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    requires transitive javafx.base;
    requires javafx.swing;

    opens com.project.paradoxplatformer.controller.deserialization.dtos to com.fasterxml.jackson.databind;
    opens com.project.paradoxplatformer to javafx.fxml;
    
    exports com.project.paradoxplatformer.view.javafx;
    exports com.project.paradoxplatformer.view;
}