package com.example.epn.controlador;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.epn.appraepn.R;
import com.example.epn.modelo.entidades.Paciente;
import com.example.epn.modelo.servicios.ServiciosPaciente;

/**
 * Created by Diego on 01/04/2015.
 */
public class ActivityRegistrarPaciente extends Activity {
    ServiciosPaciente serviciosPaciente;

    EditText txtnombre;
    EditText txtapellido;
    EditText txtdireccion;
    Button btnguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_paciente);
        setTitle("Nuevo Registro de Paciente");
        txtnombre=(EditText) findViewById(R.id.TxtNombre);
        txtapellido=(EditText) findViewById(R.id.TxtApellido);
        txtdireccion=(EditText) findViewById(R.id.Txtdireccion);
        serviciosPaciente=new ServiciosPaciente(this);
    }

    public void irGuardar(View view){
        Paciente paciente=new Paciente();

        paciente.setNombre(txtnombre.getText().toString());
        paciente.setApellido(txtapellido.getText().toString());
        paciente.setDireccion(txtdireccion.getText().toString());

        try {
            serviciosPaciente.abrir();
            serviciosPaciente.insertar(paciente);
            Toast.makeText(getApplicationContext(),"Paciente Ingresado",Toast.LENGTH_SHORT).show();
            serviciosPaciente.cerrar();
        }
        catch (Exception e){
            serviciosPaciente.cerrar();
        }

        txtnombre.setText("");
        txtapellido.setText("");
        txtdireccion.setText("");
    }


}
