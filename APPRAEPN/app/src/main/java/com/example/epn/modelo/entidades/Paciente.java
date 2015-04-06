package com.example.epn.modelo.entidades;

import java.io.Serializable;

/**
 * Created by Diego on 01/04/2015.
 */
public class Paciente implements Serializable {

    private int idpaciente;
    private String nombre;
    private String apellido;
    private String direccion;

    public Paciente() {

    }

    public int getIdpaciente() {
        return idpaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setIdpaciente(int idpaciente) {
        this.idpaciente = idpaciente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idpaciente=" + idpaciente +
                ", nombre='" + nombre +
                ", apellido='" + apellido +
                ", direccion='" + direccion +'}';
    }
}
