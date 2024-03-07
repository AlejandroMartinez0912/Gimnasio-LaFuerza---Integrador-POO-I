package edu.unam.vistas;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import edu.unam.App;
import edu.unam.modelo.Cliente;
import edu.unam.modelo.EntrenamientoCliente;
import edu.unam.modelo.Tutor;
import edu.unam.repositorio.Repositorio;
import edu.unam.servicios.ServicioEntrenamientoCliente;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class viewEntrenamientoClienteController {
    private final Repositorio repositorio;
    private final ServicioEntrenamientoCliente servicioEntrenamientoCliente;
    private EntityManagerFactory emf;

    public viewEntrenamientoClienteController() {
        emf = Persistence.createEntityManagerFactory("LaFuerzaPU");
        this.repositorio = new  Repositorio(emf);
        this.servicioEntrenamientoCliente = new ServicioEntrenamientoCliente(repositorio);
    }
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
    private ComboBox<Cliente> comboBoxCliente;

    @FXML
    private ComboBox<Tutor> comboBoxTutor;

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
    private Label labelTutor;

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
        Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("Error");
        alertSuccess.setHeaderText(null);

        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);

        Cliente cliente = comboBoxCliente.getValue();
        Date fechaInicio = java.sql.Date.valueOf(dateFechaInicio.getValue());
        Date fechaFin = java.sql.Date.valueOf(dateFechaFin.getValue());
        Tutor tutor = comboBoxTutor.getValue();
        Boolean activo = true;

        if (cliente == null || fechaInicio == null || fechaFin == null|| tutor == null|| activo == null){ 
            alertError.setContentText("Todos los campos son obligatorios");
            alertError.showAndWait();
            return;
        }
        else{
            try {
                
                EntrenamientoCliente entrenamientoCliente = new EntrenamientoCliente();
                entrenamientoCliente.setCliente(cliente);
                entrenamientoCliente.setFechaInicio(fechaInicio);
                entrenamientoCliente.setFechaFin(fechaFin);
                entrenamientoCliente.setTutor(tutor);
                entrenamientoCliente.setActivo(activo);

                servicioEntrenamientoCliente.agregarEntrenamientoCliente(entrenamientoCliente);
                tableEntrenamientosClientes.getItems().add(entrenamientoCliente);
                


                comboBoxCliente.setValue(null);
                comboBoxCliente.setValue(null);
                comboBoxTutor.setValue(null);
                dateFechaInicio.setValue(null);
                dateFechaFin.setValue(null);
                
                alertSuccess.setContentText("Entrenamiento del cliente guardado correctamente");
                alertSuccess.showAndWait();
                
            }
            catch(Exception e){
                alertError.setContentText("Error al guardar el entrenamiento del cliente");
                alertError.showAndWait();
                return;
            }
        }
    }

    public void initialize() {
        // Deshabilitar los botones eliminar y editar
        btnEliminarEntrenamientoCliente.setDisable(true);
        btnActualizarEntrenamientoCliente.setDisable(true);

        // Obtener los tutores y clientes del repositorio
        List<Tutor> tutores = repositorio.buscarTodos(Tutor.class);
        List<Cliente> clientes = repositorio.buscarTodos(Cliente.class);

        // Llenar los ComboBox con los datos obtenidos
        comboBoxTutor.setItems(FXCollections.observableArrayList(tutores));
        comboBoxCliente.setItems(FXCollections.observableArrayList(clientes));
        

        // Obtener los entrenamientos de los clientes
        

        
    }

    @FXML
    void volverHome(ActionEvent event) throws IOException {
        App.setRoot("homeView");
    }
}
