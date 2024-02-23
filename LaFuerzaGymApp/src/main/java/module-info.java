module edu.unam.lafuerzagymapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens edu.unam.lafuerzagymapp to javafx.fxml;
    opens edu.unam.lafuerzagymapp.vistas to javafx.fxml;
    
    exports edu.unam.lafuerzagymapp;
    exports edu.unam.lafuerzagymapp.vistas;
    requires java.persistence;
}
