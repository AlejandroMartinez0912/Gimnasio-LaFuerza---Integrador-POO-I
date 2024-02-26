package edu.unam.lafuerzagymapp.modelo;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tutor {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idTutor;
    
    @Basic
    private String nombre;
    @Basic
    private String apellido;

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
