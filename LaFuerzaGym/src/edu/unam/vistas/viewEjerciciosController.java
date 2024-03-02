package edu.unam.vistas;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
    private Button btnVolverHome;

    @FXML
    private ComboBox<String> comboBoxGrupoMuscular;

    @FXML
    private TableColumn<Ejercicio, Void> editarColumn;

    @FXML
    private TableColumn<Ejercicio, Void> eliminarColumn;

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

        eliminarColumn.setCellFactory(new Callback<TableColumn<Ejercicio, Void>, TableCell<Ejercicio, Void>>() {
            @Override
            public TableCell<Ejercicio, Void> call(final TableColumn<Ejercicio, Void> param) {
                return new TableCell<Ejercicio, Void>() {

                    private final Button btnEliminar = crearBotonEliminar("Eliminar");

                    {
                        btnEliminar.setOnAction((ActionEvent event) -> {
                            Ejercicio data = getTableView().getItems().get(getIndex());
                            
                            try {
                                // Llamar al servicio para eliminar el ejercicio
                                servicioEjercicio.eliminarEjercicio(data);
                                tableViewEjercicios.getItems().remove(data);
                                
                                // Para mostrar un mensaje de éxito
                                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                                alertSuccess.setTitle("Éxito");
                                alertSuccess.setHeaderText(null);
                                alertSuccess.setContentText("El ejercicio se eliminó correctamente.");
                    
                                alertSuccess.showAndWait();
                            } catch (Exception e) {
                                // En caso de error, mostrar mensaje de error
                                Alert alertError = new Alert(Alert.AlertType.ERROR);
                                alertError.setTitle("Error");
                                alertError.setHeaderText(null);
                                alertError.setContentText("Hubo un error al eliminar el ejercicio.");
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

        editarColumn.setCellFactory(new Callback<TableColumn<Ejercicio, Void>, TableCell<Ejercicio, Void>>() {
            @Override
            public TableCell<Ejercicio, Void> call(final TableColumn<Ejercicio, Void> param) {
                return new TableCell<Ejercicio, Void>() {

                    private final Button btnEditar = crearBotonEditar("Editar");

                    {
                        btnEditar.setOnAction((ActionEvent event) -> {
                            Ejercicio data = getTableView().getItems().get(getIndex());
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
        editarColumn.setStyle("-fx-alignment: CENTER;");
    }

    private void abrirDialogoEdicion(Ejercicio ejercicio) {
        EditEjercicioDialog dialog = new EditEjercicioDialog(ejercicio, servicioGrupoMuscular);
        Optional<Ejercicio> result = dialog.showAndWait();
        result.ifPresent(nuevoEjercicio -> {
            servicioEjercicio.editarEjercicio(nuevoEjercicio);
            tableViewEjercicios.refresh();
        });
    }

    @FXML
    private Button crearBotonEliminar(String tooltip){
        Button boton = new Button();
        boton.setTooltip(new Tooltip(tooltip));
        boton.setPrefSize(20, 20);
        boton.setText("X");
        boton.setStyle("-fx-background-color: #ff0000; -fx-cursor: hand; -fx-text-fill: white; -fx-alignment: center;");
        return boton;
    }

    @FXML
    private Button crearBotonEditar(String tooltip){
        Button boton = new Button();
        boton.setTooltip(new Tooltip(tooltip));
        boton.setText("Editar");
        boton.setPrefSize(50, 20);
        boton.setStyle("-fx-background-color: #127e80; -fx-cursor: hand; -fx-text-fill: white; -fx-alignment: center;");
        return boton;
    }

    @FXML
    void volverHome()throws IOException {
        App.setRoot("homeView");
    }

}

