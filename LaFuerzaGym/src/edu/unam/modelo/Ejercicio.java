package edu.unam.modelo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ejercicio")
public class Ejercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejercicio", nullable = false)
    private int idEjercicio;

    @Column(name = "nombre_ejercicio", nullable = false, length = 100)
    @Basic
    private String nombre;

    @OneToOne
    private GrupoMuscular grupoMuscular;

    public Ejercicio() {
    }

    public Ejercicio(int idEjercicio, String nombre, GrupoMuscular grupoMuscular) {
        this.idEjercicio = idEjercicio;
        this.nombre = nombre;
        this.grupoMuscular = grupoMuscular;
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
    
    public GrupoMuscular getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(GrupoMuscular grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }
}
