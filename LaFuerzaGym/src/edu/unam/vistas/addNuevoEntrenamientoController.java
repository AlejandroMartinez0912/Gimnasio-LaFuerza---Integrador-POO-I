package edu.unam.vistas;

import java.io.IOException;

import edu.unam.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class addNuevoEntrenamientoController {
    @FXML
    private Label labelTituloApp;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private ComboBox<String> comboBoxEjercicio;

    @FXML
    private Label labelEjercicio;

    @FXML
    private TextField txtNombreSeries;

    @FXML
    private Label labelSeries;

    @FXML
    private TextField txtRepeticiones;

    @FXML
    private Label labelRepeticiones;

    @FXML
    private TextField txtDescanso;

    @FXML
    private Label labelDescanso;

    @FXML
    private Button btnGuardarNuevoEjercicio;

    @FXML
    private Button btnVolverHome;

    @FXML
    void guardarNuevoEjercicio() throws IOException {
        // Implementa la lógica para guardar un nuevo ejercicio
        String ejercicioSeleccionado = comboBoxEjercicio.getValue();
        String nombreSeries = txtNombreSeries.getText();
        String repeticiones = txtRepeticiones.getText();
        String descanso = txtDescanso.getText();

        //Conexion

        //Alerta
        //Limpiar campos
        comboBoxEjercicio.setValue(null);
        txtNombreSeries.clear();
        txtDescanso.clear();
        txtRepeticiones.clear();
    }

    @FXML
    void volverHome() throws IOException {
        
    }
}
