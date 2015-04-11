package com.example.epn.vista;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
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

    TextView txtnombre;
    TextView txtapellido;
    TextView txtdireccion;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_paciente);

        txtapellido=(TextView)findViewById(R.id.txtApellidoPaciente);
        txtnombre=(TextView)findViewById(R.id.txtNombrePaciente);
        txtdireccion=(TextView)findViewById(R.id.txtDireccionPaciente);

        serviciosPaciente=new ServiciosPaciente(this);
        extras=this.getIntent().getExtras();

        cargarPaciente(extras.getInt("idPaciente"));
        paciente=new Paciente();
    }

    public void cargarPaciente(int id) {
        serviciosPaciente.abrirBD();

        Cursor cursor = null;
        try {
            cursor=serviciosPaciente.recuperarPaciente(id);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        txtnombre.setText(cursor.getString(cursor.getColumnIndex("nombre")));
        txtapellido.setText(cursor.getString(cursor.getColumnIndex("apellido")));
        txtdireccion.setText(cursor.getString(cursor.getColumnIndex("direccion")));

        serviciosPaciente.cerrarBD();
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
