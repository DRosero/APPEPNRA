package com.example.epn.modelo.entidades;

/**
 * Created by Gabriel on 07/04/2015.
 */
public class Responsable {

    public int idresponsable;
    public String nombre;
    public String telefonoMovil;
    public String telefonoFijo;
    public String direccionHogar;
    public String direccionTrabajo;

    public Responsable(){

    }

    public int getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(int idresponsable) {
        this.idresponsable = idresponsable;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getDireccionHogar() {
        return direccionHogar;
    }

    public void setDireccionHogar(String direccionHogar) {
        this.direccionHogar = direccionHogar;
    }

    public String getDireccionTrabajo() {
        return direccionTrabajo;
    }

    public void setDireccionTrabajo(String direccionTrabajo) {
        this.direccionTrabajo = direccionTrabajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Responsable{" +
                "idresponsable=" + idresponsable +
                ", nombre='" + nombre +
                ", móvil='" + telefonoMovil +
                ", fijo='" + telefonoFijo +
                ", direccionHogar='" + direccionHogar +
                ", direccionTrabajo='"+direccionTrabajo+'}';
    }
}
