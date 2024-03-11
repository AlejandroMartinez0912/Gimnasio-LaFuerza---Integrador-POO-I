package edu.unam.modelo;

import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rutina")
public class Rutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rutina", nullable = false)
    private int idRutina;

    @Column(name = "series", nullable = false)
    @Basic
    private int series;
    
    @Column(name = "repeticiones", nullable = false)
    @Basic
    private int repeticiones;
    
    @Column(name = "descanso", nullable = false)
    @Basic
    private int descanso;

    //Establecemos la relación con Ejercicio
    @ManyToOne
    @JoinColumn(name = "id_ejercicio")
    private Ejercicio ejercicio;

    //Establecemos la relación con EntrenamientoCliente
    @ManyToMany (mappedBy = "rutinas")
    private Set<EntrenamientoCliente> entrenamientoCliente;
    

    public Rutina() {
    }


    public Rutina(int idRutina, Ejercicio ejercicio, int series, int repeticiones, int descanso,
            Set<EntrenamientoCliente> entrenamientoCliente) {
        this.idRutina = idRutina;
        this.ejercicio = ejercicio;
        this.series = series;
        this.repeticiones = repeticiones;
        this.descanso = descanso;
        this.entrenamientoCliente = entrenamientoCliente;
    }


    public int getIdRutina() {
        return idRutina;
    }


    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
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


    public Set<EntrenamientoCliente> getEntrenamientoCliente() {
        return entrenamientoCliente;
    }


    public void setEntrenamientoCliente(Set<EntrenamientoCliente> entrenamientoCliente) {
        this.entrenamientoCliente = entrenamientoCliente;
    }

    

    
}