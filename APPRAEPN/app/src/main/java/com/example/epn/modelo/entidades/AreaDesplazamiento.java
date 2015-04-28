package com.example.epn.modelo.entidades;

import java.io.Serializable;

/**
 * Created by Gabriel on 28/04/2015.
 */
public class AreaDesplazamiento implements Serializable {

    private int idArea;
    private int radio;

    public AreaDesplazamiento(){

    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    @Override
    public String toString() {
        return "AreaDesplazamiento{" +
                "idArea=" + idArea +
                ", radio=" + radio +
                ", Área Permitida=" + radio*radio*3.1416 +
                '}';
    }
}
