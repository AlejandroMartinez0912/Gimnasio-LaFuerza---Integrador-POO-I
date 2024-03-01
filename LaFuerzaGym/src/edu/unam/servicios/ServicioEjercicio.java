package edu.unam.servicios;

import edu.unam.modelo.Ejercicio;
import edu.unam.repositorio.Repositorio;

public class ServicioEjercicio {
    private Repositorio repositorio;

    public ServicioEjercicio(Repositorio p) {
        this.repositorio = p;
    }

    public void agregarEjercicio(Ejercicio ejercicio) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(ejercicio);
        this.repositorio.confirmarTransaccion();
    }

    public void editarEjercicio(Ejercicio ejercicio) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.modificar(ejercicio);
        this.repositorio.confirmarTransaccion();
    }

    public void eliminarEjercicio(Ejercicio ejercicio) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.eliminar(ejercicio);
        this.repositorio.confirmarTransaccion();
    }
    
}
