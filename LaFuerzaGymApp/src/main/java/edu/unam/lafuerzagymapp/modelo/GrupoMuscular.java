package edu.unam.lafuerzagymapp.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="grupo_muscular")
public class GrupoMuscular implements Serializable {
    @Id
    @Column(name="id")
    private int idGrupoMuscular;
    @Column(name="nombre")
    private String nombre;

    public GrupoMuscular() {
    }

    public GrupoMuscular(int idGrupoMuscular, String nombre) {
        this.idGrupoMuscular = idGrupoMuscular;
        this.nombre = nombre;
    }

    public int getIdGrupoMuscular() {
        return idGrupoMuscular;
    }

    public void setIdGrupoMuscular(int idGrupoMuscular) {
        this.idGrupoMuscular = idGrupoMuscular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

