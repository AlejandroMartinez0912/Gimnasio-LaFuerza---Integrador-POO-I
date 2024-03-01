package edu.unam.vistas;

import edu.unam.modelo.GrupoMuscular;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class EditGrupoMuscularDialog extends Dialog<GrupoMuscular>{
    private GrupoMuscular grupoMuscular;
    private TextField nombreTextField;

    public EditGrupoMuscularDialog(GrupoMuscular grupoMuscular) {
        this.grupoMuscular = grupoMuscular;

        setTitle("Editar Grupo Muscular");
        setHeaderText(null);

        // Crear los controles para la edición
        Label nombreLabel = new Label("Grupo muscular:");
        nombreTextField = new TextField(grupoMuscular.getNombre());

        // Configurar el diseño del diálogo
        GridPane grid = new GridPane();
        grid.add(nombreLabel, 0, 0);
        grid.add(nombreTextField, 1, 0);
        getDialogPane().setContent(grid);

        // Botones de aceptar y cancelar
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Configurar el resultado al hacer clic en Aceptar
        setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                // Guardar los cambios en el grupo muscular y devolverlo como resultado
                grupoMuscular.setNombre(nombreTextField.getText());
                return grupoMuscular;
            }
            return null;
        });
    }
}
