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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrenamiento_cliente")
    private int idEntrenamientoCliente;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
        name = "rutina_x_entrenamiento_clientes", 
        joinColumns = @JoinColumn(name = "id_entrenamiento_cliente"), 
        inverseJoinColumns = @JoinColumn(name = "id_rutina"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_entrenamiento_cliente", "id_rutina"}
        )
    )
    private Set<Rutina> rutinas;

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
    @Column(name = "evaluacion_tutor")
    private boolean evaluacionTutor; 

    @Basic
    @Column(name = "volumen_semanal")
    private int volumenSemanal;

    public EntrenamientoCliente() {
    }

    public EntrenamientoCliente(int idEntrenamientoCliente, Cliente cliente, Set<Rutina> rutinas,
            int duracionSemanas, Date fechaInicio, Date fechaFin, Tutor tutor, boolean evaluacionTutor, int volumenSemanal) {
        this.idEntrenamientoCliente = idEntrenamientoCliente;
        this.cliente = cliente;
        this.rutinas = rutinas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tutor = tutor;
        this.evaluacionTutor = evaluacionTutor;
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

    public Set<Rutina> getrutinas() {
        return rutinas;
    }

    public void setrutinas(Set<Rutina> rutinas) {
        this.rutinas = rutinas;
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

    public boolean isevaluacionTutor() {
        return evaluacionTutor;
    }

    public void setevaluacionTutor(boolean evaluacionTutor) {
        this.evaluacionTutor = evaluacionTutor;
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