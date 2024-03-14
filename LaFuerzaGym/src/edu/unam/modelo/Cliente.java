package edu.unam.modelo;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private int idCliente;
    @Column(name = "nombre", nullable = false, length = 100)
    @Basic
    private String nombre;
    @Column(name = "apellido", nullable = false, length = 100)
    @Basic
    private String apellido;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;
    @Column(name = "sexo", nullable = false, length = 20)
    @Basic
    private String sexo;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ingreso", nullable = false)
    private Date fechaIngreso;
    
    
    public Cliente() {
    }

    /*public Cliente(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    } */
    
    public Cliente(int idCliente, String nombre, String apellido, Date fechaNacimiento, String sexo, Date fechaIngreso) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.fechaIngreso = fechaIngreso;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
