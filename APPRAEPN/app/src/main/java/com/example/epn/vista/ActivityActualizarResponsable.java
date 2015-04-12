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
import com.example.epn.modelo.entidades.Responsable;
import com.example.epn.modelo.servicios.ServiciosPaciente;
import com.example.epn.modelo.servicios.ServiciosResponsable;

import java.sql.SQLException;

/**
 * Created by Diego on 11/04/2015.
 */
public class ActivityActualizarResponsable extends Activity {

    ServiciosResponsable serviciosResponsable;
    Responsable responsable;

    private EditText txtNombre;
    private EditText txtNumeroMovil;
    private EditText txtNumeroFijo;
    private EditText txtDireccionHogar;
    private EditText getTxtDireccionTrabajo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_responsable);
        txtNombre = (EditText) findViewById(R.id.txtNombreResponsable);
        txtNumeroMovil = (EditText) findViewById(R.id.txtTelefonoMovil);
        txtNumeroFijo = (EditText) findViewById(R.id.txtTelefonoFijo);
        txtDireccionHogar = (EditText) findViewById(R.id.txtDireccionHogarResponsable);
        getTxtDireccionTrabajo = (EditText) findViewById(R.id.txtDireccionTrabajoResponsable);

        Bundle extras = this.getIntent().getExtras();

        responsable = new Responsable();
        serviciosResponsable = new ServiciosResponsable(this);
        cargarResponsable(extras.getInt("idResponsable"));

    }

    public void cargarResponsable(int id) {

        serviciosResponsable.abrirConexion();
        try {
            Cursor c = serviciosResponsable.recuperarResponsable(id);
            if (c != null) {
                responsable.setIdresponsable(c.getInt(0));
                responsable.setNombre(c.getString(1));
                responsable.setTelefonoMovil(c.getString(2));
                responsable.setTelefonoFijo(c.getString(3));
                responsable.setDireccionHogar(c.getString(4));
                responsable.setDireccionTrabajo(c.getString(5));
                responsable.setPrioridadResponsable(c.getInt(6));

                txtNombre.setText(responsable.getNombre());
                txtNumeroFijo.setText(responsable.getTelefonoFijo());
                txtNumeroMovil.setText(responsable.getTelefonoMovil());
                txtDireccionHogar.setText(responsable.getDireccionHogar());
                getTxtDireccionTrabajo.setText(responsable.getDireccionTrabajo());
            } else {
                Toast.makeText(this, "El responsable no existe", Toast.LENGTH_LONG).show();
            }
            serviciosResponsable.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void irActualizar(View view) {

        try {
            serviciosResponsable.abrirConexion();
            responsable.setNombre(txtNombre.getText().toString());
            responsable.setTelefonoMovil(txtNumeroMovil.getText().toString());
            responsable.setDireccionHogar(txtDireccionHogar.getText().toString());
            responsable.setDireccionTrabajo(getTxtDireccionTrabajo.getText().toString());
            responsable.setTelefonoFijo(txtNumeroFijo.getText().toString());           
            responsable.setPrioridadResponsable(responsable.getPrioridadResponsable());

            serviciosResponsable.actualizar(responsable);
            Toast.makeText(getApplicationContext(), "Datos de Responsable Actualizado", Toast.LENGTH_SHORT).show();
            serviciosResponsable.cerrarConexion();
            startActivity(new Intent(this, ActivityAdministrarResponsable.class));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No se puede actualizar", Toast.LENGTH_SHORT).show();
        }
    }
}
