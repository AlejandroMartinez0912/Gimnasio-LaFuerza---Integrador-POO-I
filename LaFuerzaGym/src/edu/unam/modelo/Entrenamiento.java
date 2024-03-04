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
@Table(name = "entrenamiento")
public class Entrenamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrenamiento", nullable = false)
    private int idEntrenamiento;

    @OneToOne
    private Ejercicio ejercicio;

    @Column(name = "series", nullable = false)
    @Basic
    private int series;
    
    @Column(name = "repeticiones", nullable = false)
    @Basic
    private int repeticiones;
    
    @Column(name = "descanso", nullable = false)
    @Basic
    private int descanso;
    
    @Basic
    @Column(name = "duracion_minutos", nullable = false)
    private int duracionMinutos;

    public Entrenamiento() {
    }

    public Entrenamiento(int idEntrenamiento, Ejercicio ejercicio, int series, int repeticiones, int descanso, int duracionMinutos) {
        this.idEntrenamiento = idEntrenamiento;
        this.ejercicio = ejercicio;
        this.series = series;
        this.repeticiones = repeticiones;
        this.descanso = descanso;
        this.duracionMinutos = duracionMinutos;
    }

    public int getIdEntrenamiento() {
        return idEntrenamiento;
    }

    public void setIdEntrenamiento(int idEntrenamiento) {
        this.idEntrenamiento = idEntrenamiento;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getDescanso() {
        return descanso;
    }

    public void setDescanso(int descanso) {
        this.descanso = descanso;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    
}