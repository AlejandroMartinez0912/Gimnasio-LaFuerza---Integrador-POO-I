package edu.unam.modelo;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tutor")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tutor", nullable = false)
    private int idTutor;

    @Column(name = "nombre_tutor", nullable = false, length = 80 )
    private String nombre;

    @Column(name = "apellido_tutor", nullable = false, length = 80 )
    private String apellido;

    @OneToMany (mappedBy = "tutor"  )
    private Set<EntrenamientoCliente> entrenamientoClientes = new HashSet<EntrenamientoCliente>();
    
    public Tutor() {
    }

    public Tutor(int idTutor, String nombre, String apellido) {
        this.idTutor = idTutor;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}

