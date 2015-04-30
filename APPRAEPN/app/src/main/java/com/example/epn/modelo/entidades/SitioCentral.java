package com.example.epn.modelo.entidades;

/**
 * Created by Diego on 29/04/2015.
 */
public class SitioCentral {

    private int idSitioCentral;
    private int latitud;
    private int longitud;
    private byte[] imagen;

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public SitioCentral() {

    }

    public byte[] getImagen() {
        return imagen;
    }

    public int getIdSitioCentral() {
        return idSitioCentral;
    }

    public void setIdSitioCentral(int idSitioCentral) {
        this.idSitioCentral = idSitioCentral;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }


}
