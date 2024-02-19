module com.unam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.unam to javafx.fxml;
    opens com.unam.vistas to javafx.fxml;

    exports com.unam;
    exports com.unam.vistas;
}
