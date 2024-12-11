module com.sevenatseven {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    exports com.sevenatseven.controllers.offiecer.util.display;
    exports com.sevenatseven;
    exports com.sevenatseven.controllers.auth;
    opens com.sevenatseven.controllers to javafx.fxml;
    opens com.sevenatseven to javafx.fxml;
}