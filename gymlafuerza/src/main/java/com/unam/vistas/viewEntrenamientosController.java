package com.unam.vistas;

import java.io.IOException;

import com.unam.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class viewEntrenamientosController {
    @FXML
    private Button btnGuardarNuevoCliente;

    @FXML
    private Button btnVolverHome;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private Label labelTituloApp;

    @FXML
    private TableView<?> rutinasTable;

    @FXML
    void guardarNuevoCliente() throws IOException {
        // Implementa lógica para guardar un nuevo entrenamiento
    }

    @FXML
    void volverHome() throws IOException {
        App.setRoot("homeView");
    }
}
