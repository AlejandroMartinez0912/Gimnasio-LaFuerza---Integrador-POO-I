package edu.unam.vistas;

import java.io.IOException;

import edu.unam.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddEjerciciosViewController {

    public AddEjerciciosViewController() {
    }

    @FXML
    private Button btnGuardarNuevoEjercicio;

    @FXML
    private Button btnVolverHome;

    @FXML
    private ComboBox<?> comboBoxGrupoMuscular;

    @FXML
    private Label labelGrupoMuscular;

    @FXML
    private Label labelNombreEjercicio;

    @FXML
    private Label labelTituloApp;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private Label lblObligatorio;

    @FXML
    private Label lblObligatorio1;

    @FXML
    private TextField txtNombreEjercicio;

    @FXML
    void guardarNuevoEjercicio() throws IOException {

    }

    @FXML
    void volverHome() throws IOException {
        App.setRoot("homeView");
    }
}
