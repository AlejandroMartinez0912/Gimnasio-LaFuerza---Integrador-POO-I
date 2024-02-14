package com.unam.modelo;

public class GrupoMuscular {
    private int idGrupoMuscular;
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

