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

public class viewEntrenamientosController {

    @FXML
    private Button btnEditarEntrenamiento;

    @FXML
    private Button btnEliminarEntrenamiento;

    @FXML
    private Button btnGuardarNuevoEntrenamiento;

    @FXML
    private Button btnVolverHome;

    @FXML
    private ComboBox<?> comboBoxEjercicio;

    @FXML
    private ComboBox<?> comboBoxSeries;

    @FXML
    private TableColumn<?, ?> descansoColumn;

    @FXML
    private TableColumn<?, ?> duracionColumn;

    @FXML
    private TableColumn<?, ?> ejercicioColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private Label labelDescanso;

    @FXML
    private Label labelEjercicio;

    @FXML
    private Label labelRepeticiones;

    @FXML
    private Label labelSeries;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private TableColumn<?, ?> repetecionesColumn;

    @FXML
    private TableColumn<?, ?> seriesColumn;

    @FXML
    private TableView<?> tableviewEntrenamientos;

    @FXML
    private TextField txtDescanso;

    @FXML
    private TextField txtRepeticiones;

    @FXML
    void guardarNuevoEntrenamiento(ActionEvent event) {
        // Implementa la lógica para guardar un nuevo ejercicio
        //String ejercicioSeleccionado = comboBoxEjercicio.getValue();
       // String nombreSeries = comboBoxSeries.getValue();
        String repeticiones = txtRepeticiones.getText();
        String descanso = txtDescanso.getText();
        
        //Conexion

        //Alerta
        //Limpiar campos
        comboBoxEjercicio.setValue(null);
        //txtNombreSeries.clear();
        txtDescanso.clear();
        txtRepeticiones.clear();
    }

    @FXML
    void volverHome()throws IOException {
        App.setRoot("homeView");
    }
}
