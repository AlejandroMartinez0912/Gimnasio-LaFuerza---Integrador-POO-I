package edu.unam.vistas;

import java.util.List;
import java.util.stream.Collectors;

import edu.unam.modelo.Ejercicio;
import edu.unam.modelo.GrupoMuscular;
import edu.unam.servicios.ServicioGrupoMuscular;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class EditEjercicioDialog extends Dialog<Ejercicio>{
    private Ejercicio ejercicio;
    private TextField nombreTextField;
    private ComboBox<String> comboBoxGrupoMuscular;

    public EditEjercicioDialog(Ejercicio ejercicio, ServicioGrupoMuscular servicioGrupoMuscular) {
        this.ejercicio = ejercicio;

        setTitle("Editar Ejercicio");
        setHeaderText(null);

        comboBoxGrupoMuscular = new ComboBox<>();

        // Crear los controles para la edición
        Label nombreLabel = new Label("Ejercicio:");
        nombreTextField = new TextField(ejercicio.getNombre());

        Label grupoMuscularLabel = new Label("Grupo muscular:");

        //Obtenemos los grupos musculares
        List<GrupoMuscular> gruposMusculares = servicioGrupoMuscular.obtenerTodos();

        // Filtramos por nombres de los grupos musculares
        List<String> nombresGruposMusculares = gruposMusculares
        .stream()
        .map(GrupoMuscular::getNombre)
        .collect(Collectors.toList());

        //Guardamos los nombres en una lista observable
        ObservableList<String> items = FXCollections.observableArrayList(nombresGruposMusculares);

        //Mostramos los nombres en el combobox
        comboBoxGrupoMuscular.setItems(items);

        // Establecer el valor seleccionado del ComboBox al grupo muscular del ejercicio
        comboBoxGrupoMuscular.setValue(ejercicio.getGrupoMuscular().getNombre());

        // Configurar el diseño del diálogo
        GridPane grid = new GridPane();
        grid.add(nombreLabel, 0, 0);
        grid.add(nombreTextField, 1, 0);
        grid.add(grupoMuscularLabel, 0, 1);
        grid.add(comboBoxGrupoMuscular, 1, 1);
        getDialogPane().setContent(grid);

        // Botones de aceptar y cancelar
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Configurar el resultado al hacer clic en Aceptar
        setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                // Guardar los cambios en el grupo muscular y devolverlo como resultado
                ejercicio.setNombre(nombreTextField.getText());
                String nombreGrupoMuscularSeleccionado = comboBoxGrupoMuscular.getValue();
                GrupoMuscular grupoMuscularSeleccionado = gruposMusculares
                .stream()
                .filter(grupo -> grupo.getNombre().equals(nombreGrupoMuscularSeleccionado))
                .findFirst()
                .orElse(null);
                ejercicio.setGrupoMuscular(grupoMuscularSeleccionado);
                return ejercicio;
            }
            return null;
        });
    }
}
