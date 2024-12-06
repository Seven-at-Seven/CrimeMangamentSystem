module com.criminalsys.crimemanagementsys {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.criminalsys.crimemanagementsys to javafx.fxml;
    exports com.criminalsys.crimemanagementsys;
}