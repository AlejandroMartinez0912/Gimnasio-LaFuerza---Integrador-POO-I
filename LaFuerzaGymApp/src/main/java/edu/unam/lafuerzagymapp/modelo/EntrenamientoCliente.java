package edu.unam.lafuerzagymapp.modelo;

import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class EntrenamientoCliente {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idEntrenamientoCliente;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToMany
    @JoinTable(
        name = "entrenamiento_entrenamientoCliente",
        joinColumns = @JoinColumn(name = "idEntrenamientoCliente"),
        inverseJoinColumns = @JoinColumn(name = "idEntrenamiento")
    )
    private Set<Entrenamiento> entrenamientos;
    @Basic
    private int duracionSemanas;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    private Tutor tutor;
    @Basic
    private boolean activo; 
    @Basic
    private int volumenSemanal;
    
    public EntrenamientoCliente() {
    }

    public EntrenamientoCliente(int idEntrenamientoCliente, Cliente cliente, Set<Entrenamiento> entrenamientos, int duracionSemanas, Date fechaInicio, Tutor tutor, boolean activo, int volumenSemanal) {
        this.idEntrenamientoCliente = idEntrenamientoCliente;
        this.cliente = cliente;
        this.entrenamientos = entrenamientos;
        this.duracionSemanas = duracionSemanas;
        this.fechaInicio = fechaInicio;
        this.tutor = tutor;
        this.activo = activo;
        this.volumenSemanal = volumenSemanal;
    }

    public int getIdEntrenamientoCliente() {
        return idEntrenamientoCliente;
    }

    public void setIdEntrenamientoCliente(int idEntrenamientoCliente) {
        this.idEntrenamientoCliente = idEntrenamientoCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Entrenamiento> getEntrenamientos() {
        return entrenamientos;
    }

    public void setEntrenamientos(Set<Entrenamiento> entrenamientos) {
        this.entrenamientos = entrenamientos;
    }

    public int getDuracionSemanas() {
        return duracionSemanas;
    }

    public void setDuracionSemanas(int duracionSemanas) {
        this.duracionSemanas = duracionSemanas;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getVolumenSemanal() {
        return volumenSemanal;
    }

    public void setVolumenSemanal(int volumenSemanal) {
        this.volumenSemanal = volumenSemanal;
    }

    
    
}