package com.example.epn.basehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Diego on 01/04/2015.
 */
public class BaseHelper extends SQLiteOpenHelper {

    //sentencia para tabla Paciente
    String sentPaciente="CREATE TABLE PACIENTE(idPaciente INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT NOT NULL," +
            "apellido TEXT NOT NULL," +
            "direccion TEXT NOT NULL)";

    //sentencia para tabla Responsable
    String sentResponsable="CREATE TABLE RESPONSABLE(idResponsable INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombreResponsable TEXT NOT NULL," +
            "telefonoMovil TEXT NOT NULL"+
            "telefonoFijo TEXT NOT NULL"+
            "direccionHogar TEXT NOT NULL"+
            "direccionTrabajo TEXT NOT NULL"+
            "prioridadResponsable INTEGER NOT NULL)";


    public BaseHelper(Context contexto){
        super(contexto,"bdtesis",null,2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sentPaciente);
        db.execSQL(sentResponsable);
        //db.execSQL("DROP DATABASE bdtesis");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DBAdapter", "Upgrading database from version " + oldVersion
                + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS PACIENTE");
        db.execSQL("DROP TABLE IF EXISTS RESPONSABLE");
        onCreate(db);
    }

}
