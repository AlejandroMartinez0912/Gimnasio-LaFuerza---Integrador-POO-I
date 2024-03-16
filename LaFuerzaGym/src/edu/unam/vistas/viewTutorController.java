package edu.unam.vistas;

import java.io.IOException;
import java.util.List;

import edu.unam.App;
import edu.unam.modelo.Tutor;
import edu.unam.repositorio.Repositorio;
import edu.unam.servicios.ServicioTutor;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class viewTutorController {

    private EntityManagerFactory emf;
    private Repositorio repositorio;
    private ServicioTutor servicioTutor;
    
    public viewTutorController() {
        emf = Persistence.createEntityManagerFactory("LaFuerzaPU");
        repositorio = new Repositorio(emf);
        servicioTutor = new ServicioTutor(repositorio);
    }

    @FXML
    private TableColumn<?, ?> apellidoColumn;

    @FXML
    private Button btnEditarTutor;

    @FXML
    private Button btnEliminarTutor;

    @FXML
    private Button btnGuardarTutor;

    @FXML
    private Button btnVolverHome;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private Label labelApellidoTutor;

    @FXML
    private Label labelNombreTutor;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private TableColumn<?, ?> nombreColumn;

    @FXML
    private TableView<Tutor> tutoresTable;

    @FXML
    private TextField txtApellidoTutor;

    @FXML
    private TextField txtNombreTutor;

    @FXML
    void guardarTutor(ActionEvent event) {
        // Para mostrar un mensaje de éxito
        Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("Éxito");
        alertSuccess.setHeaderText(null);
        alertSuccess.setContentText("El tutor se agregó correctamente.");
        
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText("Hubo un error al agregar el tutor.");

        String nombreTutor = txtNombreTutor.getText().trim(); // Obtener el texto del campo y eliminar espacios en blanco al inicio y al final
        String apellidoTutor = txtApellidoTutor.getText().trim();

        if(!nombreTutor.matches("[a-zA-Z ]+")){
            alertError.setContentText("Error al registrar el tutor. El nombre del tutor solo puede contener letras.");
        }

        if(!apellidoTutor.matches("[a-zA-Z ]+")){
            alertError.setContentText("Error al registrar el tutor. El apellido del tutor solo puede contener letras.");
        }

        if(nombreTutor.isEmpty() || apellidoTutor.isEmpty()){
            alertError.setContentText("Error al registrar el tutor. Los campos no pueden estar vacíos.");
            alertError.showAndWait();
        }

        if (!nombreTutor.isEmpty() && nombreTutor.matches("[a-zA-Z ]+") && !apellidoTutor.isEmpty() && apellidoTutor.matches("[a-zA-Z ]+")) { // Verificar si el campo no está vacío y contiene solo letras
            // Crear una nueva instancia de Tutor
            Tutor nuevoTutor = new Tutor();
            nuevoTutor.setNombre(nombreTutor);
            nuevoTutor.setApellido(apellidoTutor);

            // Llamar al servicio para agregar el nuevo GrupoMuscular
            servicioTutor.agregarTutor(nuevoTutor);
            tutoresTable.getItems().add(nuevoTutor);

            // Limpiar los campos de texto después de agregar el tutor
            txtNombreTutor.clear();
            txtApellidoTutor.clear();
            alertSuccess.showAndWait();

        } else {
            // Mostrar un mensaje de error al usuario indicando que el campo es obligatorio
            alertError.showAndWait();
        }
    }
    @FXML
    public void initialize() {
        //Deshabilitar los botones de editar y eliminar al inicio
        btnEditarTutor.setDisable(true);
        btnEliminarTutor.setDisable(true);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("idTutor"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        // Obtener todos los tutores de la base de datos
        List<Tutor> tutores = servicioTutor.obtenerTodos();

        if (tutores.isEmpty()) {
            // Si la lista está vacía, mostrar un mensaje en la tabla
            tutoresTable.setPlaceholder(new Label("No hay tutores para mostrar."));
        } else {
            // Si la lista no está vacía, mostrar los tutores en la tabla
            tutoresTable.getItems().addAll(tutores);
         }
         tutoresTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnEditarTutor.setDisable(false);
                btnEliminarTutor.setDisable(false);
    
                txtNombreTutor.setText(newSelection.getNombre());
                txtApellidoTutor.setText(newSelection.getApellido());
            } else {
                btnEditarTutor.setDisable(true);
                btnEliminarTutor.setDisable(true);
                txtNombreTutor.clear();
                txtApellidoTutor.clear();
            }
        });
        btnEliminarTutor.setOnAction((ActionEvent event) -> {
            //Obtenemos el tutor seleccionado
            Tutor data = tutoresTable.getSelectionModel().getSelectedItem();
            //Mostramos un mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro que quieres eliminar el tutor?");
            if (alert.showAndWait().get().getText().equals("Aceptar")) 
                //Si el usuario presiona OK, eliminamos el tutor
            {    
            try {
                // Llamar al servicio para eliminar el tutor
                servicioTutor.eliminarTutor(data);
                tutoresTable.getItems().remove(data);
    
                // Después de eliminar, deseleccionar el elemento
                tutoresTable.getSelectionModel().clearSelection();
    
                // Para mostrar un mensaje de éxito
                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                alertSuccess.setTitle("Éxito");
                alertSuccess.setHeaderText(null);
                alertSuccess.setContentText("El tutor se eliminó correctamente.");
    
                alertSuccess.showAndWait();
            } catch (Exception e) {
                // En caso de error, mostrar mensaje de error
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText(null);
                alertError.setContentText("No se puede eliminar el tutor.");
    
                alertError.showAndWait();
            }
            btnEliminarTutor.setDisable(true);
        }
        });
    
        btnEditarTutor.setOnAction((ActionEvent event) -> {
            //Obtenemos el tutor seleccionado
            Tutor data = tutoresTable.getSelectionModel().getSelectedItem();
            //Mostramos un mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro que quieres editar el tutor?");
            if (alert.showAndWait().get().getText().equals("Aceptar")) 
                //Si el usuario presiona OK, editamos el tutor
            {    
            try {
                data.setNombre(txtNombreTutor.getText());
                data.setApellido(txtApellidoTutor.getText());
                servicioTutor.editarTutor(data);
                tutoresTable.refresh();
    
                // Después de editar, deseleccionar el elemento
                tutoresTable.getSelectionModel().clearSelection();
    
                // Mostramos mensaje de éxito
                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                alertSuccess.setTitle("Éxito");
                alertSuccess.setHeaderText(null);
                alertSuccess.setContentText("El tutor se editó correctamente.");
    
                alertSuccess.showAndWait();
            } catch (Exception e) {
                // En caso de error, mostramos mensaje de error
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText(null);
                alertError.setContentText("No se puede editar el tutor.");
    
                alertError.showAndWait();
            }
            btnEditarTutor.setDisable(true);
        }
        });
    }

    @FXML
    void volverHome() throws IOException {
        App.setRoot("homeView");
    }
}
