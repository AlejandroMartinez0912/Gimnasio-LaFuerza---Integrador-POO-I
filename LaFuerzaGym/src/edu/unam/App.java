package edu.unam;

import java.io.IOException;

import edu.unam.repositorio.Repositorio;
import edu.unam.servicios.ServicioEjercicio;
import edu.unam.servicios.ServicioGrupoMuscular;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import edu.unam.servicios.ServicioCliente;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LaFuerzaPU");

        // Crear Repositorio
        Repositorio repositorio = new Repositorio(emf);

        ServicioGrupoMuscular servicioGrupoMuscular = new ServicioGrupoMuscular(repositorio);
        ServicioEjercicio servicioEjercicio = new ServicioEjercicio(repositorio);
        ServicioCliente servicioCliente = new ServicioCliente(repositorio);
        scene = new Scene(loadFXML("homeView"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/edu/unam/vistas/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
