package edu.unam.vistas;

import java.io.IOException;

import edu.unam.App;
import edu.unam.modelo.Tutor;
import edu.unam.servicios.ServicioTutor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class viewTutorController {
    @FXML
    private TableView<Tutor> TutoresTable;

    @FXML
    private Button btnGuardarTutor;

    @FXML
    private Button btnVolverHome;

    @FXML
    private TableColumn<?, ?> editColumn;

    @FXML
    private TableColumn<?, ?> eliminarColumn;

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
    private TextField txtApellidoTutor;

    @FXML
    private TextField txtNombreTutor;

    @FXML
    private Button crearBotonEliminar(String tooltip){
        Button boton = new Button();
        boton.setTooltip(new Tooltip(tooltip));
        boton.setPrefSize(20, 20);
        boton.setText("X");
        boton.setStyle("-fx-background-color: #ff0000; -fx-cursor: hand; -fx-text-fill: white; -fx-alignment: center;");
        return boton;
    }

    private ServicioTutor ServicioTutor;  // Agregamos una propiedad para el servicio

    public viewTutorController() {
        this.ServicioTutor = new ServicioTutor();  // Creamos una instancia del servicio en el constructor
    }

    @FXML
    void guardarTutor(ActionEvent event) {
        Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("Éxito");
        alertSuccess.setHeaderText(null);
        alertSuccess.setContentText("El tutor se agregó correctamente.");

        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(null);
        alertError.setContentText("Hubo un error al agregar el tutor.");

        String nombreTutor = txtNombreTutor.getText().trim();
        String apellidoTutor = txtApellidoTutor.getText().trim();

         if (!nombreTutor.isEmpty() && !apellidoTutor.isEmpty()) {
            Tutor nuevoTutor = new Tutor();
            nuevoTutor.setNombre(nombreTutor);
            nuevoTutor.setApellido(apellidoTutor);

            // Llamar al servicio para agregar el nuevo Tutor
            ServicioTutor.agregarTutor(nuevoTutor);
            TutoresTable.getItems().add(nuevoTutor);
            
            // Limpiar los campos de texto después de agregar el tutor
            txtNombreTutor.clear();
            txtApellidoTutor.clear();
            alertSuccess.showAndWait();
        } else {
            alertError.showAndWait();
        }
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
    void volverHome() throws IOException {
        App.setRoot("homeView");
    }

}

