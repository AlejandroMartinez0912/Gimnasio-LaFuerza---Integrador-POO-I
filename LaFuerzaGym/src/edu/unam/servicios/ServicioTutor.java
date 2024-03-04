package edu.unam.servicios;

import java.util.List;

import edu.unam.modelo.Tutor;
import edu.unam.repositorio.Repositorio;

public class ServicioTutor {
    private Repositorio repositorio;

    public ServicioTutor(Repositorio p) {
        this.repositorio = p;
    }
    
    public void agregarTutor (Tutor tutor) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(tutor);
        this.repositorio.confirmarTransaccion();
    }

    public void editarTutor(Tutor tutor) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.modificar(tutor);
        this.repositorio.confirmarTransaccion();
    }

    public void eliminarTutor(Tutor tutor) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.eliminar(tutor);
        this.repositorio.confirmarTransaccion();
    }

    public List<Tutor> obtenerTodos() {
        return this.repositorio.buscarTodos(Tutor.class);
    }
}

