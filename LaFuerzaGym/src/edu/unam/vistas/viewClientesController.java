package edu.unam.vistas;

import java.io.IOException;


import edu.unam.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class viewClientesController {
    @FXML
    private TableColumn<?, ?> apellidoColumn;

    @FXML
    private Button btnGuardarNuevoCliente;

    @FXML
    private Button btnVolverHome;

    @FXML
    private TableView<?> clientesTable;

    @FXML
    private ComboBox<?> comboBoxSexo;

    @FXML
    private DatePicker dateFechaIngreso;

    @FXML
    private DatePicker dateFechaNacimiento;

    @FXML
    private TableColumn<?, ?> editarColumn;

    @FXML
    private TableColumn<?, ?> eliminarColumn;

    @FXML
    private TableColumn<?, ?> fechaIngresoColumn;

    @FXML
    private TableColumn<?, ?> fechaNacimientoColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

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
    private Label labelTítuloVista;

    @FXML
    private TableColumn<?, ?> nombreColumn;

    @FXML
    private TableColumn<?, ?> sexoColumn;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtNombre;


    
    @FXML
    void guardarNuevoCliente() throws IOException {
        // Implementa lógica para guardar un nuevo cliente
    }

    @FXML
    void volverHome() throws IOException {
        App.setRoot("homeView");
    }
}
