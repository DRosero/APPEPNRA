package com.example.epn.modelo.servicios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.epn.basehelper.BaseHelper;
import com.example.epn.modelo.entidades.AreaDesplazamiento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 28/04/2015.
 */
public class ServiciosAreaDesplazamiento implements ServiciosGenerales{


    private BaseHelper baseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private String columnas[]={"idArea","radio"};

    public ServiciosAreaDesplazamiento(Context context) {
        baseHelper=new BaseHelper(context);
    }

    @Override
    public void abrirConexion() {
        sqLiteDatabase=baseHelper.getWritableDatabase();
    }

    @Override
    public void cerrarConexion() {
        baseHelper.close();
    }

    @Override
    public void insertar() {

    }

    @Override
    public List<AreaDesplazamiento> recuperarTodos() {
        List<AreaDesplazamiento> areaDesplazamientos=new ArrayList<AreaDesplazamiento>();
        return areaDesplazamientos;
    }

    @Override
    public void actualizar() {

    }

    @Override
    public void eliminar() {

    }

    @Override
    public void recuperarElemento() {

    }
}
