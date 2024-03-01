package edu.unam.vistas;

import java.io.IOException;
import java.util.ResourceBundle;

import edu.unam.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class viewEntrenamientosController {

    @FXML
    private Button btnGuardarNuevoEjercicio;

    @FXML
    private Button btnVolverHome;

    @FXML
    private ComboBox<?> comboBoxEjercicio;

    @FXML
    private ComboBox<Integer> comboBoxSeries;
    
        
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
    private TableView<?> tableviewEntrenamientos;

    @FXML
    private TextField txtDescanso;

    @FXML
    private TextField txtRepeticiones;

    @FXML
    void guardarNuevoEjercicio(ActionEvent event) {
        // Implementa la lógica para guardar un nuevo ejercicio
        String ejercicioSeleccionado = comboBoxEjercicio.getValue();
        String nombreSeries = comboBoxSeries.getValue();
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
