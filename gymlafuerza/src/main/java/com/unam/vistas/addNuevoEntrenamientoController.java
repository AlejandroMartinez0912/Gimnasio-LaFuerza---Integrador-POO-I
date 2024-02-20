package com.unam.vistas;

import java.io.IOException;

import com.unam.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class addNuevoEntrenamientoController {
    @FXML
    private Button btnGuardarNuevoEjercicio;

    @FXML
    private Button btnVolverHome;

    @FXML
    private ComboBox<?> comboBoxGrupoMuscular;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private Label labelTituloApp;

    @FXML
    private Label labelNombreEjercicio;

    @FXML
    private Label lblObligatorio;

    @FXML
    private Label labelGrupoMuscular;

    @FXML
    private Label lblObligatorio1;

    @FXML
    private TextField txtNombreEjercicio;

    @FXML
    private TextField txtNombreEjercicio1;

    @FXML
    private Label labelGrupoMuscular1;

    @FXML
    private Label lblObligatorio11;

    @FXML
    private TextField txtNombreEjercicio2;

    @FXML
    private Label labelGrupoMuscular2;

    @FXML
    private Label lblObligatorio12;

    @FXML
    void guardarNuevoEjercicio() throws IOException {
        // Implementa la lógica para guardar un nuevo ejercicio
    }

    @FXML
    void volverHome() throws IOException {
        App.setRoot("homeView");
    }
}
