module com.project.paradoxplatformer {
    
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires commons.lang3;


    opens com.project.paradoxplatformer to javafx.fxml;
    exports com.project.paradoxplatformer;
}