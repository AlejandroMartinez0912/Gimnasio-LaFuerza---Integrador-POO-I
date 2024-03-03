package edu.unam.vistas;

import java.io.IOException;

import edu.unam.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeViewController {

    public HomeViewController() {
    }

    
    @FXML
    private Button btnClientes;

    @FXML
    private Button btnEjercicios;

    @FXML
    private Button btnEntrenamientos;

    @FXML
    private Button btnGruposMusculares;

    @FXML
    private Button btnNuevoCliente;

    @FXML
    private Button btnNuevoEjercicio;

    @FXML
    private Button btnNuevoEntrenamiento;

    @FXML
    private Button btnNuevoGrupoMuscular;

    @FXML
    private Button btnTutor;

    @FXML
    private Label labelTituloApp;

    @FXML
    void addCliente() throws IOException {
        App.setRoot("addClientesView");
    }

    @FXML
    void addEjercicio() throws IOException  {
        App.setRoot("addEjerciciosView");
    }

    @FXML
    void addEntrenamiento() throws IOException  {
        App.setRoot("addNuevoEntrenamiento");
    }

    @FXML
    void addGrupoMuscular() throws IOException  {
        App.setRoot("addGruposMuscularesView");
    }

    @FXML
    void verEjercicios() throws IOException  {
        App.setRoot("viewEjercicios");
    }

    @FXML
    void verEntrenamientos() throws IOException  {
        App.setRoot("viewEntrenamientos");
    }

    @FXML
    void verGruposMusculares() throws IOException  {
        App.setRoot("viewGruposMusculares");
    }

    @FXML
    private void verClientes() throws IOException {
        App.setRoot("viewClientes");
    }

    @FXML
    private void verTutor() throws IOException {
        App.setRoot("viewTutor");
    }
    

}
