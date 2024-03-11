package edu.unam.vistas;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.unam.App;
import edu.unam.modelo.Cliente;
import edu.unam.modelo.EntrenamientoCliente;
import edu.unam.modelo.GrupoMuscular;
import edu.unam.modelo.Rutina;
import edu.unam.modelo.Tutor;
import edu.unam.repositorio.Repositorio;
import edu.unam.servicios.ServicioCliente;
import edu.unam.servicios.ServicioEntrenamientoCliente;
import edu.unam.servicios.ServicioRutina;
import edu.unam.servicios.ServicioTutor;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

public class viewEntrenamientoClienteController {
    private EntityManagerFactory emf;
    private Repositorio repositorio;
    private ServicioEntrenamientoCliente servicioEntrenamientoCliente;
    private ServicioRutina servicioRutina;
    private ServicioCliente servicioCliente;
    private ServicioTutor servicioTutor;

    public viewEntrenamientoClienteController() {
        emf = Persistence.createEntityManagerFactory("LaFuerzaPU");
        repositorio = new Repositorio(emf);
        servicioEntrenamientoCliente = new ServicioEntrenamientoCliente(repositorio);
        servicioRutina = new ServicioRutina(repositorio);
        servicioCliente = new ServicioCliente(repositorio);
        servicioTutor = new ServicioTutor(repositorio);
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
    private TableColumn<Rutina, String> rutinaColumn;

    @FXML
    private TableColumn<?, ?> fechaFinColumn;

    @FXML
    private TableColumn<?, ?> evaluacionColumn;

    @FXML
    private TableColumn<?, ?> grupoMuscularEntrenamientoClienteColumn;

    @FXML
    private TableColumn<Rutina, String> grupoMuscularRutinaColumn;

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
    private TableColumn<Rutina, Integer> repeticionesColumn;

    @FXML
    private TableColumn<Rutina, Integer> seriesColumn;

    @FXML
    private TableView<EntrenamientoCliente> tableEntrenamientosClientes;

    @FXML
    private TableView<Rutina> tableSeleccionEntrenamientos;

    @FXML
    void guardarEntrenamientoCliente(ActionEvent event) throws IOException {
        Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("Error");
        alertSuccess.setHeaderText(null);
        alertSuccess.setContentText("El entrenamiento del cliente se agregó correctamente.");


        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertSuccess.setContentText("Error al guardar el entrenamiento del cliente.");

        //Obtenemos los valors ingresados en los inputs
        Cliente cliente = comboBoxCliente.getValue();
        //Date fechaInicio = java.sql.Date.valueOf(dateFechaInicio.getValue());
        LocalDate fechaInicio = dateFechaInicio.getValue();
        LocalDate fechaFin = dateFechaFin.getValue();
        Tutor tutor = comboBoxTutor.getValue();
        ObservableList<Rutina> rutinasSeleccionadas = tableSeleccionEntrenamientos.getSelectionModel().getSelectedItems();
        Set<Rutina> rutinasSet = new HashSet<>(rutinasSeleccionadas);

        if (cliente == null || fechaInicio == null || fechaFin == null|| tutor == null){ 
            alertError.setContentText("Todos los campos son obligatorios");
            alertError.showAndWait();
            return;
        }
        
        try {
                
            EntrenamientoCliente entrenamientoCliente = new EntrenamientoCliente();
            entrenamientoCliente.setCliente(cliente);
            entrenamientoCliente.setFechaInicio(fechaInicio);
            entrenamientoCliente.setFechaFin(fechaFin);
            entrenamientoCliente.setTutor(tutor);
            entrenamientoCliente.setRutinas(rutinasSet);
            
            //Guardamos el entrenamiento del cliente
            servicioEntrenamientoCliente.agregarEntrenamientoCliente(entrenamientoCliente);
            
            //Mostramos un mensaje de éxito
            alertSuccess.setContentText("Entrenamiento del cliente guardado correctamente");
            alertSuccess.showAndWait();

            //Limpiamos los campos de selección
            comboBoxCliente.getSelectionModel().clearSelection();
            comboBoxTutor.getSelectionModel().clearSelection();

            //Limpiamos la tabla de rutinas seleccionadas
            tableSeleccionEntrenamientos.getSelectionModel().clearSelection();

            //Limpiamos los input date
            dateFechaInicio.setValue(null);
            dateFechaFin.setValue(null);
            
        }
        catch(Exception e){
            alertError.setContentText("Error al guardar el entrenamiento del cliente");
            alertError.showAndWait();
            return;
        }
    }

    public void initialize() {
        System.out.println("Método initialize() llamado");
        // Deshabilitar los botones eliminar y editar
        btnEliminarEntrenamientoCliente.setDisable(true);
        btnActualizarEntrenamientoCliente.setDisable(true);

        // Obtener los tutores y clientes del repositorio
        List<Tutor> tutores = servicioTutor.obtenerTodos();
        List<Cliente> clientes = servicioCliente.obtenerTodos();

        //Llenamos el comboBox de clientes
        comboBoxCliente.setItems(FXCollections.observableArrayList(clientes));

        //Llenamos el comboBox de tutores
        comboBoxTutor.setItems(FXCollections.observableArrayList(tutores));

        //Convertimos los datos de los combobox para que se muestren solo los nombre y apellidos
        comboBoxCliente.setConverter(new StringConverter<Cliente>() {
            @Override
            public String toString(Cliente cliente) {
                if(cliente != null){
                    return cliente.getNombre() + " " + cliente.getApellido();
                }else{
                    return "";
                }
            }

            @Override
            public Cliente fromString(String string) {
                return null;
            }
        });

        comboBoxTutor.setConverter(new StringConverter<Tutor>() {
            @Override
            public String toString(Tutor tutor) {
                if(tutor != null){
                    return tutor.getNombre() + " " + tutor.getApellido();
                }else{
                    return "";
                }
            }

            @Override
            public Tutor fromString(String string) {
                return null;
            }
        });

        //Comenzamos a rellenar la tabla de rutinas
        rutinaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEjercicio().getNombre()));
        seriesColumn.setCellValueFactory(new PropertyValueFactory<>("series"));
        repeticionesColumn.setCellValueFactory(new PropertyValueFactory<>("repeticiones"));
        grupoMuscularRutinaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEjercicio().getGrupoMuscular().getNombre()));

        List<Rutina> rutinas = servicioRutina.obtenerTodos();

        //Si la lista está vacía, mostramos un mensaje en la tabla
        if (rutinas.isEmpty()) {
            tableSeleccionEntrenamientos.setPlaceholder(new Label("No hay rutinas para mostrar."));
        } else {
            //Si hay ejercicios, los mostramos en la tabla
            tableSeleccionEntrenamientos.getItems().setAll(rutinas);
        }

        //Hacemos que la tabla de rutinas sea de selección múltiple
        tableSeleccionEntrenamientos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);



        /* 
        // Obtener los entrenamientos de los clientes
        LocalDate fechaInicio = dateFechaInicio.getValue();
        LocalDate fechaFin = dateFechaFin.getValue();

        EntrenamientoCliente entrenamientoCliente = new EntrenamientoCliente();

        if (entrenamientoCliente.getFechaInicio() != null) {
            String fechaInicioFormateada = entrenamientoCliente.getFechaInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            // Lógica para usar la fecha formateada...
        } else {
            // Manejar el caso cuando fechaInicio es nula...
        }

        if (entrenamientoCliente.getFechaFin() != null) {
            String fechaFinFormateada = entrenamientoCliente.getFechaFin().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            // Lógica para usar la fecha formateada...
        } else {
            // Manejar el caso cuando fechaFin es nula...
        }
        */
    }

    @FXML
    void volverHome(ActionEvent event) throws IOException {
        App.setRoot("homeView");
    }
}
