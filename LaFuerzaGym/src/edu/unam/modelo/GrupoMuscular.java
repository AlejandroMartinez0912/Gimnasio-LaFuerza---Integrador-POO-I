package edu.unam.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupo_muscular")
public class GrupoMuscular {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_grupo_muscular", nullable = false)
    private int idGrupoMuscular;

    @Column(name = "nombre_grupo_muscular", nullable = false, length = 80 )
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

