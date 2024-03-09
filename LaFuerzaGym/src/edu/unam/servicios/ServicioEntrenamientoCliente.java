package edu.unam.servicios;
import java.util.List;

import edu.unam.modelo.Rutina;
import edu.unam.modelo.EntrenamientoCliente;
import edu.unam.repositorio.Repositorio;

public class ServicioEntrenamientoCliente {
    private Repositorio repositorio;
    private EntrenamientoCliente entrenamientoCliente;
    

    public ServicioEntrenamientoCliente(Repositorio p) {
        this.repositorio = p;
    }

    public void agregarEntrenamientoCliente(EntrenamientoCliente entrenamientoCliente) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(entrenamientoCliente);
        this.repositorio.confirmarTransaccion();
    }

    public void editarEntrenamientoCliente(EntrenamientoCliente entrenamientoCliente) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.modificar(entrenamientoCliente);
        this.repositorio.confirmarTransaccion();
    }

    public void eliminarEntrenamientoCliente(EntrenamientoCliente entrenamientoCliente) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.eliminar(entrenamientoCliente);
        this.repositorio.confirmarTransaccion();
    }

   public List<EntrenamientoCliente> obtenerTodos() {
        return this.repositorio.buscarTodos(EntrenamientoCliente.class);
    }

}
