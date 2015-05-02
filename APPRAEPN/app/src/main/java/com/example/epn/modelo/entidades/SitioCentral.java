package com.example.epn.modelo.entidades;

/**
 * Created by Diego on 29/04/2015.
 */
public class SitioCentral {

    private int idSitioCentral;
    private String nombresitio;
    private String descripcionSitio;
    private int latitud;
    private int longitud;
    private byte[] imagen;

    public SitioCentral() {

    }

    public int getIdSitioCentral() {
        return idSitioCentral;
    }

    public void setIdSitioCentral(int idSitioCentral) {
        this.idSitioCentral = idSitioCentral;
    }

    public String getNombresitio() {
        return nombresitio;
    }

    public void setNombresitio(String nombresitio) {
        this.nombresitio = nombresitio;
    }

    public String getDescripcionSitio() {
        return descripcionSitio;
    }

    public void setDescripcionSitio(String descripcionSitio) {
        this.descripcionSitio = descripcionSitio;
    }

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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
