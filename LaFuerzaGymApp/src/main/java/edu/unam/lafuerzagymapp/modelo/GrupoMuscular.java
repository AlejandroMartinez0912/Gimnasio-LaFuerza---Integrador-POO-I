package edu.unam.lafuerzagymapp.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GrupoMuscular {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int idGrupoMuscular;
    
    @Basic
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

