package edu.unam.vistas;

import java.io.IOException;

import edu.unam.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
public class viewEjerciciosController {
     @FXML
    private Button btnGuardarNuevoCliente;

    @FXML
    private Button btnVolverHome;

    @FXML
    private Label labelTituloApp;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private TableColumn<?, ?> tableviewEjercicios;

    @FXML
    void guardarNuevoCliente(ActionEvent event) {

    }
    @FXML
    void volverHome() throws IOException{
        App.setRoot("homeView");
    }

}

