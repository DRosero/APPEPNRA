package com.example.epn.modelo.servicios;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.epn.basehelper.BaseHelper;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * Created by Gabriel on 28/04/2015.
 */
public interface ServiciosGenerales {

    //public void instanciarElemento();

    public void abrirConexion();

    public void cerrarConexion();

    public void insertar(Object object);

    public List recuperarTodos();

    public void actualizar(Object object);

    public void eliminar(Object object);

    public Cursor recuperarElemento(int rowid) throws SQLException;

}
