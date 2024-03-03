package edu.unam.vistas;

import java.io.IOException;

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

public class viewTutorController {
    @FXML
    private TableView<?> tutoresTable;

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
    private TextField txtApellidoTutor;

    @FXML
    private TextField txtNombreTutor;

    private EntityManagerFactory emf;
    private Repositorio repositorio;
    private ServicioTutor servicioTutor;

    public viewTutorController() {
        emf = Persistence.createEntityManagerFactory("LaFuerzaPU");
        repositorio = new Repositorio(emf);
        servicioTutor = new ServicioTutor(repositorio);
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
            //ServicioTutor.agregarTutor(nuevoTutor);
            //tutoresTable.getItems().add(nuevoTutor);
            
            // Limpiar los campos de texto después de agregar el tutor
            txtNombreTutor.clear();
            txtApellidoTutor.clear();
            alertSuccess.showAndWait();
        } else {
            alertError.showAndWait();
        }
    }


    @FXML
    void volverHome() throws IOException {
        App.setRoot("homeView");
    }

}

