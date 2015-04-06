package com.example.epn.controlador;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.epn.basehelper.BaseHelper;
import com.example.epn.modelo.entidades.Paciente;
import com.example.epn.modelo.servicios.ServiciosPaciente;

/**
 * Created by Diego on 02/04/2015.
 */
public class ControladorPaciente extends Activity{
    ServiciosPaciente serviciosPaciente;

    public ControladorPaciente() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviciosPaciente=new ServiciosPaciente(this);
    }

    public void irGuardar(String nombre,String apellido,String direccion){

        Paciente paciente=new Paciente();

        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setDireccion(direccion);

        try {
            serviciosPaciente.abrirBD();
            serviciosPaciente.insertar(paciente);
            Toast.makeText(getApplicationContext(), "Paciente Ingresado", Toast.LENGTH_SHORT).show();
            serviciosPaciente.cerrarBD();
        }
        catch (Exception e){
            serviciosPaciente.cerrarBD();
        }
    }

}
