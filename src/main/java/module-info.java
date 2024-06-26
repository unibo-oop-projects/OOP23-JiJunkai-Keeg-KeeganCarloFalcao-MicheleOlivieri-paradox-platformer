module com.project.paradoxplatformer {
    
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires commons.lang3;
    requires java.sql;
    requires com.fasterxml.jackson.databind;



    opens com.project.paradoxplatformer to javafx.fxml;
    exports com.project.paradoxplatformer;
}