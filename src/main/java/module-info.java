module com.sevenatseven {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.sevenatseven;
    opens com.sevenatseven.controllers to javafx.fxml;
}