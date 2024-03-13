package edu.unam.vistas;

import java.io.IOException;
import java.util.List;

import edu.unam.App;
import edu.unam.modelo.EntrenamientoCliente;
import edu.unam.modelo.Rutina;
import edu.unam.repositorio.Repositorio;
import edu.unam.servicios.ServicioCliente;
import edu.unam.servicios.ServicioEntrenamientoCliente;
import edu.unam.servicios.ServicioRutina;
import edu.unam.servicios.ServicioTutor;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class viewDetalleRutinaController {
    //Declaración del repositorio
    private EntityManagerFactory emf;
    private Repositorio repositorio;
    private ServicioEntrenamientoCliente servicioEntrenamientoCliente;
    private ServicioRutina servicioRutina;
    private ServicioCliente servicioCliente;

    public viewDetalleRutinaController() {
        emf = Persistence.createEntityManagerFactory("LaFuerzaPU");
        repositorio = new Repositorio(emf);
        servicioEntrenamientoCliente = new ServicioEntrenamientoCliente(repositorio);
        servicioRutina = new ServicioRutina(repositorio);
        servicioCliente = new ServicioCliente(repositorio);
    }

    @FXML
    private Button btnEditarDetalleRutina;

    @FXML
    private Button btnEliminarDetalleRutina;

    @FXML
    private Button btnGuardarNuevoDetalleRutina;

    @FXML
    private Button btnVolverHome;

    @FXML
    private ComboBox<?> comboBoxEjercicio;

    @FXML
    private ComboBox<?> comboBoxRepeticiones;
    
    @FXML
    private Label labelEjercicio;

    @FXML
    private Label labelPeso;

    @FXML
    private Label labelRepeticiones;

    @FXML
    private Label labelSeries;

    @FXML
    private Label labelTítuloVista;

    @FXML
    private ComboBox<?> comboBoxSeries;

    @FXML
    private TableColumn<?, ?> clienteColumn;

    @FXML
    private TableColumn<?, ?> ejercicioColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> pesoColumn;

    @FXML
    private TableColumn<?, ?> repeticionesColumn;

    @FXML
    private TableColumn<?, ?> seriesColumn;

    @FXML
    private TableColumn<?, ?> volumenRutinaColumn;

    @FXML
    private TableView<?> tableDetalleEntrenamiento;

    @FXML
    private TextField txtPeso;


    @FXML 
    public void initialize(){
        //Deshabilitamos botones de edición y eliminación
        btnEditarDetalleRutina.setDisable(true);
        btnEliminarDetalleRutina.setDisable(true);

        //Obtenemos las rutinas del EntrenamientoCliente y los nombres de los ejercicios
        //EntrenamientoCliente entrenamientoCliente = servicioEntrenamientoCliente.obtenerTodos().get(0);


    }

    @FXML
    void guardarNuevoEntrenamiento(ActionEvent event) throws IOException {

    }

    @FXML
    void volverHome(ActionEvent event) throws IOException {
        App.setRoot("homeView");
    }

}
