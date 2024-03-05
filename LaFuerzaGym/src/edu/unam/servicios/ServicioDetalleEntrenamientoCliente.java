package edu.unam.servicios;

import java.util.List;

import edu.unam.modelo.DetalleEntrenamientoCliente;
import edu.unam.repositorio.Repositorio;

public class ServicioDetalleEntrenamientoCliente {
    
    private Repositorio repositorio;

    public ServicioDetalleEntrenamientoCliente(Repositorio p) {
        this.repositorio = p;
    }

    public void agregarCliente(DetalleEntrenamientoCliente detalleEntrenamientoCliente) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(detalleEntrenamientoCliente);
        this.repositorio.confirmarTransaccion();
    }

    public void editarCliente(DetalleEntrenamientoCliente detalleEntrenamientoCliente) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.modificar(detalleEntrenamientoCliente);
        this.repositorio.confirmarTransaccion();
    }

    public void eliminarCliente(DetalleEntrenamientoCliente detalleEntrenamientoCliente) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.eliminar(detalleEntrenamientoCliente);
        this.repositorio.confirmarTransaccion();
    }

    public List<DetalleEntrenamientoCliente> obtenerTodos() {
        return this.repositorio.buscarTodos(DetalleEntrenamientoCliente.class);
    }

}
