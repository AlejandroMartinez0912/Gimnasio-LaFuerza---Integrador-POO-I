package com.unam.vistas;

import java.io.IOException;

import com.unam.App;

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
    private Label labelTituloApp;

    @FXML
    void addCliente() throws IOException {

    }

    @FXML
    void addEjercicio() throws IOException  {

    }

    @FXML
    void addEntrenamiento() throws IOException  {

    }

    @FXML
    void addGrupoMuscular() throws IOException  {

    }

    @FXML
    void verEjercicios() throws IOException  {

    }

    @FXML
    void verEntrenamientos() throws IOException  {

    }

    @FXML
    void verGruposMusculares() throws IOException  {

    }

    @FXML
    private void verClientes() throws IOException {
        //Cambiar de vista a "primary"
        App.setRoot("nuevoClienteView");
    }

    
}
