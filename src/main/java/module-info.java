module com.sevenatseven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.sevenatseven to javafx.fxml;
    exports com.sevenatseven;
}