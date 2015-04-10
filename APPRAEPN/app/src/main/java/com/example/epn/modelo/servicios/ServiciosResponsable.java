package com.example.epn.modelo.servicios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.epn.basehelper.BaseHelper;
import com.example.epn.modelo.entidades.Responsable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 07/04/2015.
 * Project: APPRAREPN
 */
public class ServiciosResponsable {

    private BaseHelper baseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private String columnas[]={"idResponsable","nombreResponsable","telefonoMovil","telefonoFijo","direccionHogar","direccionTrabajo","prioridadResponsable"};

    public ServiciosResponsable(Context contexto){
        baseHelper=new BaseHelper(contexto);
    }

    public void abrirConexion(){
        sqLiteDatabase= baseHelper.getWritableDatabase();
    }

    public void cerrarConexion(){
        baseHelper.close();
    }

    public void insertar(Responsable responsable){
        ContentValues valores = new ContentValues();
        valores.put("nombreResponsable",responsable.getNombre().toString());
        valores.put("telefonoMovil", responsable.getTelefonoMovil().toString());
        valores.put("telefonoFijo", responsable.getTelefonoFijo().toString());
        valores.put("direccionHogar",responsable.getDireccionHogar().toString());
        valores.put("direccionTrabajo",responsable.getDireccionTrabajo().toString());
        valores.put("prioridadResponsable", responsable.getPrioridadResponsable());

        sqLiteDatabase.insert("RESPONSABLE",null, valores);
    }

    public List<Responsable> listarResponsbale() {
        List<Responsable> responsables = new ArrayList<Responsable>();
        Cursor cursor = sqLiteDatabase.query("RESPONSABLE", columnas, null, null, null, null, null);
        cursor.moveToNext();

        while (!cursor.isAfterLast()) {
            Responsable r = new Responsable();
            r.setIdresponsable(cursor.getInt(0));
            r.setNombre(cursor.getString(1));
            r.setTelefonoMovil(cursor.getString(2));
            r.setTelefonoFijo(cursor.getString(3));
            r.setDireccionHogar(cursor.getString(4));
            r.setDireccionTrabajo(cursor.getString(5));
            r.setPrioridadResponsable(cursor.getInt(6));

            responsables.add(r);
            cursor.moveToNext();
        }
        return responsables;
    }

    public void actualizar(Responsable responsable){
        ContentValues valores = new ContentValues();

        valores.put("nombreResponsable",responsable.getNombre().toString());
        valores.put("telefonoMovil", responsable.getTelefonoMovil().toString());
        valores.put("telefonoFijo", responsable.getTelefonoFijo().toString());
        valores.put("direccionHogar",responsable.getDireccionHogar().toString());
        valores.put("direccionTrabajo",responsable.getDireccionTrabajo().toString());
        valores.put("prioridadResponsable", responsable.getPrioridadResponsable());

        sqLiteDatabase.update("RESPONSABLE", valores, "idResponsable=" + responsable.getIdresponsable(), null);
        System.out.println("Responsable Actualizado en ServicioResponsable, OK");
    }

    public void eliminar(Responsable responsable){
        sqLiteDatabase.delete("RESPONSABLE", "idResponsable=" + responsable.getIdresponsable(), null);
    }

}
