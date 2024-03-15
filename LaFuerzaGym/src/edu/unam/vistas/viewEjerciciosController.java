package edu.unam.vistas;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import edu.unam.App;
import edu.unam.modelo.Ejercicio;
import edu.unam.modelo.GrupoMuscular;
import edu.unam.repositorio.Repositorio;
import edu.unam.servicios.ServicioEjercicio;
import edu.unam.servicios.ServicioGrupoMuscular;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class viewEjerciciosController {
    private EntityManagerFactory emf;
    private Repositorio repositorio;
    private ServicioEjercicio servicioEjercicio;
    private ServicioGrupoMuscular servicioGrupoMuscular;

    public viewEjerciciosController() {
        emf = Persistence.createEntityManagerFactory("LaFuerzaPU");
        repositorio = new Repositorio(emf);
        servicioEjercicio = new ServicioEjercicio(repositorio);
        servicioGrupoMuscular = new ServicioGrupoMuscular(repositorio);
    }

    @FXML
    private TableColumn<Ejercicio, String> ejercicioColumn;

    @FXML
    private Button btnGuardarNuevoEjercicio;

    @FXML
    private Button btnEditarEjercicio;

    @FXML
    private Button btnEliminarEjercicio;

    @FXML
    private Button btnVolverHome;

    @FXML
    private ComboBox<String> comboBoxGrupoMuscular;

    @FXML
    private TableColumn<GrupoMuscular, String> grupoMuscularColumn;

    @FXML
    private TableColumn<Ejercicio, Integer> idColumn;

    @FXML
    private Label labelGrupoMuscular;

    @FXML
    private Label labelNombreEjercicio;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private TableView<Ejercicio> tableViewEjercicios;

    @FXML
    private TextField txtNombreEjercicio;

    @FXML
    void guardarNuevoEjercicio(ActionEvent event) {
        // Creamos la alerta con un mensaje de éxito
        Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("Éxito");
        alertSuccess.setHeaderText(null);
        alertSuccess.setContentText("El ejercicio se agregó correctamente.");
        
        //Creamos la alerta con un mensaje de error
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText("Hubo un error al agregar el ejercicio.");

        // Obtenemos el texto del campo y se eliminan espacios en blanco al inicio y al final para mejor validación
        String nombreEjercicio = txtNombreEjercicio.getText().trim();
        String nombreGrupoMuscularSeleccionado = comboBoxGrupoMuscular.getValue();

        //Obtenemos los grupos musculares
        List<GrupoMuscular> gruposMusculares = servicioGrupoMuscular.obtenerTodos();

        //Buscamos el grupo muscular seleccionado
        GrupoMuscular grupoMuscularSeleccionado = gruposMusculares
        .stream()
        .filter(grupo -> grupo.getNombre().equals(nombreGrupoMuscularSeleccionado))
        .findFirst()
        .orElse(null);

        if(grupoMuscularSeleccionado == null){
            alertError.setContentText("Debe seleccionar un grupo muscular de la lista desplegable.");
            alertError.showAndWait();
            return;
        }

        // Verificamos si el campo no está vacío y contiene solo letras        
        if (!nombreEjercicio.isEmpty() && nombreEjercicio.matches("[a-zA-Z]+")) {

            // Creamos una nueva instancia de Ejercicio
            Ejercicio nuevoEjercicio = new Ejercicio();
            nuevoEjercicio.setNombre(nombreEjercicio);
            nuevoEjercicio.setGrupoMuscular(grupoMuscularSeleccionado);
    
            // Llamar al servicio para agregar el nuevo GrupoMuscular
            servicioEjercicio.agregarEjercicio(nuevoEjercicio);
            tableViewEjercicios.getItems().add(nuevoEjercicio);
            // Limpiar el campo de texto después de agregar el grupo muscular
            txtNombreEjercicio.clear();
            alertSuccess.showAndWait();
        } else {
            // Mostrar un mensaje de error al usuario indicando que el campo es obligatorio
            alertError.showAndWait();
        }
    }

    @FXML
    public void initialize() {

        //Deshabilitamos los botones de eliminar y editar
        btnEditarEjercicio.setDisable(true);
        btnEliminarEjercicio.setDisable(true);

        //Obtenemos los grupos musculares
        List<GrupoMuscular> gruposMusculares = servicioGrupoMuscular.obtenerTodos();

        // Filtramos por nombres de los grupos musculares
        List<String> nombresGruposMusculares = gruposMusculares
        .stream()
        .map(GrupoMuscular::getNombre)
        .collect(Collectors.toList());

        //Guardamos los nombres en una lista observable
        ObservableList<String> items = FXCollections.observableArrayList(nombresGruposMusculares);

        //Mostramos los nombres en el combobox
        comboBoxGrupoMuscular.setItems(items);

        //Comenzamos a rellenar la tabla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idEjercicio"));
        ejercicioColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        grupoMuscularColumn.setCellValueFactory(new PropertyValueFactory<>("grupoMuscular"));

        //Obtenemos todos los ejercicios de la base de datos
        List<Ejercicio> ejercicios = servicioEjercicio.obtenerTodos();

        //Si la lista está vacía, mostramos un mensaje en la tabla
        if (ejercicios.isEmpty()) {
            tableViewEjercicios.setPlaceholder(new Label("No hay ejercicios para mostrar."));
        } else {
            //Si hay ejercicios, los mostramos en la tabla
            tableViewEjercicios.getItems().setAll(ejercicios);
        }

        tableViewEjercicios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnEditarEjercicio.setDisable(false);
                btnEliminarEjercicio.setDisable(false);

                txtNombreEjercicio.setText(newSelection.getNombre());
                comboBoxGrupoMuscular.setValue(newSelection.getGrupoMuscular().getNombre());

            }else{
                btnEditarEjercicio.setDisable(true);
                btnEliminarEjercicio.setDisable(true);
                txtNombreEjercicio.clear();
                comboBoxGrupoMuscular.setValue(null);
            }
        });

        //Eliminación de datos
        btnEliminarEjercicio.setOnAction((ActionEvent event) -> {
            //Obtenemos los detalles del ejercicio seleccionado
            Ejercicio data = tableViewEjercicios.getSelectionModel().getSelectedItem();
            //Mostramos un Mensaje de Confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Esta seguro que desea eliminar este ejercicio?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    //Si el usuario confirma, eliminamos el ejercicio
                    servicioEjercicio.eliminarEjercicio(data);
                    tableViewEjercicios.getItems().remove(data);
                    //Mostramos un mensaje de éxito
                    Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                    alertSuccess.setTitle("Éxito");
                    alertSuccess.setHeaderText(null);
                    alertSuccess.setContentText("El ejercicio se eliminó correctamente.");
                    alertSuccess.showAndWait();
                }
            });
            //Deshabilitamos los botones de eliminar
            btnEliminarEjercicio.setDisable(true);
        });

        btnEditarEjercicio.setOnAction((ActionEvent event) -> {
            //Obtenemos los detalles del ejercicio seleccionado
            Ejercicio data = tableViewEjercicios.getSelectionModel().getSelectedItem();

            //Mostramos un mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Esta seguro que desea editar este ejercicio?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    //Si el usuario confirma, habilitamos el botón de guardar
                    btnGuardarNuevoEjercicio.setDisable(false);

                    data.setNombre(txtNombreEjercicio.getText());
                String nombreGrupoMuscularSeleccionado = comboBoxGrupoMuscular.getValue();
                GrupoMuscular grupoMuscularSeleccionado = gruposMusculares
                .stream()
                .filter(grupo -> grupo.getNombre().equals(nombreGrupoMuscularSeleccionado))
                .findFirst()
                .orElse(null);
                data.setGrupoMuscular(grupoMuscularSeleccionado);                
                servicioEjercicio.editarEjercicio(data);
                tableViewEjercicios.refresh();
                
                // Para mostrar un mensaje de éxito
                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                alertSuccess.setTitle("Éxito");
                alertSuccess.setHeaderText(null);
                alertSuccess.setContentText("El ejercicio se editó correctamente.");
    
                alertSuccess.showAndWait();
                }
                
                //Deshabilitamos el botón de editar
                btnEditarEjercicio.setDisable(true);
                
            });
            
        });
       
    }

    @FXML
    void volverHome()throws IOException {
        App.setRoot("homeView");
    }

}

