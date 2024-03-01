package edu.unam.servicios;

import java.util.List;

import edu.unam.modelo.GrupoMuscular;
import edu.unam.repositorio.Repositorio;

public class ServicioGrupoMuscular {
    private Repositorio repositorio;

    public ServicioGrupoMuscular(Repositorio p) {
        this.repositorio = p;
    }
    
    public void agregarGrupoMuscular(GrupoMuscular grupoMuscular) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.insertar(grupoMuscular);
        this.repositorio.confirmarTransaccion();
    }

    public void editarGrupoMuscular(GrupoMuscular grupoMuscular) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.modificar(grupoMuscular);
        this.repositorio.confirmarTransaccion();
    }

    public void eliminarGrupoMuscular(GrupoMuscular grupoMuscular) {
        this.repositorio.iniciarTransaccion();
        this.repositorio.eliminar(grupoMuscular);
        this.repositorio.confirmarTransaccion();
    }

    public List<GrupoMuscular> obtenerTodos() {
        return this.repositorio.buscarTodos(GrupoMuscular.class);
    }
}
