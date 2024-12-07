module com.sevenatseven {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sevenatseven to javafx.fxml;
    exports com.sevenatseven;
}