package edu.unam.servicios;

import java.util.List;

import edu.unam.modelo.Entrenamiento;
import edu.unam.repositorio.Repositorio;

public class ServicioEntrenamiento {
    private Repositorio repositorio;

    public ServicioEntrenamiento(Repositorio p) {
        this.repositorio = p;
    }

    public void agregarEntrenamiento(Entrenamiento entrenamiento) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(entrenamiento);
        this.repositorio.confirmarTransaccion();
    }

    public void editarEntrenamiento(Entrenamiento entrenamiento) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.modificar(entrenamiento);
        this.repositorio.confirmarTransaccion();
    }

    public void eliminarEntrenamiento(Entrenamiento entrenamiento) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.eliminar(entrenamiento);
        this.repositorio.confirmarTransaccion();
    }
    
    public List<Entrenamiento> obtenerTodos() {
        return this.repositorio.buscarTodos(Entrenamiento.class);
    }
}
