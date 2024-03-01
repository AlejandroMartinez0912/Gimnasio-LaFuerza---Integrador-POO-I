package edu.unam.vistas;

import java.io.IOException;
import java.time.LocalDate;

import edu.unam.App;
//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddClientesViewController {
    @FXML
    private Button btnGuardarNuevoCliente;

    @FXML
    private Button btnVolverHome;

    @FXML
    private ComboBox<String> comboBoxSexo;

    @FXML
    private DatePicker dateFechaIngreso;

    @FXML
    private DatePicker dateFechaNacimiento;

    @FXML
    private Label labelApellido;

    @FXML
    private Label labelFechaIngreso;

    @FXML
    private Label labelFechaNacimiento;

    @FXML
    private Label labelNombres;

    @FXML
    private Label labelSexo;

    @FXML
    private Label labelTituloApp;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private Label lblObligatorio;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtNombres;


    @FXML
    private void initialize() {
        comboBoxSexo.getItems().addAll("Masculino", "Femenino", "Otro");
    }

   @FXML
    void guardarNuevoCliente() throws IOException{
        //Variables
        String apellido = txtApellido.getText();
        String nombres = txtNombres.getText();
        LocalDate fechaNacimiento = dateFechaNacimiento.getValue();
        LocalDate fechaInicio = dateFechaIngreso.getValue();
        String sexoBiologico = comboBoxSexo.getValue();
        //Union 
        //Alerta
        //Limpiar campos
        txtApellido.clear();
        txtNombres.clear();
        dateFechaIngreso.setValue(null);
        dateFechaNacimiento.setValue(null);
        comboBoxSexo.setValue(null);

    }
    
    @FXML
    void volverHome() throws IOException{
        App.setRoot("homeView");
    }

}   
