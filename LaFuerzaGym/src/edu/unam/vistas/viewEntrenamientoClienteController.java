package edu.unam.vistas;

import java.io.IOException;

import edu.unam.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class viewEntrenamientoClienteController {

    @FXML
    private Button btnActualizarEntrenamientoCliente;

    @FXML
    private Button btnEliminarEntrenamientoCliente;

    @FXML
    private Button btnGuardarEntrenamientoCliente;

    @FXML
    private Button btnVolverHome;

    @FXML
    private TableColumn<?, ?> clienteColumn;

    @FXML
    private ComboBox<?> comboBoxCliente;

    @FXML
    private DatePicker dateFechaFin;

    @FXML
    private DatePicker dateFechaInicio;

    @FXML
    private TableColumn<?, ?> ejercicioColumn;

    @FXML
    private TableColumn<?, ?> entrenamientoColumn;

    @FXML
    private TableColumn<?, ?> fechaFinColumn;

    @FXML
    private TableColumn<?, ?> fechaInicioColumn;

    @FXML
    private TableColumn<?, ?> grupoMuscularEntrenamientoClienteColumn;

    @FXML
    private TableColumn<?, ?> grupoMuscularEntrenamientoColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private Label labelCliente;

    @FXML
    private Label labelEntrenamientos;

    @FXML
    private Label labelFechaFin;

    @FXML
    private Label labelFechaInicio;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private TableColumn<?, ?> repeticionesColumn;

    @FXML
    private TableColumn<?, ?> seriesColumn;

    @FXML
    private TableView<?> tableEntrenamientosClientes;

    @FXML
    private TableView<?> tableSeleccionEntrenamientos;


    @FXML
    void guardarEntrenamientoCliente(ActionEvent event) throws IOException {

    }

    @FXML
    void volverHome(ActionEvent event) throws IOException {
        App.setRoot("homeView");
    }

}
