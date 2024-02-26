package edu.unam.lafuerzagymapp.modelo;

public class DetalleEntrenamientoCliente {
    private int idDetalleEntrenamientoCliente;
    private String entrenamientoRealizado;
    private int series;
    private int repeticiones;
    private double peso;
    private int volumenEntrenamiento;

    public DetalleEntrenamientoCliente() {
    }

    public DetalleEntrenamientoCliente(int idDetalleEntrenamientoCliente, String entrenamientoRealizado, int series, int repeticiones, double peso, int volumenEntrenamiento) {
        this.idDetalleEntrenamientoCliente = idDetalleEntrenamientoCliente;
        this.entrenamientoRealizado = entrenamientoRealizado;
        this.series = series;
        this.repeticiones = repeticiones;
        this.peso = peso;
        this.volumenEntrenamiento = volumenEntrenamiento;
    }

    public int getIdDetalleEntrenamientoCliente() {
        return idDetalleEntrenamientoCliente;
    }

    public void setIdDetalleEntrenamientoCliente(int idDetalleEntrenamientoCliente) {
        this.idDetalleEntrenamientoCliente = idDetalleEntrenamientoCliente;
    }

    public String getEntrenamientoRealizado() {
        return entrenamientoRealizado;
    }

    public void setEntrenamientoRealizado(String entrenamientoRealizado) {
        this.entrenamientoRealizado = entrenamientoRealizado;
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

    public int getVolumenEntrenamiento() {
        return volumenEntrenamiento;
    }

    public void setVolumenEntrenamiento(int volumenEntrenamiento) {
        this.volumenEntrenamiento = volumenEntrenamiento;
    }
}
