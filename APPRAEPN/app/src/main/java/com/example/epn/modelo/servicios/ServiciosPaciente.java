package com.example.epn.modelo.servicios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.epn.basehelper.BaseHelper;
import com.example.epn.modelo.entidades.Paciente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego on 01/04/2015.
 */
public class ServiciosPaciente<SQLiteDataBase> {

    final String TAG="Tesis";
    private BaseHelper baseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private String columnas[]={"idPaciente","nombre","apellido","direccion"};

    public ServiciosPaciente(Context context) {
        baseHelper=new BaseHelper(context);
    }

    public void abrirBD(){
        sqLiteDatabase=baseHelper.getWritableDatabase();
    }

    public void cerrarBD(){
        baseHelper.close();
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
       /* ContentValues values=new ContentValues();
        values.put("idPaciente",paciente.getIdpaciente());*/
        sqLiteDatabase.delete("PACIENTE","idPaciente="+paciente.getIdpaciente(),null);
    }

    public void actualizar(Paciente paciente) {
        abrirBD();
        ContentValues values = new ContentValues();
        values.put("nombre",paciente.getNombre());
        values.put("apellido",paciente.getApellido());
        values.put("direccion",paciente.getDireccion());

        sqLiteDatabase.update("PACIENTE",values,"idPaciente="+paciente.getIdpaciente(),null);
        cerrarBD();
    }

    public Cursor recuperarPaciente(int rowid) throws SQLException {
        Cursor cursor = sqLiteDatabase.query("PACIENTE", columnas,
                "idPaciente=" + rowid, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Log.d(TAG, "Saliendo de recuperarPaciente");
        }
        return cursor;
    }
}
