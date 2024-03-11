package edu.unam.vistas;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import edu.unam.App;
import edu.unam.modelo.Ejercicio;
import edu.unam.modelo.Rutina;
import edu.unam.repositorio.Repositorio;
import edu.unam.servicios.ServicioEjercicio;
import edu.unam.servicios.ServicioRutina;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class viewRutinasController {

    private EntityManagerFactory emf;
    private Repositorio repositorio;
    private ServicioEjercicio servicioEjercicio;
    private ServicioRutina servicioRutina;

    public viewRutinasController() {
        emf = Persistence.createEntityManagerFactory("LaFuerzaPU");
        repositorio = new Repositorio(emf);
        servicioEjercicio = new ServicioEjercicio(repositorio);
        servicioRutina = new ServicioRutina(repositorio);
    }

    @FXML
    private Button btnEditarRutina;

    @FXML
    private Button btnEliminarRutina;

    @FXML
    private Button btnGuardarNuevaRutina;

    @FXML
    private Button btnVolverHome;

    @FXML
    private ComboBox<String> comboBoxEjercicio;

    @FXML
    private ComboBox<Integer> comboBoxSeries;

    @FXML
    private TableColumn<Rutina, String> descansoColumn;

    @FXML
    private TableColumn<Rutina, String> ejercicioColumn;

    @FXML
    private TableColumn<Rutina, Integer> idColumn;

    @FXML
    private Label labelDescanso;

    @FXML
    private Label labelEjercicio;

    @FXML
    private Label labelRepeticiones;

    @FXML
    private Label labelSeries;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private TableColumn<Rutina, Integer> repeticionesColumn;

    @FXML
    private TableColumn<Rutina, Integer> seriesColumn;

    @FXML
    private TableView<Rutina> tableviewRutinas;

    @FXML
    private TextField txtDescanso;

    @FXML
    private TextField txtRepeticiones;

    @FXML
    void guardarNuevaRutina(ActionEvent event) {
        // Creamos la alerta con un mensaje de éxito
        Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("Éxito");
        alertSuccess.setHeaderText(null);
        alertSuccess.setContentText("La rutina se agregó correctamente.");
        
        //Creamos la alerta con un mensaje de error
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText("Hubo un error al agregar la rutina.");

        //Obtenemos los datos ingresados por el usuario
        int descansoIngresado = Integer.parseInt(txtDescanso.getText());
        int repeticionesIngresadas = Integer.parseInt(txtRepeticiones.getText());
        String ejercicioSeleccionado =  comboBoxEjercicio.getValue();
        int seriesSeleccionada = comboBoxSeries.getValue();

        //Obtenemos los ejercicios
        List<Ejercicio> ejercicios = servicioEjercicio.obtenerTodos();

        //Buscamos el ejercicio seleccionado
        Ejercicio ejercicioEncontrado = ejercicios
        .stream()
        .filter(grupo -> grupo.getNombre().equals(ejercicioSeleccionado))
        .findFirst()
        .orElse(null);

        if (descansoIngresado <= 0 || repeticionesIngresadas <= 0 || ejercicioSeleccionado == null || seriesSeleccionada <= 0) {
            alertError.setContentText("Todos los campos deben ser completados con valores válidos.");
            alertError.showAndWait();
            return;
        }

        //Creamos un nuevo entrenamiento
        Rutina nuevaRutina = new Rutina();
        nuevaRutina.setDescanso(descansoIngresado);
        nuevaRutina.setRepeticiones(repeticionesIngresadas);
        nuevaRutina.setSeries(seriesSeleccionada);
        nuevaRutina.setEjercicio(ejercicioEncontrado);

        //Guardamos el nuevo entrenamiento
        // Llamar al servicio para agregar el nuevo Entrenamiento
        servicioRutina.agregarRutina(nuevaRutina);
        tableviewRutinas.getItems().add(nuevaRutina);
        // Limpiar el campo de texto después de agregar el grupo muscular
        txtDescanso.clear();
        txtRepeticiones.clear();
        //Limpiamos los campos de selección
        comboBoxEjercicio.getSelectionModel().clearSelection();
        comboBoxSeries.getSelectionModel().clearSelection();
        alertSuccess.showAndWait();
    }

     @FXML
    public void initialize() {

        //Deshabilitamos los botones de eliminar y editar
        btnEditarRutina.setDisable(true);
        btnEliminarRutina.setDisable(true);

        comboBoxSeries.getItems().addAll(1,2,3,4,5,6,7,8,9,10);

        //Obtenemos los grupos musculares
        List<Ejercicio> ejercicios = servicioEjercicio.obtenerTodos();

        // Filtramos por nombres de los grupos musculares
        List<String> nombresEjercicios = ejercicios
        .stream()
        .map(Ejercicio::getNombre)
        .collect(Collectors.toList());

        //Guardamos los nombres en una lista observable
        ObservableList<String> items = FXCollections.observableArrayList(nombresEjercicios);

        //Mostramos los nombres en el combobox
        comboBoxEjercicio.setItems(items);

        //Comenzamos a rellenar la tabla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idRutina"));
        ejercicioColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEjercicio().getNombre()));
        seriesColumn.setCellValueFactory(new PropertyValueFactory<>("series"));
        repeticionesColumn.setCellValueFactory(new PropertyValueFactory<>("repeticiones"));
        descansoColumn.setCellValueFactory(new PropertyValueFactory<>("descanso"));

        //Obtenemos todos las rutinas de la base de datos
        List<Rutina> rutinas = servicioRutina.obtenerTodos();

        //Si la lista está vacía, mostramos un mensaje en la tabla
        if (rutinas.isEmpty()) {
            tableviewRutinas.setPlaceholder(new Label("No hay rutinas para mostrar."));
        } else {
            //Si hay ejercicios, los mostramos en la tabla
            tableviewRutinas.getItems().setAll(rutinas);
        }

        tableviewRutinas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnEditarRutina.setDisable(false);
                btnEliminarRutina.setDisable(false);

                //txtDescanso.setText(newSelection.getDescanso());
                //txtRepeticiones.setText(newSelection.getRepeticiones());
                comboBoxEjercicio.setValue(newSelection.getEjercicio().getNombre());

            }else{
                btnEditarRutina.setDisable(true);
                btnEliminarRutina.setDisable(true);
                txtDescanso.clear();
                txtRepeticiones.clear();
                comboBoxEjercicio.setValue(null);
                comboBoxSeries.setValue(null);
            }
        });

        btnEliminarRutina.setOnAction((ActionEvent event) -> {
            Rutina data = tableviewRutinas.getSelectionModel().getSelectedItem();
            
            try {
                // Llamar al servicio para eliminar el ejercicio
                servicioRutina.eliminarRutina(data);
                tableviewRutinas.getItems().remove(data);
                
                // Para mostrar un mensaje de éxito
                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                alertSuccess.setTitle("Éxito");
                alertSuccess.setHeaderText(null);
                alertSuccess.setContentText("El entrenamiento se eliminó correctamente.");
    
                alertSuccess.showAndWait();
            } catch (Exception e) {
                // En caso de error, mostrar mensaje de error
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText(null);
                alertError.setContentText("Hubo un error al eliminar el entrenamiento.");
                alertError.showAndWait();
            }
        });

        btnEditarRutina.setOnAction((ActionEvent event) -> {
            Rutina data = tableviewRutinas.getSelectionModel().getSelectedItem();

            try {
                // Llamar al servicio para editar el ejercicio
                data.setDescanso(Integer.parseInt(txtDescanso.getText()));
                data.setRepeticiones(Integer.parseInt(txtRepeticiones.getText()));
                data.setSeries(comboBoxSeries.getValue());
                String nombreEjercicioSeleccionado = comboBoxEjercicio.getValue();
                Ejercicio ejercicioSeleccionado = ejercicios
                .stream()
                .filter(grupo -> grupo.getNombre().equals(nombreEjercicioSeleccionado))
                .findFirst()
                .orElse(null);
                data.setEjercicio(ejercicioSeleccionado);                
                servicioRutina.editarRutina(data);
                tableviewRutinas.refresh();
                
                // Para mostrar un mensaje de éxito
                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                alertSuccess.setTitle("Éxito");
                alertSuccess.setHeaderText(null);
                alertSuccess.setContentText("El ejercicio se editó correctamente.");
    
                alertSuccess.showAndWait();
            } catch (Exception e) {
                // En caso de error, mostrar mensaje de error
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText(null);
                alertError.setContentText("Hubo un error al editar el ejercicio.");
                alertError.showAndWait();
            }
        });
       
    }

    @FXML
    void volverHome()throws IOException {
        App.setRoot("homeView");
    }
}
