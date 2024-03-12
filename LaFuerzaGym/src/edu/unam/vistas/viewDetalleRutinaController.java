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

public class viewDetalleRutinaController {

    @FXML
    private Button btnEditarDetalleEntrenamiento;

    @FXML
    private Button btnEliminarDetalleEntrenamiento;

    @FXML
    private Button btnGuardarNuevoDetalleEntrenamiento;

    @FXML
    private Button btnVolverHome;

    @FXML
    private TableColumn<?, ?> clienteColumn;

    @FXML
    private ComboBox<?> comboBoxEjercicio;

    @FXML
    private ComboBox<?> comboBoxRepeticiones;

    @FXML
    private ComboBox<?> comboBoxSeries;

    @FXML
    private TableColumn<?, ?> ejercicioColumn;

    @FXML
    private TableColumn<?, ?> grupoMuscularColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private Label labelEjercicio;

    @FXML
    private Label labelEjercicio1;

    @FXML
    private Label labelRepeticiones;

    @FXML
    private Label labelSeries;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private TableColumn<?, ?> repeticionesColumn;

    @FXML
    private TableColumn<?, ?> seriesColumn;

    @FXML
    private TableView<?> tableDetalleEntrenamiento;

    @FXML
    void guardarNuevoEntrenamiento(ActionEvent event) {

    }

    @FXML
    void volverHome(ActionEvent event) throws IOException {
        App.setRoot("homeView");
    }

}
