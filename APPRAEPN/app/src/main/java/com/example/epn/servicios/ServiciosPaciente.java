package com.example.epn.servicios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.epn.basehelper.BaseHelper;
import com.example.epn.entidades.Paciente;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * Created by Diego on 01/04/2015.
 */
public class ServiciosPaciente<SQLiteDataBase> {

    final String TAG="Tesis";
    private BaseHelper baseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private String columnas[]={"idpaciente","nombre","apellido","direccion"};

    public void abrir(){
        sqLiteDatabase= baseHelper.getWritableDatabase();
    }

    public void cerrar(){
        baseHelper.close();
    }

    public ServiciosPaciente(Context context){
        baseHelper=new BaseHelper(context);
    }

    public void insertar(Paciente paciente){
        ContentValues values=new ContentValues();

        values.put("nombre",paciente.getNombre());
        values.put("apellido",paciente.getApellido());
        values.put("direccion",paciente.getDireccion());
        sqLiteDatabase.insert("PACIENTE",null,values);
    }

    public List<Paciente> recuperarTodos(){
        List<Paciente>pacientes=new ArrayList<Paciente>();
        Cursor cursor= sqLiteDatabase.query("PACIENTE",columnas,null,null,null,null,null);
        cursor.moveToNext();
        while (!cursor.isAfterLast()){
            Paciente paciente=new Paciente();

            paciente.setIdpaciente(cursor.getInt(0));
            paciente.setNombre(cursor.getString(1));
            paciente.setApellido(cursor.getString(2));
            paciente.setDireccion(cursor.getString(3));

            pacientes.add(paciente);
            cursor.moveToNext();
        }
        return pacientes;
    }

    public void eliminar(Paciente paciente){
        ContentValues values=new ContentValues();
        values.put("idPaciente",paciente.getIdpaciente());
        sqLiteDatabase.delete("PACIENTE","idPaciente="+paciente.getIdpaciente(),null);
    }


}
