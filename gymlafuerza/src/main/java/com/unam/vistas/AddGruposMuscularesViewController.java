package com.unam.vistas;

import java.io.IOException;

import com.unam.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class AddGruposMuscularesViewController {
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
        
    }

    @FXML
    void volverHome() throws IOException {
        App.setRoot("homeView");
    }
}
