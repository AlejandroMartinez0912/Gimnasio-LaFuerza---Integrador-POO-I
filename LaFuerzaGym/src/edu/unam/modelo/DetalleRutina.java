package edu.unam.modelo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_rutina")
public class DetalleRutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_rutina")
    private int idDetalleRutina;
     
    @ManyToOne
    @JoinColumn(name = "id_entrenamiento_cliente")
    private EntrenamientoCliente entrenamientoCliente; //Desde esta relacion obengo el nombre del ejercicio y el cliente

    @Column(name = "series", nullable = false)
    @Basic
    private int series;

    @Column(name = "repeticiones", nullable = false)
    @Basic
    private int repeticiones;

    @Column(name = "peso", nullable = false)
    @Basic
    private double peso;

    @Column(name = "volumen_rutina", nullable = false)
    @Basic
    private double volumenRutina;

    public DetalleRutina() {
    }

    public DetalleRutina(int idDetalleRutina, EntrenamientoCliente entrenamientoCliente, int series, int repeticiones,
            double peso, double volumenRutina) {
        this.idDetalleRutina = idDetalleRutina;
        this.entrenamientoCliente = entrenamientoCliente;
        this.series = series;
        this.repeticiones = repeticiones;
        this.peso = peso;
        this.volumenRutina = volumenRutina;
    }

    public int getIdDetalleRutina() {
        return idDetalleRutina;
    }

    public void setIdDetalleRutina(int idDetalleRutina) {
        this.idDetalleRutina = idDetalleRutina;
    }

    public EntrenamientoCliente getEntrenamientoCliente() {
        return entrenamientoCliente;
    }

    public void setEntrenamientoCliente(EntrenamientoCliente entrenamientoCliente) {
        this.entrenamientoCliente = entrenamientoCliente;
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

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getVolumenRutina() {
        return volumenRutina;
    }

    public void setVolumenRutina(double volumenRutina) {
        this.volumenRutina = volumenRutina;
    }

    
}
