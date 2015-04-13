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
    public int prioridadResponsable;

    public Responsable(){

    }

    public int getPrioridadResponsable() {
        return prioridadResponsable;
    }

    public void setPrioridadResponsable(int prioridadResponsable) {
        this.prioridadResponsable = prioridadResponsable;
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
        String prioridad="";
        if(this.getPrioridadResponsable()==1){
            prioridad="llamada";
        }
        else {
            prioridad="mensaje";
        }

        return "Responsable{" +
                "idresponsable=" + idresponsable +
                ", nombre='" + nombre + '\'' +
                ", telefonoMovil='" + telefonoMovil + '\'' +
                ", telefonoFijo='" + telefonoFijo + '\'' +
                ", direccionHogar='" + direccionHogar + '\'' +
                ", direccionTrabajo='" + direccionTrabajo + '\'' +



                ", prioridadResponsable=" + prioridad +
                '}';
    }
}
