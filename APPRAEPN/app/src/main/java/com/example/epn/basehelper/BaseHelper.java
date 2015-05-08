package com.example.epn.basehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Diego on 01/04/2015.
 */
public class BaseHelper extends SQLiteOpenHelper implements TablasBddInterface{


    public static final int DATABASE_VERSION = 1;//version 2015-04-15

    public static final String DATABASE_NAME = "bdtesis.db";

    //sentencia para tabla Paciente
    String sentCrearPaciente="CREATE TABLE "+tablaPaciente+
            "(idPaciente INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT NOT NULL," +
            "apellido TEXT NOT NULL," +
            "direccion TEXT NOT NULL)";

    //sentencia para tabla Responsable
    String sentCrearResponsable="CREATE TABLE "+tablaResponsable+
            "(idResponsable INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombreResponsable TEXT NOT NULL," +
            "telefonoMovil TEXT NOT NULL,"+
            "telefonoFijo TEXT NOT NULL,"+
            "direccionHogar TEXT NOT NULL,"+
            "direccionTrabajo TEXT NOT NULL,"+
            "prioridadResponsable INTEGER NOT NULL)";

    //sentencia para tabla Sitio
    String sentCrearSitioConocido="";

    //sentencia para crear Area de Desplazamiento
    String sentCrearArea="CREATE TABLE "+tablaAreaDesplazamiento+
            "(idArea INTEGER PRIMARY KEY AUTOINCREMENT," +
            "radio INTEGER NOT NULL)";

    //sentencia para crear sitio central
    String sentCrearSitioCentral="CREATE TABLE "+tablaSitioCentral+
            "(idSitioCentral INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombreSitio TEXT NOT NULL,"+
            "descripcionSitio TEXT NOT NULL,"+
            "latitud INTEGER NOT NULL,"+
            "longitud INTEGER NOT NULL,"+
            "foto BLOB)";

    public BaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sentCrearPaciente);
        db.execSQL(sentCrearResponsable);
        db.execSQL(sentCrearArea);
        db.execSQL(sentCrearSitioCentral);
        //db.execSQL("DROP DATABASE bdtesis");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DBAdapter", "Upgrading database from version " + oldVersion
                + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS "+tablaPaciente);
        db.execSQL("DROP TABLE IF EXISTS "+tablaResponsable);
        db.execSQL("DROP TABLE IF EXISTS "+tablaAreaDesplazamiento);
        db.execSQL("DROP TABLE IF EXISTS "+tablaSitioCentral);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
