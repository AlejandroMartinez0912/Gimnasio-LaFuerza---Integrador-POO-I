package edu.unam.vistas;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private ComboBox<String> comboBoxEjercicio;

    @FXML
    private ComboBox<Integer> comboBoxRepeticiones;
    
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
    private ComboBox<Integer> comboBoxSeries;

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

    private EntrenamientoCliente entrenamientoCliente;

    public void initEntrenamientoCliente(EntrenamientoCliente entrenamientoCliente){
        this.entrenamientoCliente = entrenamientoCliente;

        //Obtenemos las rutinas del EntrenamientoCliente y los nombres de los ejercicios
        Set<Rutina> rutinas = entrenamientoCliente.getRutinas();
        //Creamos un HashSet para guardar los nombres y así evitar duplicados
        Set<String> nombresEjercicios = new HashSet<String>();
        //Obtenemos los ejercicios de la rutina
        for(Rutina rutina : rutinas){
            //Obtenemos el ejercicio de la rutina y lo guardamos en el hashset
            nombresEjercicios.add(rutina.getEjercicio().getNombre());
        }

        //Agregamos los nombres de los ejercicios al comboBox
        comboBoxEjercicio.getItems().addAll(nombresEjercicios);

    }

    @FXML 
    public void initialize(){
        //Deshabilitamos botones de edición y eliminación
        btnEditarDetalleRutina.setDisable(true);
        btnEliminarDetalleRutina.setDisable(true);

        //Agregamos los valores al combobox series
        comboBoxSeries.getItems().addAll(1,2,3,4,5,6,7,8,9,10);

        //Agregamos los valores al combobox repeticiones
        comboBoxRepeticiones.getItems().addAll(1,2,3,4,5,6,7,8,9,10);

    }

    @FXML
    void guardarNuevoEntrenamiento(ActionEvent event) throws IOException {

    }

    @FXML
    void volverHome(ActionEvent event) throws IOException {
        App.setRoot("homeView");
    }

}
