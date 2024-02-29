package edu.unam.vistas;

import java.io.IOException;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class AddGruposMuscularesViewController {
    private EntityManagerFactory emf;
    private Repositorio repositorio;
    private ServicioGrupoMuscular servicioGrupoMuscular;

    public AddGruposMuscularesViewController() {
        emf = Persistence.createEntityManagerFactory("LaFuerzaPU");
        repositorio = new Repositorio(emf);
        servicioGrupoMuscular = new ServicioGrupoMuscular(repositorio);
    }

    @FXML
    private Button btnGuardarNuevoGrupoMuscular;

    @FXML
    private Button btnVolverHome;

    @FXML
    private Label labelGrupoMuscular;

    @FXML
    private Label labelTituloApp;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private Label lblObligatorio;

    @FXML
    private TextField txtGrupoMuscular;

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
    
            // Limpiar el campo de texto después de agregar el grupo muscular
            txtGrupoMuscular.clear();
            alertSuccess.showAndWait();
        } else {
            // Mostrar un mensaje de error al usuario indicando que el campo es obligatorio
            alertError.showAndWait();
        }
    }

    @FXML
    void volverHome() throws IOException {
        App.setRoot("homeView");
    }
}
