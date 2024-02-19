package com.unam.vistas;

import java.io.IOException;

import com.unam.App;

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
        App.setRoot("addClientesView");
    }

    @FXML
    void addEjercicio() throws IOException  {
        App.setRoot("addEjerciciosView");
    }

    @FXML
    void addEntrenamiento() throws IOException  {

    }

    @FXML
    void addGrupoMuscular() throws IOException  {
        App.setRoot("addGruposMuscularesView");
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
        
        
    }

    
}
