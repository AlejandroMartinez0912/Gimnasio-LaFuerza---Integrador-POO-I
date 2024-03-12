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
    private int idDetalleRutina;
     
    @ManyToOne
    @JoinColumn(name = "id_rutina")
    private Rutina rutina; //Desde esta relacion obengo el nombre del ejercicio

    /* 
        @Column(name = "entrenamiento_realizado", nullable = false, length = 100)
        @Basic
        private String entrenamientoRealizado;
    */
    @Column(name = "series", nullable = false)
    @Basic
    private int series;

    @ManyToOne
    @JoinColumn(name = "id_Cliente")
    private Cliente cliente; 

    @Column(name = "repeticiones", nullable = false)
    @Basic
    private int repeticiones;

    @Column(name = "peso", nullable = false)
    @Basic
    private double peso;

    @Column(name = "volumen_entrenamiento", nullable = false)
    @Basic
    private int volumenEntrenamiento;

    public DetalleRutina() {
    }

    public DetalleRutina(int idDetalleRutina, int series, int repeticiones, double peso, int volumenEntrenamiento) {
        this.idDetalleRutina = idDetalleRutina;
       // this.entrenamientoRealizado = entrenamientoRealizado;
        this.series = series;
        this.repeticiones = repeticiones;
        this.peso = peso;
        this.volumenEntrenamiento = volumenEntrenamiento;
    }

    public int getIdDetalleEntrenamientoCliente() {
        return idDetalleRutina;
    }

    public void setIdDetalleEntrenamientoCliente(int idDetalleEntrenamientoCliente) {
        this.idDetalleRutina = idDetalleEntrenamientoCliente;
    }
    /* 
        public String getEntrenamientoRealizado() {
            return entrenamientoRealizado;
        }

        public void setEntrenamientoRealizado(String entrenamientoRealizado) {
            this.entrenamientoRealizado = entrenamientoRealizado;
        }
    */
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

    public int getVolumenEntrenamiento() {
        return volumenEntrenamiento;
    }

    public void setVolumenEntrenamiento(int volumenEntrenamiento) {
        this.volumenEntrenamiento = volumenEntrenamiento;
    }

}
