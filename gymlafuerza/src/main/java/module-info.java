module com.unam {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.unam to javafx.fxml;
    exports com.unam;
}
