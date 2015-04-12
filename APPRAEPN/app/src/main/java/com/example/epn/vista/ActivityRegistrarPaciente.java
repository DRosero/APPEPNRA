package com.example.epn.vista;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.epn.appraepn.R;
import com.example.epn.basehelper.BaseHelper;
import com.example.epn.controlador.ControladorPaciente;
import com.example.epn.modelo.entidades.Paciente;
import com.example.epn.modelo.servicios.ServiciosPaciente;

import java.sql.SQLException;

/**
 * Created by Diego on 01/04/2015.
 */
public class ActivityRegistrarPaciente extends Activity {

    ControladorPaciente controladorPaciente;
    ServiciosPaciente serviciosPaciente;

    EditText txtnombre;
    EditText txtapellido;
    EditText txtdireccion;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registrar_paciente);
        setTitle("Nuevo Registro de Paciente");

        txtnombre = (EditText) findViewById(R.id.txtNombrePaciente);
        txtapellido = (EditText) findViewById(R.id.txtApellidoPaciente);
        txtdireccion = (EditText) findViewById(R.id.txtDireccionPaciente);

        controladorPaciente = new ControladorPaciente();
        serviciosPaciente = new ServiciosPaciente(this);
    }

    public void irGuardarP(View view) {

        Paciente paciente = new Paciente();
        int verificador = validarDatosInterfaz();

        if (verificador == 1) {

            try {

                tomarDatosInterfazPaciente(paciente);

                serviciosPaciente.abrirBD();
                serviciosPaciente.insertar(paciente);
                Toast.makeText(getApplicationContext(), "Datos de Paciente ingresados correctamente", Toast.LENGTH_SHORT).show();
                serviciosPaciente.cerrarBD();

                Intent intent = new Intent(this, ActivityAdministrarPaciente.class);
                startActivity(intent);

            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), "No se puede guardar paciente", Toast.LENGTH_SHORT).show();
            }
        }

        else {
            Toast.makeText(getApplicationContext(), "Nombre, Apellido y Dirección son obligatorios", Toast.LENGTH_SHORT).show();

        }
    }

    private void tomarDatosInterfazPaciente(Paciente paciente) {
        paciente.setNombre(txtnombre.getText().toString());
        paciente.setApellido(txtapellido.getText().toString());
        paciente.setDireccion(txtdireccion.getText().toString());
    }

    public int validarDatosInterfaz() {

        if (txtnombre.getText().toString().equals("") || txtapellido.getText().toString().equals("") || txtdireccion.getText().toString().equals("")) {
            return 0;
        } else {
            Toast.makeText(getApplicationContext(), "Campos Llenos", Toast.LENGTH_SHORT).show();
            return 1;
        }
    }

    public void irCancelar(View view) {
        onPause();
        Toast.makeText(getApplicationContext(), "Listando Paciente", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ActivityAdministrarPaciente.class);
        startActivity(intent);
    }


}


