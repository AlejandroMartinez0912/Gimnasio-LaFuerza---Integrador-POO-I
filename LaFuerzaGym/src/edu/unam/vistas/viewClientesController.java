package edu.unam.vistas;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import edu.unam.App;
import edu.unam.modelo.Cliente;
import edu.unam.repositorio.Repositorio;
import edu.unam.servicios.ServicioCliente;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class viewClientesController {
    private final Repositorio Repositorio;
    private final ServicioCliente ServicioCliente;
    private EntityManagerFactory emf;

    public viewClientesController() {
        emf = Persistence.createEntityManagerFactory("LaFuerzaPU");
        Repositorio = new Repositorio(emf);
        ServicioCliente = new ServicioCliente(Repositorio);
    }

    @FXML
    private TableColumn<Cliente, String> apellidoColumn;

    @FXML
    private Button btnActualizarCliente;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private Button btnGuardarNuevoCliente;

    @FXML
    private Button btnVolverHome;

    @FXML
    private TableView<Cliente> clientesTable;

    @FXML
    private ComboBox<String> comboBoxSexo;

    @FXML
    private DatePicker dateFechaIngreso;

    @FXML
    private DatePicker dateFechaNacimiento;

    @FXML
    private TableColumn<Cliente, LocalDate> fechaIngresoColumn;

    @FXML
    private TableColumn<Cliente, LocalDate> fechaNacimientoColumn;

    @FXML
    private TableColumn<Cliente, Long> idColumn;

    @FXML
    private Label labelApellido;

    @FXML
    private Label labelFechaIngreso;

    @FXML
    private Label labelFechaNacimiento;

    @FXML
    private Label labelNombres;

    @FXML
    private Label labelSexo;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private TableColumn<Cliente, String> nombreColumn;

    @FXML
    private TableColumn<Cliente, String> sexoColumn;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtNombre;

    @FXML
    private void guardarNuevoCliente(ActionEvent event) {
        Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("Éxito");
        alertSuccess.setHeaderText(null);

        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);

        String nombreCliente = txtNombre.getText();
        String apellidoCliente = txtApellido.getText();
        String sexoCliente = comboBoxSexo.getValue();
        LocalDate fechaNacimiento = dateFechaNacimiento.getValue();
        LocalDate fechaIngreso = dateFechaIngreso.getValue();

        if (nombreCliente.isEmpty() || apellidoCliente.isEmpty() || sexoCliente == null || fechaNacimiento == null
                || fechaIngreso == null) {
            alertError.setContentText("Todos los campos son obligatorios.");
            alertError.showAndWait();
            return;
        }

        if (!nombreCliente.matches("^[ a-zA-ZñÑáéíóúÁÉÍÓÚ]+$")) {
            alertError.setContentText(
                    "Error al registrar el cliente. El nombre del cliente solo puede contener letras.");
            alertError.showAndWait();
            txtNombre.clear();
            txtNombre.setPromptText("Nombre");
            return;
        }

        if (!apellidoCliente.matches("^[ a-zA-ZñÑáéíóúÁÉÍÓÚ]+$")) {
            alertError.setContentText(
                    "Error al registrar el cliente. El apellido del cliente solo puede contener letras.");
            alertError.showAndWait();
            return;
        }

        if (sexoCliente == null) {
            alertError.setContentText("Error al registrar el cliente. El sexo del cliente es obligatorio.");
            alertError.showAndWait();
            comboBoxSexo.setPromptText("Sexo");
            return;
        }

        try {
            Cliente cliente = new Cliente();
            cliente.setNombre(nombreCliente);
            cliente.setApellido(apellidoCliente);
            cliente.setSexo(sexoCliente);
            cliente.setFechaNacimiento(fechaNacimiento);
            cliente.setFechaIngreso(fechaIngreso);

            ServicioCliente.agregarCliente(cliente);
            clientesTable.getItems().add(cliente);

            txtApellido.clear();
            txtNombre.clear();
            comboBoxSexo.setValue(null);
            dateFechaNacimiento.setValue(null);
            dateFechaIngreso.setValue(null);

            alertSuccess.setContentText("Cliente guardado con éxito.");
            alertSuccess.showAndWait();

            // Reestablecemos sus PromptText
            txtNombre.setPromptText("Nombre");
            txtApellido.setPromptText("Apellido");
            comboBoxSexo.setPromptText("Sexo");
            dateFechaNacimiento.setPromptText("dd/mm/yy");
            dateFechaIngreso.setPromptText("dd/mm/yy");
        } catch (Exception e) {
            alertError.setContentText("Error al guardar el cliente.");
            alertError.showAndWait();
        }
    }

    @FXML
    public void initialize() {
        // Deshabilitar el botón eliminar y editar
        btnActualizarCliente.setDisable(true);
        btnEliminarCliente.setDisable(true);
        btnGuardarNuevoCliente.setDisable(false);

        // Obtener los clientes
        List<Cliente> clientes = ServicioCliente.obtenerTodos();

        // Configurar la tabla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        sexoColumn.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        fechaNacimientoColumn.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        fechaIngresoColumn.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));

        // Configurar el ComboBox de Sexo
        ObservableList<String> sexos = FXCollections.observableArrayList("Masculino", "Femenino", "Otro");
        comboBoxSexo.setItems(sexos);

        // Si la lista de clientes está vacía, mostrar un mensaje en la tabla
        if (clientes.isEmpty()) {
            clientesTable.setPlaceholder(new Label("No hay clientes registrados."));
        } else {
            // Si hay clientes, mostrarlos en la tabla
            clientesTable.setItems(FXCollections.observableArrayList(clientes));
        }

        clientesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnActualizarCliente.setDisable(false);
                btnEliminarCliente.setDisable(false);
                btnGuardarNuevoCliente.setDisable(true); // Deshabilitar el botón de guardar para evitar guardar
                                                          // datos duplicados

                txtNombre.setText(newSelection.getNombre());
                txtApellido.setText(newSelection.getApellido());
                comboBoxSexo.setValue(newSelection.getSexo());
                dateFechaNacimiento.setValue(newSelection.getFechaNacimiento());
                dateFechaIngreso.setValue(newSelection.getFechaIngreso());
            } else {
                btnActualizarCliente.setDisable(true);
                btnEliminarCliente.setDisable(true);
                btnGuardarNuevoCliente.setDisable(false); // Habilitar el botón de guardar
                txtNombre.clear();
                txtApellido.clear();
                comboBoxSexo.setValue(null);
                dateFechaNacimiento.setValue(null);
                dateFechaIngreso.setValue(null);
            }
        });

        btnEliminarCliente.setOnAction((ActionEvent event) -> {
            // Obtener el cliente seleccionado
            Cliente cliente = clientesTable.getSelectionModel().getSelectedItem();
            // Mostramos un mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro que desea eliminar el cliente?");
            if (alert.showAndWait().get().getText().equals("Aceptar")) {
                // Si el usuario presiona OK, eliminamos el cliente
                try {
                    // Llamar al servicio para eliminar el cliente
                    ServicioCliente.eliminarCliente(cliente);
                    clientesTable.getItems().remove(cliente);

                    // Para mostrar un mensaje de éxito
                    Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                    alertSuccess.setTitle("Éxito");
                    alertSuccess.setHeaderText(null);
                    alertSuccess.setContentText("Cliente eliminado con éxito.");

                    alertSuccess.showAndWait();

                    // Reestablecemos sus PromptText
                    txtNombre.setPromptText("Nombre");
                    txtApellido.setPromptText("Apellido");
                    comboBoxSexo.setPromptText("Sexo");
                    dateFechaNacimiento.setPromptText("dd/mm/yy");
                    dateFechaIngreso.setPromptText("dd/mm/yy");

                } catch (Exception e) {
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("Error");
                    alertError.setHeaderText(null);
                    alertError.setContentText("Error al eliminar el cliente.");
                    alertError.showAndWait();
                }
                btnEliminarCliente.setDisable(true);
                clientesTable.getSelectionModel().clearSelection();
                clientesTable.refresh();
            }
        });

        btnActualizarCliente.setOnAction((ActionEvent event) -> {
            // Obtener el cliente seleccionado
            Cliente cliente = clientesTable.getSelectionModel().getSelectedItem();
            // Mostramos un mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro que desea actualizar el cliente?");
            if (alert.showAndWait().get().getText().equals("Aceptar")) {
                // Si el usuario presiona OK, actualizamos el cliente
                try {
                    // Llamar al servicio para editar el cliente
                    cliente.setNombre(txtNombre.getText());
                    cliente.setApellido(txtApellido.getText());
                    cliente.setSexo(comboBoxSexo.getValue());
                    cliente.setFechaNacimiento(dateFechaNacimiento.getValue());
                    cliente.setFechaIngreso(dateFechaIngreso.getValue());

                    ServicioCliente.editarCliente(cliente);
                    clientesTable.refresh();

                    // Para mostrar un mensaje de éxito
                    Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                    alertSuccess.setTitle("Éxito");
                    alertSuccess.setHeaderText(null);
                    alertSuccess.setContentText("Cliente actualizado con éxito.");

                    alertSuccess.showAndWait();

                    // Reestablecemos sus PromptText
                    txtNombre.setPromptText("Nombre");
                    txtApellido.setPromptText("Apellido");
                    comboBoxSexo.setPromptText("Sexo");
                    dateFechaNacimiento.setPromptText("dd/mm/yy");
                    dateFechaIngreso.setPromptText("dd/mm/yy");
                } catch (Exception e) {
                    // En caso de error, mostrar mensaje de error
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("Error");
                    alertError.setHeaderText(null);
                    alertError.setContentText("Error al actualizar el cliente.");
                    alertError.showAndWait();
                }
                btnActualizarCliente.setDisable(true);
                clientesTable.getSelectionModel().clearSelection();
                clientesTable.refresh();
            }
        });
    }

    @FXML
    private void volverHome() throws IOException {
        App.setRoot("homeView");
    }
}
