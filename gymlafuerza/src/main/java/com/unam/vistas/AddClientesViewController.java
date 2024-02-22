package com.unam.vistas;

import java.io.IOException;
import java.sql.Date;

import com.unam.App;
import com.unam.modelo.Cliente;
import com.unam.modelo.ClienteDAO;
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

    private final ClienteDAO clienteDAO = new ClienteDAO();

    @FXML
    private void initialize() {
        comboBoxSexo.getItems().addAll("Masculino", "Femenino", "Otro");
    }

    @FXML
    private void guardarNuevoCliente() {
        if (camposObligatoriosVacios()) {
            lblObligatorio.setVisible(true);
            return;
        }

        String nombres = txtNombres.getText();
        String apellido = txtApellido.getText();
        Date fechaNacimiento = Date.valueOf(dateFechaNacimiento.getValue());
        Date fechaIngreso = Date.valueOf(dateFechaIngreso.getValue());
        String sexo = comboBoxSexo.getValue();

        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre(nombres);
        nuevoCliente.setApellido(apellido);
        nuevoCliente.setFechaNacimiento(fechaNacimiento);
        nuevoCliente.setFechaIngreso(fechaIngreso);
        nuevoCliente.setSexo(sexo);

        clienteDAO.crearCliente(nuevoCliente);

        limpiarCampos();
    }

    @FXML
    private void volverHome() throws IOException {
        App.setRoot("homeView");
    }

    private boolean camposObligatoriosVacios() {
        return txtNombres.getText().isEmpty() || txtApellido.getText().isEmpty() ||
                dateFechaNacimiento.getValue() == null || dateFechaIngreso.getValue() == null ||
                comboBoxSexo.getValue() == null;
    }

    private void limpiarCampos() {
        txtNombres.clear();
        txtApellido.clear();
        dateFechaNacimiento.getEditor().clear();
        dateFechaIngreso.getEditor().clear();
        comboBoxSexo.setValue(null);
        lblObligatorio.setVisible(false);
    }
   /* @FXML
    void guardarNuevoCliente(ActionEvent event) {

    }
    
    @FXML
    void volverHome() throws IOException{
        App.setRoot("homeView");
    }*/

}   
