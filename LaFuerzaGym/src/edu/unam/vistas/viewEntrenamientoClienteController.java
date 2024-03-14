package edu.unam.vistas;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.unam.App;
import edu.unam.modelo.Cliente;
import edu.unam.modelo.EntrenamientoCliente;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private Button btnEvaluarTutor;

    @FXML
    private Button btnDetalleEntrenamientoCliente;

    @FXML
    private Button btnVolverHome;

    @FXML
    private TableColumn<EntrenamientoCliente, String> clienteColumn;

    @FXML
    private ComboBox<Cliente> comboBoxCliente;

    @FXML
    private ComboBox<Tutor> comboBoxTutor;

    @FXML
    private ComboBox<String> comboBoxEvaluacion;

    @FXML
    private DatePicker dateFechaFin;

    @FXML
    private DatePicker dateFechaInicio;

    @FXML
    private TableColumn<EntrenamientoCliente, String> ejercicioColumn;

    @FXML
    private TableColumn<Rutina, String> rutinaColumn;

    @FXML
    private TableColumn<EntrenamientoCliente, LocalDate> fechaFinColumn;

    @FXML
    private TableColumn<EntrenamientoCliente, String> evaluacionColumn;

    @FXML
    private TableColumn<EntrenamientoCliente, String> grupoMuscularEntrenamientoClienteColumn;

    @FXML
    private TableColumn<Rutina, String> grupoMuscularRutinaColumn;

    @FXML
    private TableColumn<EntrenamientoCliente, Integer> idColumn;

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

        //Obtenemos los valores ingresados en los inputs
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
            tableEntrenamientosClientes.getItems().add(entrenamientoCliente);
            
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
        btnEvaluarTutor.setDisable(true);

        // Obtener los tutores y clientes del repositorio
        List<Tutor> tutores = servicioTutor.obtenerTodos();
        List<Cliente> clientes = servicioCliente.obtenerTodos();

        //Llenamos el comboBox de clientes
        comboBoxCliente.setItems(FXCollections.observableArrayList(clientes));

        //Llenamos el comboBox de tutores
        comboBoxTutor.setItems(FXCollections.observableArrayList(tutores));

        // Llenar el comboBox de evaluación
        comboBoxEvaluacion.setItems(FXCollections.observableArrayList("Excelente", "Bueno", "Regular", "Malo"));
               

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

        //Comenzamos a rellenar la tabla de entrenamientos de clientes
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idEntrenamientoCliente"));
        clienteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getNombre() + " " + cellData.getValue().getCliente().getApellido()));
        fechaFinColumn.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        evaluacionColumn.setCellValueFactory(new PropertyValueFactory<>("evaluacionTutor"));
        grupoMuscularEntrenamientoClienteColumn.setCellValueFactory(cellData -> {
            Set<Rutina> misRutinas = cellData.getValue().getRutinas();
            StringBuilder nombresGruposMusculares = new StringBuilder();
            
            for (Rutina rutina : misRutinas) {
                if (nombresGruposMusculares.length() > 0) {
                    nombresGruposMusculares.append(", ");
                }
                nombresGruposMusculares.append(rutina.getEjercicio().getGrupoMuscular().getNombre());
            }
            
            return new SimpleStringProperty(nombresGruposMusculares.toString());
        });
        ejercicioColumn.setCellValueFactory(cellData -> {
            Set<Rutina> misRutinas = cellData.getValue().getRutinas();
            StringBuilder nombresEjercicios = new StringBuilder();
            for (Rutina rutina : misRutinas) {
                if (nombresEjercicios.length() > 0) {
                    nombresEjercicios.append(", ");
                }
                nombresEjercicios.append(rutina.getEjercicio().getNombre());
            }
            
            return new SimpleStringProperty(nombresEjercicios.toString());
        });        

        // Obtener los entrenamientos de los clientes del repositorio
        List<EntrenamientoCliente> entrenamientosClientes = servicioEntrenamientoCliente.obtenerTodos();

         //Si la lista está vacía, mostramos un mensaje en la tabla
        if (entrenamientosClientes.isEmpty()) {
            tableEntrenamientosClientes.setPlaceholder(new Label("No hay rutinas para mostrar."));
        } else {
            //Si hay ejercicios, los mostramos en la tabla
            tableEntrenamientosClientes.getItems().setAll(entrenamientosClientes);
        }

        tableEntrenamientosClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnActualizarEntrenamientoCliente.setDisable(false);
                btnEliminarEntrenamientoCliente.setDisable(false);
                btnDetalleEntrenamientoCliente.setDisable(false);
                btnGuardarEntrenamientoCliente.setDisable(true);
                btnEvaluarTutor.setDisable(false);
                comboBoxEvaluacion.setDisable(false);
                
                comboBoxCliente.setValue(newSelection.getCliente());
                comboBoxTutor.setValue(newSelection.getTutor());
                dateFechaInicio.setValue(newSelection.getFechaInicio());
                dateFechaFin.setValue(newSelection.getFechaFin());

                List<Rutina> rutinasSeleccionadas = new ArrayList<>(newSelection.getRutinas());
                
                // Limpiar la selección previa
                tableSeleccionEntrenamientos.getSelectionModel().clearSelection();

                // Obtener el índice de cada rutina seleccionada y seleccionar esos índices
                for (Rutina rutina : rutinasSeleccionadas) {
                    int index = tableSeleccionEntrenamientos.getItems().indexOf(rutina);
                    if (index >= 0) {
                        tableSeleccionEntrenamientos.getSelectionModel().select(index);
                    }
                }
            }else{
                btnActualizarEntrenamientoCliente.setDisable(true);
                btnEliminarEntrenamientoCliente.setDisable(true);
                btnGuardarEntrenamientoCliente.setDisable(false);
                comboBoxCliente.setValue(null);
                comboBoxTutor.setValue(null);
                dateFechaInicio.setValue(null);
                dateFechaFin.setValue(null);
            }
        });

        btnEliminarEntrenamientoCliente.setOnAction((ActionEvent event) -> {
            EntrenamientoCliente data = tableEntrenamientosClientes.getSelectionModel().getSelectedItem();
            
            try {
                // Llamar al servicio para eliminar el ejercicio
                servicioEntrenamientoCliente.eliminarEntrenamientoCliente(data);
                tableEntrenamientosClientes.getItems().remove(data);
                
                // Para mostrar un mensaje de éxito
                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                alertSuccess.setTitle("Éxito");
                alertSuccess.setHeaderText(null);
                alertSuccess.setContentText("El entrenamiento del cliente se eliminó correctamente.");
    
                alertSuccess.showAndWait();
            } catch (Exception e) {
                // En caso de error, mostrar mensaje de error
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText(null);
                alertError.setContentText("Hubo un error al eliminar el entrenamiento del cliente.");
                alertError.showAndWait();
            }
            btnEliminarEntrenamientoCliente.setDisable(true);
        });


        btnActualizarEntrenamientoCliente.setOnAction((ActionEvent event) -> {
            EntrenamientoCliente data = tableEntrenamientosClientes.getSelectionModel().getSelectedItem();

            try {
                // Llamar al servicio para editar el ejercicio
                
                data.setCliente(comboBoxCliente.getValue());
                data.setTutor(comboBoxTutor.getValue());
                data.setFechaInicio(dateFechaInicio.getValue());
                data.setFechaFin(dateFechaFin.getValue());           

                ObservableList<Rutina> rutinasSeleccionadas = tableSeleccionEntrenamientos.getSelectionModel().getSelectedItems();
                Set<Rutina> rutinasSet = new HashSet<>(rutinasSeleccionadas);
                data.setRutinas(rutinasSet);

                servicioEntrenamientoCliente.editarEntrenamientoCliente(data);
                tableEntrenamientosClientes.refresh();
                
                // Para mostrar un mensaje de éxito
                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                alertSuccess.setTitle("Éxito");
                alertSuccess.setHeaderText(null);
                alertSuccess.setContentText("El entrenamiento del cliente se editó correctamente.");
    
                btnGuardarEntrenamientoCliente.setDisable(false);
                btnActualizarEntrenamientoCliente.setDisable(true);
                btnEliminarEntrenamientoCliente.setDisable(true);

                alertSuccess.showAndWait();
            } catch (Exception e) {
                // En caso de error, mostrar mensaje de error
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText(null);
                alertError.setContentText("Hubo un error al editar el entrenamiento del cliente.");
                alertError.showAndWait();
            }
            btnActualizarEntrenamientoCliente.setDisable(true);
        });

        //Ahora creamos el evento para habilitar el botón para ir a la vista EvaluarTutor
        btnEvaluarTutor.setOnAction((ActionEvent event) ->{
            EntrenamientoCliente entrenamientoCliente = tableEntrenamientosClientes.getSelectionModel().getSelectedItem();
            String evaluacion = comboBoxEvaluacion.getValue();
            if (evaluacion == null) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText(null);
                alertError.setContentText("Debes seleccionar una evaluación para el tutor.");
                alertError.showAndWait();
                return;
            }
            try {
                // Llamar al servicio para editar el ejercicio
                entrenamientoCliente.setEvaluacionTutor(evaluacion); // Aquí se guarda la evaluación del tutor
                servicioEntrenamientoCliente.editarEntrenamientoCliente(entrenamientoCliente); // Aquí se guarda la evaluación del tutor
                tableEntrenamientosClientes.refresh(); // Aquí se guarda la evaluación del tutor
                
                // Para mostrar un mensaje de éxito
                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                alertSuccess.setTitle("Éxito");
                alertSuccess.setHeaderText(null);
                alertSuccess.setContentText("El entrenamiento del cliente se editó correctamente.");
    
                alertSuccess.showAndWait();
            } catch (Exception e) {
                // En caso de error, mostrar mensaje de error
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText(null);
                alertError.setContentText("Hubo un error al editar el entrenamiento del cliente.");
                alertError.showAndWait();
            }
            btnEvaluarTutor.setDisable(true);
            comboBoxEvaluacion.setDisable(true);
        } );

        //Ahora creamos el evento para habilitar el botón para ir a la vista DetalleRutina
        btnDetalleEntrenamientoCliente.setOnAction((ActionEvent event) -> {
            EntrenamientoCliente entrenamientoCliente = tableEntrenamientosClientes.getSelectionModel().getSelectedItem();
            
            try {
                 // Cargamos el FXMLLoader para la nueva vista
                FXMLLoader loader = new FXMLLoader(getClass().getResource("viewDetalleRutina.fxml"));
                Parent root = loader.load();

                //Obtenemos el controlador de la vista DetalleRutina y pasamos el objeto
                viewDetalleRutinaController detalleRutinaController = loader.getController();
                detalleRutinaController.initEntrenamientoCliente(entrenamientoCliente);

                App.setRoot(root);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        });
    }

    @FXML
    void volverHome(ActionEvent event) throws IOException {
        App.setRoot("homeView");
    }
}
