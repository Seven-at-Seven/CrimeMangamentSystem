module com.sevenatseven {
    requires javafx.controls;
    requires javafx.fxml;
    requires rt;
    requires jfxrt;

    exports com.sevenatseven;
    exports com.sevenatseven.controllers.auth;
    opens com.sevenatseven.controllers to javafx.fxml;
    opens com.sevenatseven to javafx.fxml;
}