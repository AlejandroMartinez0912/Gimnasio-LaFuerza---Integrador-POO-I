package edu.unam.lafuerzagymapp.modelo;


import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Entrenamiento {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idEntrenamiento;
    
    private Ejercicio ejercicio;
    @Basic
    private int series;
    @Basic
    private int repeticiones;
    @Basic
    private int descanso;
    @Basic
    private int duracionMinutos;
    
    @ManyToMany(mappedBy = "entrenamientos")
    private Set<EntrenamientoCliente> entrenamientosClientes;

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