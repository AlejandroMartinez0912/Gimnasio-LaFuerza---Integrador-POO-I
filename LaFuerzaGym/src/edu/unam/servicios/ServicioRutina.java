package edu.unam.servicios;

import java.util.List;

import edu.unam.modelo.Rutina;
import edu.unam.repositorio.Repositorio;

public class ServicioRutina {
    private Repositorio repositorio;

    public ServicioRutina(Repositorio p) {
        this.repositorio = p;
    }

    public void agregarRutina(Rutina rutina) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(rutina);
        this.repositorio.confirmarTransaccion();
    }

    public void editarRutina(Rutina rutina) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.modificar(rutina);
        this.repositorio.confirmarTransaccion();
    }

    public void eliminarRutina(Rutina rutina) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.eliminar(rutina);
        this.repositorio.confirmarTransaccion();
    }
    
    public List<Rutina> obtenerTodos() {
        return this.repositorio.buscarTodos(Rutina.class);
    }
}
