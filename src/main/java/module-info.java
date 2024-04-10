module com.project.paradoxplatformer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project.paradoxplatformer to javafx.fxml;
    exports com.project.paradoxplatformer;
}