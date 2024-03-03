package edu.unam.servicios;

import edu.unam.modelo.Tutor;
import edu.unam.repositorio.Repositorio;
import java.util.List;

public class ServicioTutor {
    private Repositorio repositorio;

    public ServicioTutor(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void agregarTutor(Tutor tutor) {
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

