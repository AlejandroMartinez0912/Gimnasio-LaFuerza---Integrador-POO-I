package com.unam.vistas;

import java.io.IOException;

import com.unam.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeViewController {

    public HomeViewController() {
    }

    @FXML
    private Button btnClientes;

    @FXML
    private void cambiarVista() throws IOException {
        //Cambiar de vista a "primary"
        App.setRoot("nuevoClienteView");
    }

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
}
