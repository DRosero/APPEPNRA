package com.example.epn.modelo.servicios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.epn.basehelper.BaseHelper;

import java.util.List;

/**
 * Created by Gabriel on 28/04/2015.
 */
public interface ServiciosGenerales {

    //public void constructor(Context context);

    public void abrirConexion();

    public void cerrarConexion();

    public void insertar();

    public List recuperarTodos();

    public void actualizar();

    public void eliminar();

    public void recuperarElemento();

}
