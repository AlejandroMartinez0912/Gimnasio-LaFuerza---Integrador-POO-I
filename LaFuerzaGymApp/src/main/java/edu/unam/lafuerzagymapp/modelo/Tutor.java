package edu.unam.lafuerzagymapp.modelo;

public class Tutor {
    private int idTutor;
    private String nombre;
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
