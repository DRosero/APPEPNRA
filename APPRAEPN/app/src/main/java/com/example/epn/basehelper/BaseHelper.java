package com.example.epn.basehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Diego on 01/04/2015.
 */
public class BaseHelper extends SQLiteOpenHelper {

    String sentencia="CREATE TABLE PACIENTE(idPaciente INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT NOT NULL," +
            "apellido TEXT NOT NULL," +
            "direccion TEXT NOT NULL)";

    public BaseHelper(Context context){
        super(context,"bdtesis",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sentencia);
        //db.execSQL("DROP DATABASE bdtesis");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DBAdapter", "Upgrading database from version " + oldVersion
                + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS PACIENTE");
        onCreate(db);
    }
}
