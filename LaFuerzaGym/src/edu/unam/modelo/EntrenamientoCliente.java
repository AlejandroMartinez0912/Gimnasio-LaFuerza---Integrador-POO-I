package edu.unam.modelo;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
@Entity
@Table(name = "entrenamiento_cliente")
public class EntrenamientoCliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue
    @Column(name = "id_entrenamiento_cliente")
    private int idEntrenamientoCliente;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
        name = "entrenamiento_x_entrenamiento_clientes", 
        joinColumns = @JoinColumn(name = "id_entrenamiento_cliente"), 
        inverseJoinColumns = @JoinColumn(name = "id_entrenamiento"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_entrenamiento_cliente", "id_entrenamiento"}
        )
    )
    private Set<Entrenamiento> entrenamientos;

    @Basic
    @Column(name = "duracion_semanas")
    private int duracionSemanas; //¿Es necesario o dejamos en 4 semanas siempre?

    @Basic
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Basic
    @Column(name = "fecha_fin")
    private Date fechaFin;

    @ManyToOne
    @JoinColumn(name = "id_tutor")
    private Tutor tutor;
    
    @Basic
    @Column(name = "activo")
    private boolean activo; //Este debería actualizarse automáticamente una vez superada la fecha fin

    @Basic
    @Column(name = "volumen_semanal")
    private int volumenSemanal; //¿Borramos de acá y creamos un nuevo modelo DetallesCliente?

    public EntrenamientoCliente() {
    }

    public EntrenamientoCliente(int idEntrenamientoCliente, Cliente cliente, Set<Entrenamiento> entrenamientos,
            int duracionSemanas, Date fechaInicio, Date fechaFin, Tutor tutor, boolean activo, int volumenSemanal) {
        this.idEntrenamientoCliente = idEntrenamientoCliente;
        this.cliente = cliente;
        this.entrenamientos = entrenamientos;
        this.duracionSemanas = duracionSemanas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    
    
}