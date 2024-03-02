package edu.unam.servicios;

import edu.unam.modelo.Cliente;
import edu.unam.repositorio.Repositorio;
import java.util.List;
public class ServicioCliente {
    
    private Repositorio repositorio;

    public ServicioCliente(Repositorio p) {
        this.repositorio = p;
    }

    public void agregarCliente(Cliente cliente) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(cliente);
        this.repositorio.confirmarTransaccion();
    }

    public void editarCliente(Cliente cliente) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.modificar(cliente);
        this.repositorio.confirmarTransaccion();
    }

    public void eliminarCliente(Cliente cliente) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.eliminar(cliente);
        this.repositorio.confirmarTransaccion();
    }

    public List<Cliente> obtenerTodos() {
        return this.repositorio.buscarTodos(Cliente.class);
    }
    
}
