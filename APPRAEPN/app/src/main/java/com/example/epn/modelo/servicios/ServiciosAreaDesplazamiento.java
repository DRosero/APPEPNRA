package com.example.epn.modelo.servicios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Camera;
import android.util.Log;

import com.example.epn.basehelper.BaseHelper;
import com.example.epn.basehelper.TablasBddInterface;
import com.example.epn.modelo.entidades.AreaDesplazamiento;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Gabriel on 28/04/2015.
 */
public class ServiciosAreaDesplazamiento implements ServiciosGenerales, TablasBddInterface {

    final String TAG = "Tesis";
    private BaseHelper baseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private String columnas[] = {"idArea", "radio"};

    public ServiciosAreaDesplazamiento(Context context) {
        baseHelper = new BaseHelper(context);
    }

    @Override
    public void abrirConexion() {
        sqLiteDatabase = baseHelper.getWritableDatabase();
    }

    @Override
    public void cerrarConexion() {
        baseHelper.close();
    }

    @Override
    public void insertar(Object object) {
        AreaDesplazamiento areaDesplazamiento = (AreaDesplazamiento) object;

        ContentValues valores = new ContentValues();
        valores.put("radio", areaDesplazamiento.getRadio());
        sqLiteDatabase.insert("AREADESPLAZAMIENTO", null, valores);
    }

    @Override
    public List<AreaDesplazamiento> recuperarTodos() {

        List<AreaDesplazamiento> areaDesplazamientos = new ArrayList<AreaDesplazamiento>();
        Cursor cursor = sqLiteDatabase.query(tablaAreaDesplazamiento, columnas, null, null, null, null, null);
        cursor.moveToNext();

        int contador =0;

        while (!cursor.isAfterLast()) {
            AreaDesplazamiento areaDesplazamiento = new AreaDesplazamiento();

            areaDesplazamiento.setIdArea(cursor.getInt(0));
            areaDesplazamiento.setRadio(cursor.getInt(1));

            areaDesplazamientos.add(areaDesplazamiento);
            cursor.moveToNext();
            contador++;
        }
        return areaDesplazamientos;
    }

    @Override
    public void actualizar(Object object) {
        AreaDesplazamiento areaDesplazamiento = (AreaDesplazamiento) object;

        abrirConexion();

        ContentValues values = new ContentValues();
        values.put("radio", areaDesplazamiento.getRadio());
        sqLiteDatabase.update(tablaAreaDesplazamiento, values, "idArea= " + areaDesplazamiento.getIdArea(), null);

        cerrarConexion();
    }

    @Override
    public void eliminar(Object object) {
        AreaDesplazamiento areaDesplazamiento = (AreaDesplazamiento) object;
        sqLiteDatabase.delete(tablaAreaDesplazamiento, "idArea=" + areaDesplazamiento.getIdArea(), null);
    }

    @Override
    public Cursor recuperarElemento(int rowid) throws SQLException {

        Cursor cursor = sqLiteDatabase.query(tablaAreaDesplazamiento, columnas, "idArea= " + rowid, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Log.d(TAG, "Saliendo de recuperarArea");
        }
        return cursor;
    }

    public int conteoRegistros() throws SQLException {
        int verificador=0;
        this.abrirConexion();
        Cursor cursor = sqLiteDatabase.query(tablaAreaDesplazamiento, columnas, null, null, null, null, null);
        cursor.moveToNext();

        while (!cursor.isAfterLast()) {

            cursor.moveToNext();
            verificador++;
        }

        this.cerrarConexion();
        return verificador;
    }

}
