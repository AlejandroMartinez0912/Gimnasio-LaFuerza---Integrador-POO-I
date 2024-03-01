package edu.unam.vistas;

import java.io.IOException;

import edu.unam.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class viewEjerciciosController {

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
    private Label labelTítuloVista;

    @FXML
    private TableColumn<?, ?> tablaGrupoMuscular;

    @FXML
    private TableColumn<?, ?> tablaeditarEjercicio;

    @FXML
    private TableColumn<?, ?> tablaeliminarEjercicio;

    @FXML
    private TableColumn<?, ?> tableEjercicios;

    @FXML
    private TableView<?> tableViewEjercicios;

    @FXML
    private TextField txtNombreEjercicio;

    @FXML
    void guardarNuevoEjercicio(ActionEvent event) {

    }

    @FXML
    void volverHome()throws IOException {
        App.setRoot("homeView");
    }

}

