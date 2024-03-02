package edu.unam.vistas;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.unam.App;
import edu.unam.modelo.GrupoMuscular;
import edu.unam.repositorio.Repositorio;
import edu.unam.servicios.ServicioGrupoMuscular;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
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
    private Button btnGuardarNuevoCliente;

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
    private TableColumn<GrupoMuscular, Void> eliminarColumn;

    @FXML
    private TableColumn<GrupoMuscular, Void> editColumn;

    @FXML
    private Button btnEliminar;
    
    @FXML
    private Button btnEditar;

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

        if (!nombreGrupoMuscular.isEmpty() && nombreGrupoMuscular.matches("[a-zA-Z]+")) { // Verificar si el campo no está vacío y contiene solo letras
            // Crear una nueva instancia de GrupoMuscular
            GrupoMuscular nuevoGrupoMuscular = new GrupoMuscular();
            nuevoGrupoMuscular.setNombre(nombreGrupoMuscular);
    
            // Llamar al servicio para agregar el nuevo GrupoMuscular
            servicioGrupoMuscular.agregarGrupoMuscular(nuevoGrupoMuscular);
            gruposMuscularesTable.getItems().add(nuevoGrupoMuscular);
            // Limpiar el campo de texto después de agregar el grupo muscular
            txtGrupoMuscular.clear();
            alertSuccess.showAndWait();
        } else {
            // Mostrar un mensaje de error al usuario indicando que el campo es obligatorio
            alertError.showAndWait();
        }
    }

    @FXML
    public void initialize() {
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

        eliminarColumn.setCellFactory(new Callback<TableColumn<GrupoMuscular, Void>, TableCell<GrupoMuscular, Void>>() {
            @Override
            public TableCell<GrupoMuscular, Void> call(final TableColumn<GrupoMuscular, Void> param) {
                return new TableCell<GrupoMuscular, Void>() {

                    private final Button btnEliminar = crearBotonEliminar("Eliminar");

                    {
                        btnEliminar.setOnAction((ActionEvent event) -> {
                            GrupoMuscular data = getTableView().getItems().get(getIndex());
                            
                            try {
                                // Llamar al servicio para eliminar el grupo muscular
                                servicioGrupoMuscular.eliminarGrupoMuscular(data);
                                gruposMuscularesTable.getItems().remove(data);
                                
                                // Para mostrar un mensaje de éxito
                                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                                alertSuccess.setTitle("Éxito");
                                alertSuccess.setHeaderText(null);
                                alertSuccess.setContentText("El grupo muscular se eliminó correctamente.");
                    
                                alertSuccess.showAndWait();
                            } catch (Exception e) {
                                // En caso de error, mostrar mensaje de error
                                Alert alertError = new Alert(Alert.AlertType.ERROR);
                                alertError.setTitle("Error");
                                alertError.setHeaderText(null);
                                alertError.setContentText("No se puede eliminar el grupo muscular. Asegúrese de que no haya ejercicios asociados a este grupo muscular.");
                    
                                alertError.showAndWait();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnEliminar);
                        }
                    }
                };
            }
        });
        eliminarColumn.setStyle("-fx-alignment: CENTER;");

        editColumn.setCellFactory(new Callback<TableColumn<GrupoMuscular, Void>, TableCell<GrupoMuscular, Void>>() {
            @Override
            public TableCell<GrupoMuscular, Void> call(final TableColumn<GrupoMuscular, Void> param) {
                return new TableCell<GrupoMuscular, Void>() {

                    private final Button btnEditar = crearBotonEditar("Editar");

                    {
                        btnEditar.setOnAction((ActionEvent event) -> {
                            GrupoMuscular data = getTableView().getItems().get(getIndex());
                            abrirDialogoEdicion(data);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnEditar);
                        }
                    }
                };
            }
        });
        editColumn.setStyle("-fx-alignment: CENTER;");
    }

    private void abrirDialogoEdicion(GrupoMuscular grupoMuscular) {
        EditGrupoMuscularDialog dialog = new EditGrupoMuscularDialog(grupoMuscular);
        Optional<GrupoMuscular> result = dialog.showAndWait();
        result.ifPresent(grupo -> {
            servicioGrupoMuscular.editarGrupoMuscular(grupo);
            gruposMuscularesTable.refresh();
        });
    }

    @FXML
    private Button crearBotonEliminar(String tooltip){
        Button boton = new Button();
        boton.setTooltip(new Tooltip(tooltip));
        boton.setPrefSize(20, 20);
        boton.setText("X");
        boton.setStyle("-fx-background-color: #ff0000; -fx-text-fill: white; -fx-alignment: center;");
        return boton;
    }

    @FXML
    private Button crearBotonEditar(String tooltip){
        Button boton = new Button();
        boton.setTooltip(new Tooltip(tooltip));
        boton.setText("Editar");
        boton.setPrefSize(50, 20);
        boton.setStyle("-fx-background-color: #ffff00; -fx-text-fill: white; -fx-alignment: center;");
        return boton;
    }

    @FXML
    void volverHome() throws IOException {
        App.setRoot("homeView");
    }
}
