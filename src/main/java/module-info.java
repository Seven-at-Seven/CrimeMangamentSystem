module com.sevenatseven {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    exports com.sevenatseven;
    exports com.sevenatseven.controllers.auth;
    exports com.sevenatseven.controllers.admin;
    exports com.sevenatseven.controllers.admin.util;
    
    opens com.sevenatseven.controllers.admin to javafx.fxml;
    opens com.sevenatseven.controllers.admin.util to javafx.fxml;
    opens com.sevenatseven.controllers to javafx.fxml;
    opens com.sevenatseven to javafx.fxml;
    opens com.sevenatseven.controllers.auth to javafx.fxml;
}
