module fi.metropolia.assignment11 {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens fi.metropolia.assignment11 to javafx.fxml;
    exports fi.metropolia.assignment11;
}
