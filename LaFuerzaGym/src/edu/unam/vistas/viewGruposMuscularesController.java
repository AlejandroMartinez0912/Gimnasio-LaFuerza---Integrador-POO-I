package edu.unam.vistas;

import java.io.IOException;
import java.util.List;

import edu.unam.App;
import edu.unam.modelo.GrupoMuscular;
import edu.unam.repositorio.Repositorio;
import edu.unam.servicios.ServicioGrupoMuscular;
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

public class viewGruposMuscularesController {
    private EntityManagerFactory emf;
    private Repositorio repositorio;
    private ServicioGrupoMuscular servicioGrupoMuscular;

    public viewGruposMuscularesController() {
        emf = Persistence.createEntityManagerFactory("LaFuerzaPU");
        repositorio = new Repositorio(emf);
        servicioGrupoMuscular = new ServicioGrupoMuscular(repositorio);
    }
    
    @FXML
    private Button btnGuardarNuevoGrupoMuscular;

    @FXML
    private Button btnEditarGrupoMuscular;

    @FXML
    private Button btnEliminarGrupoMuscular;

    @FXML
    private Button btnVolverHome;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private Label labelTituloApp;

    @FXML
    private Label labelGrupoMuscular;

    @FXML
    private TextField txtGrupoMuscular; 

    @FXML
    private TableView<GrupoMuscular> gruposMuscularesTable;

    @FXML
    private TableColumn<GrupoMuscular, Integer> idColumn;

    @FXML
    private TableColumn<GrupoMuscular, String> nombreColumn;

    @FXML
    void guardarNuevoGrupoMuscular(ActionEvent event) throws IOException {
        // Para mostrar un mensaje de éxito
        Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("Éxito");
        alertSuccess.setHeaderText(null);
        alertSuccess.setContentText("El grupo muscular se agregó correctamente.");
        
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText("Hubo un error al agregar el grupo muscular.");

        String nombreGrupoMuscular = txtGrupoMuscular.getText().trim(); // Obtener el texto del campo y eliminar espacios en blanco al inicio y al final

        if(!nombreGrupoMuscular.matches("^[ a-zA-ZñÑáéíóúÁÉÍÓÚ]+$")){
            alertError.setContentText("Error al registrar el grupo muscular. El nombre del grupo muscular solo puede contener letras.");
        }

        if (!nombreGrupoMuscular.isEmpty() && nombreGrupoMuscular.matches("^[ a-zA-ZñÑáéíóúÁÉÍÓÚ]+$")) { // Verificar si el campo no está vacío y contiene solo letras
            // Crear una nueva instancia de GrupoMuscular
            GrupoMuscular nuevoGrupoMuscular = new GrupoMuscular();
            nuevoGrupoMuscular.setNombre(nombreGrupoMuscular);
    
            // Llamar al servicio para agregar el nuevo GrupoMuscular
            servicioGrupoMuscular.agregarGrupoMuscular(nuevoGrupoMuscular);
            gruposMuscularesTable.getItems().add(nuevoGrupoMuscular);
            // Limpiar el campo de texto después de agregar el grupo muscular
            txtGrupoMuscular.clear();
            alertSuccess.showAndWait();

            //Reestablecemos sus promptText
            txtGrupoMuscular.setPromptText("Nombre del grupo muscular");
        } else {
            // Mostrar un mensaje de error al usuario indicando que el campo es obligatorio
            alertError.showAndWait();
        }
    }

    @FXML
    public void initialize() {

        //Deshabilitamos los botones de eliminar y editar
        btnEditarGrupoMuscular.setDisable(true);
        btnEliminarGrupoMuscular.setDisable(true);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("idGrupoMuscular"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        // Obtener todos los grupos musculares de la base de datos
        List<GrupoMuscular> gruposMusculares = servicioGrupoMuscular.obtenerTodos();

        if (gruposMusculares.isEmpty()) {
            // Si la lista está vacía, mostrar un mensaje en la tabla
            gruposMuscularesTable.setPlaceholder(new Label("No hay grupos musculares para mostrar."));

        } else {
            // Si la lista no está vacía, mostrar los grupos musculares en la tabla
            gruposMuscularesTable.getItems().addAll(gruposMusculares);
        }

        gruposMuscularesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnEditarGrupoMuscular.setDisable(false);
                btnEliminarGrupoMuscular.setDisable(false);
                btnGuardarNuevoGrupoMuscular.setDisable(true); //Deshabilitamos el botón de guardar

                txtGrupoMuscular.setText(newSelection.getNombre());
            }else{
                btnEditarGrupoMuscular.setDisable(true);
                btnEliminarGrupoMuscular.setDisable(true);
                btnGuardarNuevoGrupoMuscular.setDisable(false); //Habilitamos el botón de guardar
                txtGrupoMuscular.clear();
            }
        });

        btnEliminarGrupoMuscular.setOnAction((ActionEvent event) -> {
            //Obtenemos el grupo muscular seleccionado
            GrupoMuscular data = gruposMuscularesTable.getSelectionModel().getSelectedItem();
            // Mostrar un mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de que desea eliminar el grupo muscular?");
            if (alert.showAndWait().get().getText().equals("Aceptar")) {
                // Si el usuario confirma, eliminar el grupo muscular
                try {
                    // Llamar al servicio para eliminar el grupo muscular
                    servicioGrupoMuscular.eliminarGrupoMuscular(data);
                    gruposMuscularesTable.getItems().remove(data);
    
                    // Después de eliminar, deseleccionar el elemento
                    gruposMuscularesTable.getSelectionModel().clearSelection();
                    
                    // Para mostrar un mensaje de éxito
                    Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                    alertSuccess.setTitle("Éxito");
                    alertSuccess.setHeaderText(null);
                    alertSuccess.setContentText("El grupo muscular se eliminó correctamente.");
        
                    alertSuccess.showAndWait();
                    
                    //Reestablecemos sus promptText
                    txtGrupoMuscular.setPromptText("Nombre del grupo muscular");
                } catch (Exception e) {
                    // En caso de error, mostrar mensaje de error
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("Error");
                    alertError.setHeaderText(null);
                    alertError.setContentText("No se puede eliminar el grupo muscular. Asegúrese de que no haya ejercicios asociados a este grupo muscular.");
        
                    alertError.showAndWait();
                }
                btnEditarGrupoMuscular.setDisable(true);
                gruposMuscularesTable.getSelectionModel().clearSelection();
                gruposMuscularesTable.refresh();
            }
            
        });

        btnEditarGrupoMuscular.setOnAction((ActionEvent event) -> {
            //Obtenemos el grupo muscular seleccionado
            GrupoMuscular data = gruposMuscularesTable.getSelectionModel().getSelectedItem();
            //Mostramos un mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de que desea editar el grupo muscular?");
            if (alert.showAndWait().get().getText().equals("Aceptar")) {
                // Si el usuario confirma, editar el grupo muscular
                try {
                    data.setNombre(txtGrupoMuscular.getText());
                    servicioGrupoMuscular.editarGrupoMuscular(data);
                    gruposMuscularesTable.refresh();
        
                    // Después de editar, deseleccionar el elemento
                    gruposMuscularesTable.getSelectionModel().clearSelection();
    
                    // Mostramos mensaje de éxito
                    Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                    alertSuccess.setTitle("Éxito");
                    alertSuccess.setHeaderText(null);
                    alertSuccess.setContentText("El grupo muscular se editó correctamente.");
        
                    alertSuccess.showAndWait();

                    //Reestablecemos sus promptText
                    txtGrupoMuscular.setPromptText("Nombre del grupo muscular");
                } catch (Exception e) {
                    // En caso de error, mostramos mensaje de error
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("Error");
                    alertError.setHeaderText(null);
                    alertError.setContentText("No se puede editar el grupo muscular.");
        
                    alertError.showAndWait();
                }
                btnEditarGrupoMuscular.setDisable(true);
                gruposMuscularesTable.getSelectionModel().clearSelection();
                gruposMuscularesTable.refresh();
            }
        });
    }

    @FXML
    void volverHome() throws IOException {
        App.setRoot("homeView");
    }
}
