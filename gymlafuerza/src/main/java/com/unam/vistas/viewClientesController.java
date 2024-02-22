package com.unam.vistas;

import java.io.IOException;


import com.unam.App;
//import com.unam.modelo.Cliente;
//import com.unam.modelo.ClienteDAO;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;

public class viewClientesController {
     @FXML
    private Button btnGuardarNuevoCliente;

    @FXML
    private Button btnVolverHome;

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
    private TableView<?> clientesTable;

    
    @FXML
    void guardarNuevoCliente() throws IOException {
        // Implementa lógica para guardar un nuevo cliente
    }

    @FXML
    void volverHome() throws IOException {
        App.setRoot("homeView");
    }
}
