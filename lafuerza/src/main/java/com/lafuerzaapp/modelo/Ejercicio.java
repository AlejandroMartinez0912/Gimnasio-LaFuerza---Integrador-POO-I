package com.lafuerzaapp.modelo;

import java.util.Set;

public class Ejercicio {
    private int idEjercicio;
    private String nombre;
    private String descripcion;
    private Set<GrupoMuscular> gruposMusculares;

    public Ejercicio() {
    }

    public Ejercicio(int idEjercicio, String nombre, String descripcion, Set<GrupoMuscular> gruposMusculares) {
        this.idEjercicio = idEjercicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.gruposMusculares = gruposMusculares;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<GrupoMuscular> getGruposMusculares() {
        return gruposMusculares;
    }

    public void setGruposMusculares(Set<GrupoMuscular> gruposMusculares) {
        this.gruposMusculares = gruposMusculares;
    }

    
}
