/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.unam.lafuerzagymapp.vistas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author Mariano
 */
public class AddClientesViewController implements Initializable {


    @FXML
    private TextField txtApellido;
    @FXML
    private Label labelTítuloVista;
    @FXML
    private Label labelApellido;
    @FXML
    private TextField txtNombres;
    @FXML
    private Label labelNombres;
    @FXML
    private Label labelFechaNacimiento;
    @FXML
    private DatePicker dateFechaNacimiento;
    @FXML
    private Label labelFechaIngreso;
    @FXML
    private DatePicker dateFechaIngreso;
    @FXML
    private Label labelSexo;
    @FXML
    private ComboBox<?> comboBoxSexo;
    @FXML
    private Button btnGuardarNuevoCliente;
    @FXML
    private Button btnVolverHome;
    @FXML
    private Label lblObligatorio;
    @FXML
    private Label labelTituloApp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void guardarNuevoCliente(ActionEvent event) {
    }

    @FXML
    private void volverHome(ActionEvent event) {
    }

}
