package edu.unam.vistas;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.unam.App;
import edu.unam.modelo.DetalleRutina;
import edu.unam.modelo.EntrenamientoCliente;
import edu.unam.modelo.Rutina;
import edu.unam.repositorio.Repositorio;
import edu.unam.servicios.ServicioDetalleRutina;
import edu.unam.servicios.ServicioEntrenamientoCliente;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class viewDetalleRutinaController {
    //Declaración del repositorio
    private EntityManagerFactory emf;
    private Repositorio repositorio;
    private ServicioDetalleRutina servicioDetalleRutina;
    private ServicioEntrenamientoCliente servicioEntrenamientoCliente;

    public viewDetalleRutinaController() {
        emf = Persistence.createEntityManagerFactory("LaFuerzaPU");
        repositorio = new Repositorio(emf);
        servicioDetalleRutina = new ServicioDetalleRutina(repositorio);
        servicioEntrenamientoCliente = new ServicioEntrenamientoCliente(repositorio);
    }

    @FXML
    private Button btnEditarDetalleRutina;

    @FXML
    private Button btnEliminarDetalleRutina;

    @FXML
    private Button btnGuardarNuevoDetalleRutina;

    @FXML
    private Button btnVolverHome;

    @FXML
    private ComboBox<String> comboBoxEjercicio;

    @FXML
    private TextField txtRepeticiones;
    
    @FXML
    private Label labelEjercicio;

    @FXML
    private Label labelPeso;

    @FXML
    private Label labelRepeticiones;

    @FXML
    private Label labelSeries;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private ComboBox<Integer> comboBoxSeries;

    @FXML
    private TableColumn<DetalleRutina, String> clienteColumn;

    @FXML
    private TableColumn<DetalleRutina, String> ejercicioColumn;

    @FXML
    private TableColumn<DetalleRutina, Integer> idColumn;

    @FXML
    private TableColumn<DetalleRutina, Double> pesoColumn;

    @FXML
    private TableColumn<DetalleRutina, Integer> repeticionesColumn;

    @FXML
    private TableColumn<DetalleRutina, Integer> seriesColumn;

    @FXML
    private TableColumn<DetalleRutina, Double> volumenRutinaColumn;

    @FXML
    private TableView<DetalleRutina> tableDetalleEntrenamiento;

    @FXML
    private TextField txtPeso;

    private EntrenamientoCliente entrenamientoCliente;

    public void initEntrenamientoCliente(EntrenamientoCliente entrenamientoCliente){
        this.entrenamientoCliente = entrenamientoCliente;

        //Obtenemos las rutinas del EntrenamientoCliente y los nombres de los ejercicios
        Set<Rutina> rutinas = entrenamientoCliente.getRutinas();
        //Creamos un HashSet para guardar los nombres y así evitar duplicados
        Set<String> nombresEjercicios = new HashSet<String>();
        //Obtenemos los ejercicios de la rutina
        for(Rutina rutina : rutinas){
            //Obtenemos el ejercicio de la rutina y lo guardamos en el hashset
            nombresEjercicios.add(rutina.getEjercicio().getNombre());
        }

        //Agregamos los nombres de los ejercicios al comboBox
        comboBoxEjercicio.getItems().addAll(nombresEjercicios);

    }

    @FXML 
    public void initialize(){
        //Deshabilitamos botones de edición y eliminación
        btnEditarDetalleRutina.setDisable(true);
        btnEliminarDetalleRutina.setDisable(true);

        //Agregamos los valores al combobox series
        comboBoxSeries.getItems().addAll(1,2,3,4,5,6,7,8,9,10);

        //Comenzamos a rellenar la tabla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idDetalleRutina"));
        seriesColumn.setCellValueFactory(new PropertyValueFactory<>("series"));
        repeticionesColumn.setCellValueFactory(new PropertyValueFactory<>("repeticiones"));
        pesoColumn.setCellValueFactory(new PropertyValueFactory<>("peso"));
        volumenRutinaColumn.setCellValueFactory(new PropertyValueFactory<>("volumenRutina"));
        ejercicioColumn.setCellValueFactory(new PropertyValueFactory<>("nombreEjercicio"));

        //Obtenemos el nombre y apellido del cliente de entrenamientoCliente
        clienteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEntrenamientoCliente().getCliente().getNombre() + " " + cellData.getValue().getEntrenamientoCliente().getCliente().getApellido())); 

        // Obtener los entrenamientos de los clientes del repositorio
        List<DetalleRutina> detalleRutinas = servicioDetalleRutina.obtenerTodos();

        //Si la lista está vacía, mostramos un mensaje en la tabla
        if (detalleRutinas.isEmpty()) {
            tableDetalleEntrenamiento.setPlaceholder(new Label("No hay detalle de rutinas para mostrar."));
        } else {
            //Si hay ejercicios, los mostramos en la tabla
            tableDetalleEntrenamiento.getItems().setAll(detalleRutinas);
        }

        //Ahora procedemos a realizar las operaciones de eliminación y edición
        //Primero habilitamos los botones de eliminación y edición al selecionar un elemento
        tableDetalleEntrenamiento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newSelection) -> {
            if (newSelection != null) {
                btnEditarDetalleRutina.setDisable(false);
                btnEliminarDetalleRutina.setDisable(false);
                btnGuardarNuevoDetalleRutina.setDisable(true);

                //Obtener los datos cargados anteriormente
                comboBoxEjercicio.setValue(newSelection.getNombreEjercicio());
                txtRepeticiones.setText(Integer.toString(newSelection.getRepeticiones()));
                comboBoxSeries.setValue(newSelection.getSeries());
                txtPeso.setText(Double.toString(newSelection.getPeso()));

            } else {
                btnEditarDetalleRutina.setDisable(true);
                btnEliminarDetalleRutina.setDisable(true);
                btnGuardarNuevoDetalleRutina.setDisable(false);
            }
        });

        //Eliminación de datos
        btnEliminarDetalleRutina.setOnAction(event -> {
            //Obtenemos el detalle de la rutina seleccionado
            DetalleRutina detalleRutina = tableDetalleEntrenamiento.getSelectionModel().getSelectedItem();
            //Mostramos un mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Estás seguro de eliminar el detalle de la rutina del cliente?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    //Si el usuario confirma, eliminamos el detalle de la rutina
                    servicioDetalleRutina.eliminarDetalleRutina(detalleRutina);
                    tableDetalleEntrenamiento.getItems().remove(detalleRutina);

                    //Mensaje de éxito
                    Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                    alertSuccess.setTitle("Éxito");
                    alertSuccess.setHeaderText(null);
                    alertSuccess.setContentText("El detalle de la rutina del cliente se eliminó correctamente.");
                    alertSuccess.showAndWait();
                }
            });
            tableDetalleEntrenamiento.getSelectionModel().clearSelection();
            tableDetalleEntrenamiento.refresh();
        });

        //Edición de datos
        btnEditarDetalleRutina.setOnAction(event -> {
            //Obtenemos el detalle seleccionado
            DetalleRutina data = tableDetalleEntrenamiento.getSelectionModel().getSelectedItem();

            //Mostramos un mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Estás seguro de editar el detalle de la rutina del cliente?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    //Si el usuario confirma, editamos el detalle de la rutina
                    data.setEntrenamientoCliente(entrenamientoCliente);
                    data.setNombreEjercicio(comboBoxEjercicio.getValue());
                    data.setSeries(comboBoxSeries.getValue());
                    data.setRepeticiones(Integer.parseInt(txtRepeticiones.getText()));
                    data.setPeso(Double.parseDouble(txtPeso.getText()));
                    data.setVolumenRutina(comboBoxSeries.getValue() * Integer.parseInt(txtRepeticiones.getText()) * Double.parseDouble(txtPeso.getText()));
                    servicioDetalleRutina.editarDetalleRutina(data);

                    tableDetalleEntrenamiento.refresh();

                    //Mensaje de éxito
                    Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                    alertSuccess.setTitle("Éxito");
                    alertSuccess.setHeaderText(null);
                    alertSuccess.setContentText("El detalle de la rutina del cliente se editó correctamente.");
                    alertSuccess.showAndWait();

                    //Reestablecemos sus promptText
                    comboBoxEjercicio.setPromptText("Ejercicio");
                    txtRepeticiones.setPromptText("Número de repeticiones");
                    comboBoxSeries.setPromptText("Series");
                    txtPeso.setPromptText("Peso");
                }
            });
            tableDetalleEntrenamiento.getSelectionModel().clearSelection();
            tableDetalleEntrenamiento.refresh();
        });


    }

    @FXML
    void guardarNuevoEntrenamiento(ActionEvent event) throws IOException {

        Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("Error");
        alertSuccess.setHeaderText(null);
        alertSuccess.setContentText("El detalle de la rutina del cliente se agregó correctamente.");


        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText("Error al guardar el detalle de la rutina del cliente.");

        //Validamos que se ingresó un número en el campo de repeticiones
        if(!txtRepeticiones.getText().matches("[0-9]+")){
            alertError.setContentText("El campo de repeticiones debe ser un número entero.");
            alertError.showAndWait();
            return;
        }

        //Validamos que se ingresó un número double en el campo de peso
        if(!txtPeso.getText().matches("[0-9]+([.][0-9]+)?")){
            alertError.setContentText("El campo de peso debe ser un número decimal.");
            alertError.showAndWait();
            return;
        }

        //Obtenemos los valores de los campos
        String nombreEjercicio = comboBoxEjercicio.getValue();
        Integer series = comboBoxSeries.getValue();
        Integer repeticiones = Integer.parseInt(txtRepeticiones.getText());
        Double peso = Double.parseDouble(txtPeso.getText());

        //Realizamos la validación de los campos
        if(nombreEjercicio == null || series == null || repeticiones == null || peso == null){
            alertError.setContentText("Todos los campos son obligatorios.");
            alertError.showAndWait();
            return;
        }
        
        try {
            DetalleRutina detalleRutina = new DetalleRutina();
            detalleRutina.setEntrenamientoCliente(entrenamientoCliente);
            detalleRutina.setNombreEjercicio(nombreEjercicio);
            detalleRutina.setSeries(series);
            detalleRutina.setRepeticiones(repeticiones);
            detalleRutina.setPeso(peso);
            detalleRutina.setVolumenRutina(series * repeticiones * peso);

            //Guardamos el detalle de la rutina
            servicioDetalleRutina.agregarDetalleRutina(detalleRutina);
            tableDetalleEntrenamiento.getItems().add(detalleRutina);

            //Actualizamos el volumen semanal del entrenamientoCliente
            entrenamientoCliente.setVolumenSemanal(entrenamientoCliente.getVolumenSemanal() + detalleRutina.getVolumenRutina());
            servicioEntrenamientoCliente.editarEntrenamientoCliente(entrenamientoCliente);

            //Mostramos mensaje de éxito
            alertSuccess.showAndWait();

            //Limpiamos los campos de entrada
            comboBoxEjercicio.getSelectionModel().clearSelection();
            txtRepeticiones.clear();
            txtRepeticiones.setText(null);
            comboBoxSeries.getSelectionModel().clearSelection();
            txtPeso.clear();
            txtPeso.setText(null);

            //Reestablecemos sus promptText
            comboBoxEjercicio.setPromptText("Ejercicio");
            txtRepeticiones.setPromptText("Número de repeticiones");
            comboBoxSeries.setPromptText("Series");
            txtPeso.setPromptText("Peso");

        } catch (Exception e) {
            alertError.showAndWait();
        }
        
    }

    @FXML
    void volverHome(ActionEvent event) throws IOException {
        App.setRoot("viewEntrenamientoCliente");
    }

}
