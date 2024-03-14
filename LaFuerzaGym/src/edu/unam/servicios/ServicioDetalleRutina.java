package edu.unam.servicios;

import java.util.List;

import edu.unam.modelo.DetalleRutina;
import edu.unam.repositorio.Repositorio;

public class ServicioDetalleRutina {
    
    private Repositorio repositorio;

    public ServicioDetalleRutina(Repositorio p) {
        this.repositorio = p;
    }

    public void agregarDetalleRutina(DetalleRutina detalleRutina) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(detalleRutina);
        this.repositorio.confirmarTransaccion();
    }

    public void editarDetalleRutina(DetalleRutina detalleRutina) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.modificar(detalleRutina);
        this.repositorio.confirmarTransaccion();
    }

    public void eliminarDetalleRutina(DetalleRutina detalleRutina) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.eliminar(detalleRutina);
        this.repositorio.confirmarTransaccion();
    }

    public List<DetalleRutina> obtenerTodos() {
        return this.repositorio.buscarTodos(DetalleRutina.class);
    }

}
