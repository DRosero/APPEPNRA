package com.example.epn.vista;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.epn.appraepn.R;
import com.example.epn.modelo.entidades.Paciente;
import com.example.epn.modelo.servicios.ServiciosPaciente;
import java.sql.SQLException;

/**
 * Created by Diego on 07/04/2015.
 */
public class ActivityActualizarPaciente extends Activity {

    ServiciosPaciente serviciosPaciente;
    Paciente paciente;

    EditText txtnombre;
    EditText txtapellido;
    EditText txtdireccion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_paciente);

        txtapellido=(EditText)findViewById(R.id.txtApellidoPaciente);
        txtnombre=(EditText)findViewById(R.id.txtNombrePaciente);
        txtdireccion=(EditText)findViewById(R.id.txtDireccionPaciente);

        Bundle extras=this.getIntent().getExtras();

        paciente=new Paciente();
        serviciosPaciente=new ServiciosPaciente(this);
        cargarPaciente(extras.getInt("idPaciente"));
    }

    public void cargarPaciente(int id) {
        serviciosPaciente.abrirBD();
        try {
            Cursor c=serviciosPaciente.recuperarPaciente(id);
            if(c!=null){
                paciente.setIdpaciente(c.getInt(0));
                paciente.setNombre(c.getString(1));
                paciente.setApellido(c.getString(2));
                paciente.setDireccion(c.getString(3));

                txtnombre.setText(paciente.getNombre());
                txtapellido.setText(paciente.getApellido());
                txtdireccion.setText(paciente.getDireccion());
            }
            else {
                Toast.makeText(this, "El paciente no existe", Toast.LENGTH_LONG).show();
            }
            serviciosPaciente.cerrarBD();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void irActualizar(View view){

        try {
            serviciosPaciente.abrirBD();
            paciente.setNombre(txtnombre.getText().toString());
            paciente.setApellido(txtapellido.getText().toString());
            paciente.setDireccion(txtdireccion.getText().toString());

            serviciosPaciente.actualizar(paciente);
            Toast.makeText(getApplicationContext(), "Datos de Paciente Actualizado", Toast.LENGTH_SHORT).show();
            serviciosPaciente.cerrarBD();
            startActivity(new Intent(this,ActivityAdministrarPaciente.class));
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"No se puede actualizar",Toast.LENGTH_SHORT).show();
        }
    }
}
