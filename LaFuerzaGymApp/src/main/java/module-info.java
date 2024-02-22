module edu.unam.lafuerzagymapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.unam.lafuerzagymapp to javafx.fxml;
    exports edu.unam.lafuerzagymapp;
}
