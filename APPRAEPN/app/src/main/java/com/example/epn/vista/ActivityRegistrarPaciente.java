package com.example.epn.vista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.epn.appraepn.R;
import com.example.epn.controlador.ControladorPaciente;
import com.example.epn.modelo.entidades.Paciente;
import com.example.epn.modelo.servicios.ServiciosPaciente;

/**
 * Created by Diego on 01/04/2015.
 */
public class ActivityRegistrarPaciente extends Activity {
    ServiciosPaciente serviciosPaciente;
    ControladorPaciente controladorPaciente;

    EditText txtnombre;
    EditText txtapellido;
    EditText txtdireccion;
    Button btnguardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_paciente);
        setTitle("Nuevo Registro de Paciente");
        txtnombre = (EditText) findViewById(R.id.txtNombrePaciente);
        txtapellido = (EditText) findViewById(R.id.txtApellidoPaciente);
        txtdireccion = (EditText) findViewById(R.id.txtDireccionPaciente);
    }

    public void irGuardar(View view){
        try {
            Toast.makeText(getApplicationContext(),"nombre"+txtnombre.getText().toString(),Toast.LENGTH_SHORT).show();
            System.out.println("apellido"+txtapellido.getText().toString());
            System.out.println("direccion"+txtdireccion.getText().toString());

            //Toast.makeText(getApplicationContext(),"nombre"+txtapellido.getText().toString(),Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),"nombre"+txtdireccion.getText().toString(),Toast.LENGTH_SHORT).show();
            controladorPaciente.irGuardar(txtnombre.getText().toString(), txtapellido.getText().toString(), txtdireccion.getText().toString());
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "esta en el catch", Toast.LENGTH_SHORT).show();
        }

    }

    public void irCancelar (View view){
        onPause();
        Toast.makeText(getApplicationContext(),"Listando Paciente", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ActivityAdministrarPaciente.class);
        startActivity(intent );
    }



}


